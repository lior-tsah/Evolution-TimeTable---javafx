package OurClasses;


import genreatedClasses.*;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class RulesChecker {
    public int checkTeacherIsHuman(Solution solution) {
        double countOfDuplicates = 0;

        for (Fiver xFiver : solution.getPossibleSolution()) {
            for (Fiver yFiver : solution.getPossibleSolution()) {
                if (xFiver != yFiver) {
                    if (xFiver.getD() == yFiver.getD() && xFiver.getH() == yFiver.getH() && xFiver.getT().equals(yFiver.getT())) {

                        countOfDuplicates++;
                    }
                }
            }
        }
        countOfDuplicates /= 2;
        return 100 - (int) ((countOfDuplicates / (solution.getPossibleSolution().size())) * 100);
    }

    public int checkSingularity(Solution solution) {
        double countOfNotSingularity = 0;

        for (Fiver xFiver : solution.getPossibleSolution()) {
            for (Fiver yFiver : solution.getPossibleSolution()) {
                if (xFiver != yFiver) {
                    if (xFiver.getD() == yFiver.getD() && xFiver.getH() == yFiver.getH() &&
                            xFiver.getC().equals(yFiver.getC()) && !(xFiver.getT().equals(yFiver.getT()))) {
                        countOfNotSingularity++;
                    }
                }
            }
        }
        countOfNotSingularity /= 2;
        return 100 - (int) ((countOfNotSingularity / (solution.getPossibleSolution().size())) * 100);
    }

    public int checkKnowledgeable(Solution ettSolution) {
        double countOfNotKnowledgeable = 0;

        for (Fiver fiver : ettSolution.getPossibleSolution()) {
            ETTTeacher teacher = fiver.getT();
            for (ETTTeaches teaches : teacher.getETTTeaching().getETTTeaches()) {
                if (teaches.getSubjectId() == fiver.getS().getId()) {
                    countOfNotKnowledgeable--;
                }
            }
            countOfNotKnowledgeable++;
        }
        return 100 - (int) ((countOfNotKnowledgeable / (ettSolution.getPossibleSolution().size())) * 100);
    }

    public int checkSatisfactory(Solution ettSolution, ETTTimeTable ettTimeTable) {
        double countNotSatisfactory = 0;
        int count = 0;
        int sum = 0;

        for (ETTClass currentClass : ettTimeTable.getETTClasses().getETTClass()) {
            for (ETTStudy ettStudy : currentClass.getETTRequirements().getETTStudy()) {
                sum++;
                for (Fiver ettFiver : ettSolution.getPossibleSolution()) {
                    if (ettFiver.getC().equals(currentClass) && ettFiver.getS().getId() == ettStudy.getSubjectId()) {
                        count++;
                    }
                }
                if (count == ettStudy.getHours()) {
                    countNotSatisfactory++;
                }
                count = 0;

            }
        }
        return (int) ((countNotSatisfactory / sum) * 100);
    }

    public int checkDayOffTeacher(Solution ettSolution, ETTTimeTable ettTimeTable) {
        int size = ettTimeTable.getETTTeachers().getETTTeacher().size();
        int numberDayOffTeachers = size;

        for (ETTTeacher currentTeacher : ettTimeTable.getETTTeachers().getETTTeacher()) {
            Set<Integer> set = new HashSet<>();
            for (Fiver currentFiver : ettSolution.getPossibleSolution()) {
                if (currentFiver.getT().equals(currentTeacher)) {
                    set.add(currentFiver.getD());
                }
            }
            if (set.size() > ettTimeTable.getDays()) {
                numberDayOffTeachers--;
            }
        }
        return (int) ((double) numberDayOffTeachers) / size * 100;
    }

    public int checkSequentiality(Solution ettSolution, ETTTimeTable ettTimeTable) {
        int maxHours =0;
        Optional<ETTRule> ruleById = ettTimeTable.getETTRules().getETTRule().stream().
                filter(pp -> pp.getETTRuleId().equals("Sequentiality")).findFirst();
        if (!ruleById.toString().equals("Optional.empty")) {
            maxHours = Integer.parseInt(ruleById.get().getETTConfiguration().split("=")[1]);
        }

        int countSameSubject = 0;
        int size = ettTimeTable.getETTSubjects().getETTSubject().size();
        int grade = size;

        ettSolution.getPossibleSolution().sort(Comparator.comparing(Fiver::getD).thenComparing(Fiver::getH));
        for (ETTClass currentClass : ettTimeTable.getETTClasses().getETTClass()) {
            for (int i = 0; i < ettSolution.getPossibleSolution().size() - 1; i++) {
                Fiver curr = ettSolution.getPossibleSolution().get(i);
                Fiver next = ettSolution.getPossibleSolution().get(i + 1);
                if (curr.getC().equals(currentClass) && next.getC().equals(currentClass)) {
                    if (curr.getD() == next.getD() && curr.getH() == next.getH() + 1) {
                        countSameSubject++;
                    } else {
                        countSameSubject = 0;
                    }
                } else {
                    countSameSubject = 0;
                }
            }
            if (countSameSubject > maxHours) {
                grade--;
            }

        }
        return (int)((double)(grade)/size*100);
    }
}