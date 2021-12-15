package OurClasses;

import genreatedClasses.ETTClass;
import genreatedClasses.ETTMutation;
import genreatedClasses.ETTSubject;
import genreatedClasses.ETTTeacher;

import java.util.Optional;

public class Sizer extends Mutation {
    private int totalTupples;

    public int getTotalTupples() {
        return totalTupples;
    }

    public void setTotalTupples(int totalTupples) {
        this.totalTupples = totalTupples;
    }

    public Sizer(ETTMutation currentMutation) {
        super();
        m_ETTMutation = currentMutation;
        this.m_ETTMutation.setConfiguration(currentMutation.getConfiguration());
        this.m_ETTMutation.setProbability(currentMutation.getProbability());
        this.m_ETTMutation.setName(currentMutation.getName());
    }

    @Override
    public void executeMutation(Descriptor descriptor, Solution solution) {
        Solution newSolution = new Solution(solution);
        int numOfMutations;
        int probability = rnd.nextInt(101);
        if (probability > (this.m_ETTMutation.getProbability() * 100)) {
            splitConfiguration(this.m_ETTMutation);
            numOfMutations = randomNumber();
            if (totalTupples < 0) {
                removeFivers(descriptor, numOfMutations, newSolution);
            } else {
                addFivers(descriptor, numOfMutations, newSolution);
            }
            descriptor.getSolutions().getListOfSolutions().add(newSolution);

        }
    }


    private void removeFivers(Descriptor descriptor, int numOfMutations, Solution newSolution) {
        int i = numOfMutations;
        int size = newSolution.getPossibleSolution().size();
        int days = descriptor.getM_TimeTable().getM_Days();
        while (i > 0 && size > days) {
            newSolution.getPossibleSolution().remove(size - 1);
            size--;
            i--;
        }
    }


    private void addFivers(Descriptor descriptor, int numOfMutations, Solution newSolution) {
        int i = numOfMutations;
        int size = newSolution.getPossibleSolution().size();
        int daysMulHours = descriptor.getM_TimeTable().getM_Days() * descriptor.getM_TimeTable().getM_Hours();

        while (i > 0 && size < daysMulHours) {
            int day = createDay(descriptor.getM_TimeTable().getM_Days());
            int hour = createHour(descriptor.getM_TimeTable().getM_Hours());
            ETTClass ettclass = createClass(descriptor);
            ETTTeacher teacher = createTeacher(descriptor);
            ETTSubject subject = createSubject(descriptor);
            Fiver newFiver = new Fiver(day, hour, ettclass, teacher, subject);
            newSolution.getPossibleSolution().add(newFiver);
        }
    }

    private ETTSubject createSubject(Descriptor descriptor) {
        int newSubject = rnd.nextInt(descriptor.getEttDescriptor().getETTTimeTable().getETTSubjects().getETTSubject().size()) + 1;
        Optional<ETTSubject> subjectById = descriptor.getEttDescriptor().getETTTimeTable().getETTSubjects().getETTSubject().stream().
                filter(pp -> pp.getId() == newSubject).
                findFirst();

        return subjectById.get();
    }

    private ETTTeacher createTeacher(Descriptor descriptor) {
        int newTeacher = rnd.nextInt(descriptor.getEttDescriptor().getETTTimeTable().getETTTeachers().getETTTeacher().size()) + 1;
        Optional<ETTTeacher> teacherById = descriptor.getEttDescriptor().getETTTimeTable().getETTTeachers().getETTTeacher().stream().
                filter(pp -> pp.getId() == newTeacher).
                findFirst();

        return teacherById.get();
    }

    private ETTClass createClass(Descriptor descriptor) {
        int newClass = rnd.nextInt(descriptor.getEttDescriptor().getETTTimeTable().getETTClasses().getETTClass().size()) + 1;
        Optional<ETTClass> classById = descriptor.getEttDescriptor().getETTTimeTable().getETTClasses().getETTClass().stream().
                filter(pp -> pp.getId() == newClass).
                findFirst();
        return classById.get();
    }

    private int createHour(int m_hours) {
        return rnd.nextInt(m_hours) + 1;

    }

    private int createDay(int m_days) {
        return rnd.nextInt(m_days) + 1;
    }


    private int randomNumber() {
        int value = totalTupples;
        if (value < 0) {
            value *= -1;
        }
        int num = rnd.nextInt(value + 1);
        return num;
    }

    @Override
    public void splitConfiguration(ETTMutation ettMutation) {
        totalTupples = Integer.parseInt(ettMutation.getConfiguration().split("=")[1]);
    }
}
