package OurClasses;

import genreatedClasses.ETTTeacher;

public class Teacher {
    private ETTTeacher m_ETTTeacher;
    private int m_Id;
    private String m_Name;
    private Teaching m_Teaching;


    public Teacher(ETTTeacher currentTeacher) {
        m_ETTTeacher = currentTeacher;
        m_Id = currentTeacher.getId();
        m_Name = currentTeacher.getETTName();
        m_Teaching = new Teaching(currentTeacher.getETTTeaching());
    }

    public int getM_Id() {
        return m_Id;
    }

    public void setM_Id(int m_Id) {
        this.m_Id = m_Id;
    }

    public String getM_Name() {
        return m_Name;
    }

    public void setM_Name(String m_Name) {
        this.m_Name = m_Name;
    }

    public Teaching getM_Teaching() {
        return m_Teaching;
    }

    public void setM_Teaching(Teaching m_Teaching) {
        this.m_Teaching = m_Teaching;
    }

    public ETTTeacher getM_ETTTeacher() {
        return m_ETTTeacher;
    }

    public void setM_ETTTeacher(ETTTeacher m_ETTTeacher) {
        this.m_ETTTeacher = m_ETTTeacher;
    }
}
