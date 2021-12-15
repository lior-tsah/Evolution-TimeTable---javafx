package OurClasses;

import genreatedClasses.ETTSubject;
import genreatedClasses.ETTSubjects;

import java.util.ArrayList;
import java.util.List;

public class Subjects {
    private ETTSubjects m_ETTSubjects;
    private List<Subject> m_Subject;


    public Subjects(ETTSubjects ettSubjects) {
        m_ETTSubjects = ettSubjects;
        m_Subject = new ArrayList<>();
        for (ETTSubject currentSubject : m_ETTSubjects.getETTSubject()) {
            m_Subject.add(new Subject(currentSubject));
        }
    }

    public List<Subject> getM_Subject() {
        return m_Subject;
    }

    public void setM_Subject(List<Subject> m_Subject) {
        this.m_Subject = m_Subject;
    }

    public ETTSubjects getM_ETTSubjects() {
        return m_ETTSubjects;
    }

    public void setM_ETTSubjects(ETTSubjects m_ETTSubjects) {
        this.m_ETTSubjects = m_ETTSubjects;
    }
}
