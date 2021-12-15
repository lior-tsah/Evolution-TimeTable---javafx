package OurClasses;

import genreatedClasses.ETTTeaches;

public class Teaches {

    private ETTTeaches m_ETTTeaches;
    private int m_SubjectId;

    public Teaches(ETTTeaches currentTeaching) {
        m_ETTTeaches = currentTeaching;
        m_SubjectId = currentTeaching.getSubjectId();
    }

    public int getM_SubjectId() {
        return m_SubjectId;
    }

    public void setM_SubjectId(int m_SubjectId) {
        this.m_SubjectId = m_SubjectId;
    }

    public ETTTeaches getM_ETTTeaches() {
        return m_ETTTeaches;
    }

    public void setM_ETTTeaches(ETTTeaches m_ETTTeaches) {
        this.m_ETTTeaches = m_ETTTeaches;
    }
}
