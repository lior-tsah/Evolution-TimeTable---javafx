package OurClasses;

import genreatedClasses.ETTTimeTable;


public class TimeTable {
    private ETTTimeTable m_ETTTimeTable;
    private Teachers m_Teachers;
    private Subjects m_Subjects;
    private Classes m_Classes;
    private Rules m_Rules;
    private int m_Days;

    public int getM_Days() {
        return m_Days;
    }

    public void setM_Days(int m_Days) {
        this.m_Days = m_Days;
    }

    public int getM_Hours() {
        return m_Hours;
    }

    public void setM_Hours(int m_Hours) {
        this.m_Hours = m_Hours;
    }

    private int m_Hours;


    public TimeTable(ETTTimeTable ettTimeTable) {
        m_ETTTimeTable = ettTimeTable;
        m_Teachers = new Teachers(m_ETTTimeTable.getETTTeachers());
        m_Subjects = new Subjects(m_ETTTimeTable.getETTSubjects());
        m_Classes = new Classes(m_ETTTimeTable.getETTClasses());
        m_Rules = new Rules(m_ETTTimeTable.getETTRules());
        m_Days = m_ETTTimeTable.getDays();
        m_Hours = m_ETTTimeTable.getHours();
    }



    public Teachers getM_Teachers() {
        return m_Teachers;
    }

    public void setM_Teachers(Teachers m_Teachers) {
        this.m_Teachers = m_Teachers;
    }

    public Subjects getM_Subjects() {
        return m_Subjects;
    }

    public void setM_Subjects(Subjects m_Subjects) {
        this.m_Subjects = m_Subjects;
    }

    public Classes getM_Classes() {
        return m_Classes;
    }

    public void setM_Classes(Classes m_Classes) {
        this.m_Classes = m_Classes;
    }

    public Rules getM_Rules() {
        return m_Rules;
    }

    public void setM_Rules(Rules m_Rules) {
        this.m_Rules = m_Rules;
    }

    public ETTTimeTable getM_ETTTimeTable() {
        return m_ETTTimeTable;
    }

    public void setM_ETTTimeTable(ETTTimeTable m_ETTTimeTable) {
        this.m_ETTTimeTable = m_ETTTimeTable;
    }
}
