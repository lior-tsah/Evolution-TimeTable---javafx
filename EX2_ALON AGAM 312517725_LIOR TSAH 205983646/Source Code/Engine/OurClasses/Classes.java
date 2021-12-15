package OurClasses;

import genreatedClasses.ETTClass;
import genreatedClasses.ETTClasses;

import java.util.ArrayList;
import java.util.List;

public class Classes {
    private ETTClasses m_ETTClasses;
    private List<Class> m_Class;

    public Classes(ETTClasses ettClasses) {
        m_ETTClasses = ettClasses;
        m_Class = new ArrayList<>();
        for (ETTClass currentClass : m_ETTClasses.getETTClass()) {
            m_Class.add(new Class(currentClass));
        }
    }

    public List<Class> getM_Class() {
        return m_Class;
    }

    public void setM_Class(List<Class> m_Class) {
        this.m_Class = m_Class;
    }

    public ETTClasses getM_ETTClasses() {
        return m_ETTClasses;
    }

    public void setM_ETTClasses(ETTClasses m_ETTClasses) {
        this.m_ETTClasses = m_ETTClasses;
    }
}
