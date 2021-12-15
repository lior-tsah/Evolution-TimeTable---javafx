package OurClasses;

import genreatedClasses.ETTInitialPopulation;

public class InitialPopulation {
    private ETTInitialPopulation m_ETTInitialPopulation;
    private int m_Size;

    public InitialPopulation(ETTInitialPopulation m_ettInitialPopulation) {
        m_Size = m_ettInitialPopulation.getSize();
    }

    public int getM_Size() {
        return m_Size;
    }

    public void setM_Size(int m_Size) {
        this.m_Size = m_Size;
    }

    public ETTInitialPopulation getM_ETTInitialPopulation() {
        return m_ETTInitialPopulation;
    }

    public void setM_ETTInitialPopulation(ETTInitialPopulation m_ETTInitialPopulation) {
        this.m_ETTInitialPopulation = m_ETTInitialPopulation;
    }
}
