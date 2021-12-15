package Exceptions;

public class ClassesNumberingException extends Exception {

    private String m_messageError;

    public ClassesNumberingException() {
        this.m_messageError = "The numbering in EttClasses is not valid!";
    }

    @Override
    public String getMessage() {
        return this.m_messageError;
    }
}
