package classes;

public class GenerationProgress {
    private Integer fitnessGrade;
    private Integer generation;

    public GenerationProgress(Integer fitnessGrade, Integer generation) {
        this.fitnessGrade = fitnessGrade;
        this.generation = generation;
    }

    public Integer getFitnessGrade() {
        return fitnessGrade;
    }

    public void setFitnessGrade(int fitnessGrade) {
        this.fitnessGrade = fitnessGrade;
    }

    public Integer getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }
}
