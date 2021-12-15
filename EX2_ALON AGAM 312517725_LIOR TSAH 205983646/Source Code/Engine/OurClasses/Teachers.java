package OurClasses;

import genreatedClasses.ETTTeacher;
import genreatedClasses.ETTTeachers;

import java.util.ArrayList;
import java.util.List;

public class Teachers {
    private ETTTeachers m_ETTTeachers;
    protected List<Teacher> m_Teacher;

    public Teachers(ETTTeachers ettTeachers) {
        m_ETTTeachers = ettTeachers;
        m_Teacher = new ArrayList<>();
        for (ETTTeacher currentTeacher : m_ETTTeachers.getETTTeacher()) {
            m_Teacher.add(new Teacher(currentTeacher));
        }
    }

    public List<Teacher> getM_Teacher() {
        return m_Teacher;
    }

    public void setM_Teacher(List<Teacher> m_Teacher) {
        this.m_Teacher = m_Teacher;
    }

    public ETTTeachers getM_ETTTeachers() {
        return m_ETTTeachers;
    }

    public void setM_ETTTeachers(ETTTeachers m_ETTTeachers) {
        this.m_ETTTeachers = m_ETTTeachers;
    }
}
