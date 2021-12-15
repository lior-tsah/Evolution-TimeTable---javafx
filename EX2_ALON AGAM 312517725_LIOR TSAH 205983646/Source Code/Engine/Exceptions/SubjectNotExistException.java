package Exceptions;

public class SubjectNotExistException extends Exception {

    private String m_messageError;

    public SubjectNotExistException(String ettName) {
        this.m_messageError = ettName + " is studying a subject that not exist!";
    }

    @Override
    public String getMessage() {
        return this.m_messageError;
    }
}