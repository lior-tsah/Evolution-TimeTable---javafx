package OurClasses;

import genreatedClasses.ETTClass;
import genreatedClasses.ETTSubject;
import genreatedClasses.ETTTeacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Solution implements Cloneable {
    private List<Fiver> possibleSolution;
    private Grade grade;
    private int fitnessGrade;
    private RulesChecker ettRulesChecker = new RulesChecker();
    private Random rand;
    private double probability;
    private boolean isElitism;

    public boolean isElitism() {
        return isElitism;
    }

    public void setElitism(boolean elitism) {
        isElitism = elitism;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public Solution(Solution otherSolution) {
        this.ettRulesChecker = otherSolution.ettRulesChecker;
        this.grade = new Grade();
        this.fitnessGrade = 0;
        this.rand = otherSolution.rand;
        this.possibleSolution = new ArrayList<>();
        this.possibleSolution.addAll(otherSolution.getPossibleSolution());
    }

    public Solution() {
        this.possibleSolution = new ArrayList<>();
        this.grade = new Grade();
        rand = new Random();
    }


    public RulesChecker getEttRulesChecker() {
        return ettRulesChecker;
    }

    public void setEttRulesChecker(RulesChecker ettRulesChecker) {
        this.ettRulesChecker = ettRulesChecker;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public int getFitnessGrade() {
        return fitnessGrade;
    }

    public void setFitnessGrade(int fitnessGrade) {
        this.fitnessGrade = fitnessGrade;
    }

    public List<Fiver> getPossibleSolution() {
        return possibleSolution;
    }

    public void createPossibleSolution(Descriptor descriptor) {
        int maxDay = descriptor.getEttDescriptor().getETTTimeTable().getDays();
        int maxHours = descriptor.getEttDescriptor().getETTTimeTable().getHours();
        int maxClass = descriptor.getEttDescriptor().getETTTimeTable().getETTClasses().getETTClass().size();
        int sizeOfSolution = maxDay * maxHours * maxClass;


        for (int i = 0; i < sizeOfSolution; i++) {
            int day = rand.nextInt(maxDay) + 1;
            int hour = rand.nextInt(maxHours) + 1;
            int ettClass = rand.nextInt(maxClass) + 1;
            int ettTeacher = rand.nextInt(descriptor.getEttDescriptor().getETTTimeTable().getETTTeachers().getETTTeacher().size()) + 1;
            int ettSubject = rand.nextInt(descriptor.getEttDescriptor().getETTTimeTable().getETTSubjects().getETTSubject().size()) + 1;
            Optional<ETTSubject> subjectById = descriptor.getEttDescriptor().getETTTimeTable().getETTSubjects().getETTSubject().stream().
                    filter(pp -> pp.getId() == ettSubject).
                    findFirst();
            Optional<ETTClass> classById = descriptor.getEttDescriptor().getETTTimeTable().getETTClasses().getETTClass().stream().
                    filter(pp -> pp.getId() == ettClass).
                    findFirst();
            Optional<ETTTeacher> teacherById = descriptor.getEttDescriptor().getETTTimeTable().getETTTeachers().getETTTeacher().stream().
                    filter(pp -> pp.getId() == ettTeacher).
                    findFirst();
            Fiver ettFiver = new Fiver(day, hour, classById.get(), teacherById.get(), subjectById.get());
            possibleSolution.add(ettFiver);
        }
    }

    public void setPossibleSolution(List<Fiver> possibleSolution) {
        this.possibleSolution = possibleSolution;
    }

}