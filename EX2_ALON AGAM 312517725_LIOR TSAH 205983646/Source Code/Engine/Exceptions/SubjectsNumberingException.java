package Exceptions;

public class SubjectsNumberingException extends Exception {

    private String m_messageError;

    public SubjectsNumberingException() {
        this.m_messageError = "The numbering in EttSubjects is not valid!";
    }

    @Override
    public String getMessage() {
        return this.m_messageError;
    }
}
