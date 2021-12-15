package OurClasses;

import genreatedClasses.ETTClass;
import genreatedClasses.ETTMutation;
import genreatedClasses.ETTSubject;
import genreatedClasses.ETTTeacher;

import java.util.Optional;
import java.util.Random;

public class Flipping extends Mutation {
    protected int maxFiversToChange;
    protected int maxTuples;
    protected String componentToChange;

    public int getMaxFiversToChange() {
        return maxFiversToChange;
    }

    public void setMaxFiversToChange(int maxFiversToChange) {
        this.maxFiversToChange = maxFiversToChange;
    }

    public int getMaxTuples() {
        return maxTuples;
    }

    public void setMaxTuples(int maxTuples) {
        this.maxTuples = maxTuples;
    }

    public String getComponentToChange() {
        return componentToChange;
    }

    public void setComponentToChange(String componentToChange) {
        this.componentToChange = componentToChange;
    }

    public Flipping(ETTMutation currentMutation) {
        super();
        m_ETTMutation = currentMutation;
        this.m_ETTMutation.setConfiguration(currentMutation.getConfiguration());
        this.m_ETTMutation.setProbability(currentMutation.getProbability());
        this.m_ETTMutation.setName(currentMutation.getName());
    }

    public void executeMutation(Descriptor descriptor, Solution solution) {
        Solution newSolution = new Solution(solution);
        int probability = rnd.nextInt(101);
        if (probability > (this.m_ETTMutation.getProbability() * 100)) {
            splitConfiguration(this.m_ETTMutation);
            maxFiversToChange = rnd.nextInt(maxTuples + 1);
            chooseRandomFivers(descriptor, newSolution);
            descriptor.getSolutions().getListOfSolutions().add(newSolution);
        }
    }

    private void chooseRandomFivers(Descriptor descriptor, Solution solution) {
        for (int i = 0; i < maxFiversToChange; i++) {
            int index = rnd.nextInt(solution.getPossibleSolution().size());
            Fiver fiverToChange = solution.getPossibleSolution().get(index);
            executeComponentChange(descriptor, fiverToChange);
        }
    }

    private void executeComponentChange(Descriptor descriptor, Fiver fiverToChange) {
        switch (componentToChange) {
            case "D":
                changeDay(descriptor, fiverToChange);
                break;
            case "H":
                changeHour(descriptor, fiverToChange);
                break;
            case "T":
                changeTeacher(descriptor, fiverToChange);
                break;
            case "C":
                changeClass(descriptor, fiverToChange);
                break;
            case "S":
                changeSubject(descriptor, fiverToChange);
                break;
        }
    }

    private void changeSubject(Descriptor descriptor, Fiver fiverToChange) {
        int newSubject = rnd.nextInt(descriptor.getEttDescriptor().getETTTimeTable().getETTSubjects().getETTSubject().size()) + 1;
        Optional<ETTSubject> subjectById = descriptor.getEttDescriptor().getETTTimeTable().getETTSubjects().getETTSubject().stream().
                filter(pp -> pp.getId() == newSubject).
                findFirst();
        fiverToChange.setS(subjectById.get());
    }

    private void changeClass(Descriptor descriptor, Fiver fiverToChange) {
        int newClass = rnd.nextInt(descriptor.getEttDescriptor().getETTTimeTable().getETTClasses().getETTClass().size()) + 1;
        Optional<ETTClass> classById = descriptor.getEttDescriptor().getETTTimeTable().getETTClasses().getETTClass().stream().
                filter(pp -> pp.getId() == newClass).
                findFirst();
        fiverToChange.setC(classById.get());

    }

    private void changeTeacher(Descriptor descriptor, Fiver fiverToChange) {
        int newTeacher = rnd.nextInt(descriptor.getEttDescriptor().getETTTimeTable().getETTTeachers().getETTTeacher().size()) + 1;
        Optional<ETTTeacher> teacherById = descriptor.getEttDescriptor().getETTTimeTable().getETTTeachers().getETTTeacher().stream().
                filter(pp -> pp.getId() == newTeacher).
                findFirst();
        fiverToChange.setT(teacherById.get());
    }

    private void changeHour(Descriptor descriptor, Fiver fiverToChange) {
        int newHour = rnd.nextInt(descriptor.getEttDescriptor().getETTTimeTable().getHours()) + 1;
        fiverToChange.setD(newHour);
    }

    private void changeDay(Descriptor descriptor, Fiver fiverToChange) {
        int newDay = rnd.nextInt(descriptor.getEttDescriptor().getETTTimeTable().getDays()) + 1;
        fiverToChange.setD(newDay);
    }

    public void splitConfiguration(ETTMutation ettMutation) {
        maxTuples = Integer.parseInt(ettMutation.getConfiguration().split(",")[0].split("=")[1]);
        componentToChange = ettMutation.getConfiguration().split(",")[1].split("=")[1];
    }
}