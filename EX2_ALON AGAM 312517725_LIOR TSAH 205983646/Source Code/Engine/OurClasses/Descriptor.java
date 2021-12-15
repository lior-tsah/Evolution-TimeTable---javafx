package OurClasses;

import genreatedClasses.*;

public class Descriptor {
    private ETTDescriptor m_ETTDescriptor;
    private TimeTable m_TimeTable;
    private Solutions m_Solutions;
    private EvolutionEngine m_EvolutionEngine;

    public   void loadDescriptor() {
        m_EvolutionEngine = new EvolutionEngine(m_ETTDescriptor.getETTEvolutionEngine());
        m_Solutions = new Solutions();
        m_TimeTable = new TimeTable(m_ETTDescriptor.getETTTimeTable());
    }

    public TimeTable getM_TimeTable() {
        return m_TimeTable;
    }

    public void setM_TimeTable(TimeTable m_TimeTable) {
        this.m_TimeTable = m_TimeTable;
    }

    public Solutions getSolutions() {
        return m_Solutions;
    }

    public EvolutionEngine getEvolutionEngine() {
        return m_EvolutionEngine;
    }

    public void setEvolutionEngine(EvolutionEngine evolutionEngine) {
        this.m_EvolutionEngine = evolutionEngine;
    }

    public void setSolutions(Solutions ettSolutions) {
        this.m_Solutions = ettSolutions;
    }

    public ETTDescriptor getEttDescriptor() {
        return m_ETTDescriptor;
    }

    public void setEttDescriptor(ETTDescriptor ettDescriptor) {
        this.m_ETTDescriptor = ettDescriptor;
    }
}