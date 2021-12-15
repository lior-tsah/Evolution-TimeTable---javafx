package OurClasses;

import genreatedClasses.ETTClass;
import genreatedClasses.ETTSubject;
import genreatedClasses.ETTTeacher;

import java.util.Objects;

public class Fiver {
    private int D;
    private int H;
    private ETTClass C;
    private ETTTeacher T;
    private ETTSubject S;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fiver)) return false;
        Fiver fiver = (Fiver) o;
        return getD() == fiver.getD() && getH() == fiver.getH() && getC().equals(fiver.getC()) && getT().equals(fiver.getT()) && getS().equals(fiver.getS());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getD(), getH(), getC(), getT(), getS());
    }

    public int getD() {
        return D;
    }

    public void setD(int d) {
        D = d;
    }

    public int getH() {
        return H;
    }

    public void setH(int h) {
        H = h;
    }

    public ETTClass getC() {
        return C;
    }

    public void setC(ETTClass c) {
        C = c;
    }

    public ETTTeacher getT() {
        return T;
    }

    public void setT(ETTTeacher t) {
        T = t;
    }

    public ETTSubject getS() {
        return S;
    }

    public void setS(ETTSubject s) {
        S = s;
    }

    public Fiver(int d, int h, ETTClass c, ETTTeacher t, ETTSubject s) {
        D = d;
        H = h;
        C = c;
        T = t;
        S = s;
    }
}