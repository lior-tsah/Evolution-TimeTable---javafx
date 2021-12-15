package OurClasses;

import genreatedClasses.ETTStudy;

public class Study {
    private ETTStudy m_ETTStudy;
    private int m_SubjectId;
    private int m_Hours;

    public Study(ETTStudy currentStudy) {
        m_ETTStudy = currentStudy;
        m_SubjectId = currentStudy.getSubjectId();
        m_Hours = currentStudy.getHours();
    }

    public int getM_SubjectId() {
        return m_SubjectId;
    }

    public void setM_SubjectId(int m_SubjectId) {
        this.m_SubjectId = m_SubjectId;
    }

    public int getM_Hours() {
        return m_Hours;
    }

    public void setM_Hours(int m_Hours) {
        this.m_Hours = m_Hours;
    }

    public ETTStudy getM_ETTStudy() {
        return m_ETTStudy;
    }

    public void setM_ETTStudy(ETTStudy m_ETTStudy) {
        this.m_ETTStudy = m_ETTStudy;
    }
}
