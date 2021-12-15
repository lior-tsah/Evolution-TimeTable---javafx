package OurClasses;

public class BestGrades {

    protected int grade;
    protected int difference;

    public BestGrades(int grade, int difference) {
        this.grade = grade;
        this.difference = difference;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getDifference() {
        return difference;
    }

    public void setDifference(int difference) {
        this.difference = difference;
    }
}

