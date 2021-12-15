package Exceptions;

public class KnowledgeOfTeacherException extends Exception {

    private String m_messageError;

    public KnowledgeOfTeacherException(String ettName) {
        this.m_messageError = ettName + " is teaching a subject that not exist!";
    }

    @Override
    public String getMessage() {
        return this.m_messageError;
    }
}
