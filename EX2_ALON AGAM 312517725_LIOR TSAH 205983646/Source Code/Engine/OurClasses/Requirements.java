package OurClasses;

import genreatedClasses.ETTRequirements;
import genreatedClasses.ETTStudy;

import java.util.ArrayList;
import java.util.List;

public class Requirements {
    private ETTRequirements m_ETTRequirements;
    private List<Study> m_Study;

    public Requirements(ETTRequirements ettRequirements) {
        m_ETTRequirements = ettRequirements;
        m_Study = new ArrayList<>();
        for (ETTStudy currentStudy : m_ETTRequirements.getETTStudy()) {
            m_Study.add(new Study(currentStudy));
        }
    }

    public List<Study> getM_Study() {
        return m_Study;
    }

    public void setM_Study(List<Study> m_Study) {
        this.m_Study = m_Study;
    }

    public ETTRequirements getM_ETTRequirements() {
        return m_ETTRequirements;
    }

    public void setM_ETTRequirements(ETTRequirements m_ETTRequirements) {
        this.m_ETTRequirements = m_ETTRequirements;
    }
}
