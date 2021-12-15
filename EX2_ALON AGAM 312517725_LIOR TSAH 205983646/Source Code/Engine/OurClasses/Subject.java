package OurClasses;

import genreatedClasses.ETTSubject;

public class Subject {
    private ETTSubject m_ETTSubject;
    private int m_Id;
    private String m_Name;

    public Subject(ETTSubject currentSubject) {
        m_ETTSubject = currentSubject;
        m_Id = currentSubject.getId();
        m_Name = currentSubject.getName();
    }

    public String getM_Name() {
        return m_Name;
    }

    public void setM_Name(String m_Name) {
        this.m_Name = m_Name;
    }

    public int getM_Id() {
        return m_Id;
    }

    public void setM_Id(int m_Id) {
        this.m_Id = m_Id;
    }

    public ETTSubject getM_ETTSubject() {
        return m_ETTSubject;
    }

    public void setM_ETTSubject(ETTSubject m_ETTSubject) {
        this.m_ETTSubject = m_ETTSubject;
    }
}
