package OurClasses;

import genreatedClasses.ETTClass;

public class Class {
    private ETTClass m_ETTClass;
    private int m_Id;
    private String m_Name;
    private Requirements m_Requirements;


    public Class(ETTClass currentClass) {
        m_ETTClass = currentClass;
        m_Id = currentClass.getId();
        m_Name = currentClass.getETTName();
        m_Requirements = new Requirements(currentClass.getETTRequirements());
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

    public Requirements getM_Requirements() {
        return m_Requirements;
    }

    public void setM_Requirements(Requirements m_Requirements) {
        this.m_Requirements = m_Requirements;
    }

    public ETTClass getM_ETTClass() {
        return m_ETTClass;
    }

    public void setM_ETTClass(ETTClass m_ETTClass) {
        this.m_ETTClass = m_ETTClass;
    }
}
