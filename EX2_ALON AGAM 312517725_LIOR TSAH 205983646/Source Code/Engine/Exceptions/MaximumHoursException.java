package Exceptions;

public class MaximumHoursException extends Exception {

    private String m_messageError;

    public MaximumHoursException(String ettName) {
        this.m_messageError = ettName + " is studying more hours than the maximum in a week!";
        ;
    }

    @Override
    public String getMessage() {
        return this.m_messageError;
    }
}