package OurClasses;

import genreatedClasses.ETTEvolutionEngine;

import java.util.*;

public class EvolutionEngine {
    private ETTEvolutionEngine m_ETTEvolutionEngine;
    private InitialPopulation m_InitialPopulation;
    private Crossover m_Crossover;
    private Selection m_Selection;
    private Mutations m_Mutations;
    private Random rnd = new Random();
    private List<BestGrades> list = new ArrayList<>();
    private int days;
    private int hours;
    private int cuttingPoints;

    public Mutations getM_Mutations() {
        return m_Mutations;
    }

    public void setM_Mutations(Mutations m_Mutations) {
        this.m_Mutations = m_Mutations;
    }

    public EvolutionEngine(ETTEvolutionEngine ettEvolutionEngine) {
        m_ETTEvolutionEngine = ettEvolutionEngine;
        m_InitialPopulation = new InitialPopulation(m_ETTEvolutionEngine.getETTInitialPopulation());
        m_Mutations = new Mutations(ettEvolutionEngine.getETTMutations());


    }

    public Selection getM_Selection() {
        return m_Selection;
    }

    public void setM_Selection(Selection m_Selection) {
        this.m_Selection = m_Selection;
    }

    public InitialPopulation getM_InitialPopulation() {
        return m_InitialPopulation;
    }

    public void setM_InitialPopulation(InitialPopulation m_InitialPopulation) {
        this.m_InitialPopulation = m_InitialPopulation;
    }

    public ETTEvolutionEngine getM_ETTEvolutionEngine() {
        return m_ETTEvolutionEngine;
    }

    public void setM_ETTEvolutionEngine(ETTEvolutionEngine m_ETTEvolutionEngine) {
        this.m_ETTEvolutionEngine = m_ETTEvolutionEngine;
    }

    public List<BestGrades> getList() {
        return list;
    }

    public void startAlgorithm(Descriptor descriptor) {
        descriptor.getSolutions().initialSolutions(descriptor);
        descriptor.getSolutions().calculateGrades(descriptor);
        checkSelectionType(descriptor);
        checkCrossoverType(descriptor);
    }

    public void executeMutations(Descriptor descriptor) {
        int lenOfList = descriptor.getSolutions().getListOfSolutions().size();
        Solution solutionToChange = descriptor.getSolutions().getListOfSolutions().get(rnd.nextInt(lenOfList));
        if (!solutionToChange.isElitism()) {
            m_Mutations.executeMutations(descriptor, solutionToChange);
        }
    }


    public void checkDifference(int i, List<Solution> listOfSolutions) {
        int grade = listOfSolutions.get(0).getFitnessGrade();
        int difference;
        if (i == 0) {
            difference = 0;
        } else {
            difference = grade - list.get((list.size()) - 1).getGrade();
        }
        list.add(new BestGrades(grade, difference));
    }

    private void checkCrossoverType(Descriptor descriptor) {
        days = descriptor.getEttDescriptor().getETTTimeTable().getDays();
        hours = descriptor.getEttDescriptor().getETTTimeTable().getHours();
        cuttingPoints = descriptor.getEttDescriptor().getETTEvolutionEngine().getETTCrossover().getCuttingPoints();

        switch (descriptor.getEttDescriptor().getETTEvolutionEngine().getETTCrossover().getName()) {
            case "DayTimeOriented":
                m_Crossover = new DayTimeOriented(days, hours, cuttingPoints);
                break;
            case "AspectOriented":
                m_Crossover = new AspectOriented(days, hours, cuttingPoints);
                break;
        }
    }

    public Crossover getCrossover() {
        return m_Crossover;
    }

    public void setCrossover(Crossover m_Crossover) {
        this.m_Crossover = m_Crossover;
    }

    public void executeCrossover(Descriptor descriptor) {
        m_Crossover.executeCrossover(descriptor);
    }

    public void executeSelection(Descriptor descriptor) {
        m_Selection.executeSelection(descriptor);
    }

    private void checkSelectionType(Descriptor descriptor) {
        switch (descriptor.getEttDescriptor().getETTEvolutionEngine().getETTSelection().getType()) {
            case "Truncation":
                m_Selection = new Truncation();
                break;
            case "RouletteWheel":
                m_Selection = new RouletteWheel();
                break;
        }
    }

    public void setSelection(Selection newSelection) {
        this.m_Selection = newSelection;
    }
}