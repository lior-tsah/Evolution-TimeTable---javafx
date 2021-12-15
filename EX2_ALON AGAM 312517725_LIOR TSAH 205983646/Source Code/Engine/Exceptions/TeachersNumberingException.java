package Exceptions;

public class TeachersNumberingException extends Exception {

    private String m_messageError;

    public TeachersNumberingException() {
        this.m_messageError = "The numbering in EttTeachers is not valid!";
    }

    @Override
    public String getMessage() {
        return this.m_messageError;
    }
}
