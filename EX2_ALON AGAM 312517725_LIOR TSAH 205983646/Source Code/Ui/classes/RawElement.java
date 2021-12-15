package classes;

public class RawElement {
    private Integer day;
    private Integer hour;
    private String class_Name;
    private String teacher_Name;
    private String subject_Name;

    public RawElement(Integer d, Integer h, String className, String teacherName, String subjectName) {
        this.day = d;
        this.hour = h;
        this.class_Name = className;
        this.teacher_Name = teacherName;
        this.subject_Name = subjectName;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public String getClass_Name() {
        return class_Name;
    }

    public void setClass_Name(String class_Name) {
        this.class_Name = class_Name;
    }

    public String getTeacher_Name() {
        return teacher_Name;
    }

    public void setTeacher_Name(String teacher_Name) {
        this.teacher_Name = teacher_Name;
    }

    public String getSubject_Name() {
        return subject_Name;
    }

    public void setSubject_Name(String subject_Name) {
        this.subject_Name = subject_Name;
    }
}