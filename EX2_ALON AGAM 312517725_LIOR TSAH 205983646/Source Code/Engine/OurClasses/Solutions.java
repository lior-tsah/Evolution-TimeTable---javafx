package OurClasses;

import genreatedClasses.ETTRule;

import java.util.*;

public class Solutions {
    private List<Solution> listOfSolutions = new ArrayList<>();

    public List<Solution> getListOfSolutions() {
        return listOfSolutions;
    }

    public void setListOfSolutions(List<Solution> listOfSolutions) {
        this.listOfSolutions = listOfSolutions;
    }

    public void initialSolutions(Descriptor descriptor) {
        for (int j = 0; j < descriptor.getEttDescriptor().getETTEvolutionEngine().getETTInitialPopulation().getSize(); j++) {
            Solution ettSolution = new Solution();
            ettSolution.createPossibleSolution(descriptor);
            listOfSolutions.add(ettSolution);
        }
    }

    public void calculateFirstRule(Solution currentSolution, Descriptor descriptor) {
        Optional<ETTRule> ruleById;
        int grade = currentSolution.getEttRulesChecker().checkTeacherIsHuman(currentSolution);
        ruleById = descriptor.getEttDescriptor().getETTTimeTable().getETTRules().getETTRule().stream().
                filter(pp -> pp.getETTRuleId().equals("TeacherIsHuman")).findFirst();
        if (!ruleById.toString().equals("Optional.empty")) {
            currentSolution.getGrade().getListOfGradesByRule().put(ruleById.get(), grade);

        }
    }

    public void calculateSecondRule(Solution currentSolution, Descriptor descriptor) {
        Optional<ETTRule> ruleById;
        int grade = currentSolution.getEttRulesChecker().checkKnowledgeable(currentSolution);
        ruleById = descriptor.getEttDescriptor().getETTTimeTable().getETTRules().getETTRule().stream().
                filter(pp -> pp.getETTRuleId().equals("Knowledgeable")).findFirst();
        if (!ruleById.toString().equals("Optional.empty")) {
            currentSolution.getGrade().getListOfGradesByRule().put(ruleById.get(), grade);

        }
    }

    public void calculateThirdRule(Solution currentSolution, Descriptor descriptor) {
        Optional<ETTRule> ruleById;
        int grade = currentSolution.getEttRulesChecker().checkSingularity(currentSolution);
        ruleById = descriptor.getEttDescriptor().getETTTimeTable().getETTRules().getETTRule().stream().
                filter(pp -> pp.getETTRuleId().equals("Singularity")).findFirst();
        if (!ruleById.toString().equals("Optional.empty")) {
            currentSolution.getGrade().getListOfGradesByRule().put(ruleById.get(), grade);

        }
    }

    public void calculateFourthRule(Solution currentSolution, Descriptor descriptor) {
        Optional<ETTRule> ruleById;
        int grade = currentSolution.getEttRulesChecker().checkSatisfactory(currentSolution, descriptor.getEttDescriptor().getETTTimeTable());
        ruleById = descriptor.getEttDescriptor().getETTTimeTable().getETTRules().getETTRule().stream().
                filter(pp -> pp.getETTRuleId().equals("Satisfactory")).findFirst();
        if (!ruleById.toString().equals("Optional.empty")) {
            currentSolution.getGrade().getListOfGradesByRule().put(ruleById.get(), grade);

        }
    }

    public void calculateGrades(Descriptor descriptor) {
        for (Solution currentSolution : descriptor.getSolutions().getListOfSolutions()) {
            currentSolution.getGrade().setAverageHardRules(0.0);
            currentSolution.getGrade().setAverageSoftRules(0.0);

            calculateFirstRule(currentSolution, descriptor);
            calculateSecondRule(currentSolution, descriptor);
            calculateThirdRule(currentSolution, descriptor);
            calculateFourthRule(currentSolution, descriptor);
            calculateFifthRule(currentSolution, descriptor);
            calculateSixthRule(currentSolution, descriptor);

        }
        fitnessEvaluation(descriptor);
    }

    private void calculateFifthRule(Solution currentSolution, Descriptor descriptor) {
        Optional<ETTRule> ruleById;
        int grade = currentSolution.getEttRulesChecker().checkDayOffTeacher(currentSolution, descriptor.getEttDescriptor().getETTTimeTable());
        ruleById = descriptor.getEttDescriptor().getETTTimeTable().getETTRules().getETTRule().stream().
                filter(pp -> pp.getETTRuleId().equals("DayOffTeacher")).findFirst();
        if (!ruleById.toString().equals("Optional.empty")) {
            currentSolution.getGrade().getListOfGradesByRule().put(ruleById.get(), grade);
        }
    }

    private void calculateSixthRule(Solution currentSolution, Descriptor descriptor) {
        Optional<ETTRule> ruleById;
        int grade = currentSolution.getEttRulesChecker().checkSequentiality(currentSolution, descriptor.getEttDescriptor().getETTTimeTable());
        ruleById = descriptor.getEttDescriptor().getETTTimeTable().getETTRules().getETTRule().stream().
                filter(pp -> pp.getETTRuleId().equals("Sequentiality")).findFirst();

        if (!ruleById.toString().equals("Optional.empty")) {
            currentSolution.getGrade().getListOfGradesByRule().put(ruleById.get(), grade);
        }
    }


    public void fitnessEvaluation(Descriptor descriptor) {
        for (Solution currenSolution : listOfSolutions) {
            int res = currenSolution.getGrade().calculateAveragesGradesOfSolutionByHardRuleWeight(descriptor.getEttDescriptor().getETTTimeTable().getETTRules().getHardRulesWeight());
            currenSolution.setFitnessGrade(res * (-1));
        }
        Comparator<Solution> comparator = Comparator.comparingDouble(Solution::getFitnessGrade);
        Collections.sort(listOfSolutions, comparator);
        for (Solution currenSolution : listOfSolutions) {
            currenSolution.setFitnessGrade(currenSolution.getFitnessGrade() * -1);
        }
        descriptor.getSolutions().setListOfSolutions(this.listOfSolutions);
    }
}