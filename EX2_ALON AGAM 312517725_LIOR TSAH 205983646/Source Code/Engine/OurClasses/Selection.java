package OurClasses;

import genreatedClasses.ETTSelection;

import java.util.*;

public abstract class Selection {
    protected ETTSelection m_ETTSelection;
    protected List<Solution> listOfSolutions =new ArrayList<>();
    protected Random random = new Random();
    protected int elitism =0;

    public int getElitism() {
        return elitism;
    }

    public void setElitism(int elitism) {
        this.elitism = elitism;
    }

    public ETTSelection getM_ETTSelection() {
        return m_ETTSelection;
    }

    public List<Solution> getListOfSolutions() {
        return listOfSolutions;
    }

    public void setListOfSolutions(List<Solution> listOfSolutions) {
        this.listOfSolutions = listOfSolutions;
    }

    public void setM_ETTSelection(ETTSelection m_ETTSelection) {
        this.m_ETTSelection = m_ETTSelection;
    }

    public abstract void executeSelection(Descriptor descriptor);

    public void sortListOfSolutionsByFitness(Descriptor descriptor) {
        descriptor.getSolutions().getListOfSolutions().forEach(solution -> solution.setElitism(false));
        descriptor.getSolutions().getListOfSolutions().forEach(solution -> solution.setFitnessGrade(solution.getFitnessGrade()*-1));
        Collections.sort(descriptor.getSolutions().getListOfSolutions(), Comparator.comparing(Solution::getFitnessGrade));
        descriptor.getSolutions().getListOfSolutions().forEach(solution -> solution.setFitnessGrade(solution.getFitnessGrade()*-1));
        insertElitismToList(descriptor);
    }

    public void insertElitismToList(Descriptor descriptor){
        if (descriptor.getEvolutionEngine().getM_ETTEvolutionEngine().getETTSelection().getETTElitism() != null) {
            elitism = descriptor.getEvolutionEngine().getM_ETTEvolutionEngine().getETTSelection().getETTElitism();
            for (int i = 0; i < elitism; i++) {
                descriptor.getSolutions().getListOfSolutions().get(i).setElitism(true);
                listOfSolutions.add(descriptor.getSolutions().getListOfSolutions().get(i));
            }
        }
    }
}
