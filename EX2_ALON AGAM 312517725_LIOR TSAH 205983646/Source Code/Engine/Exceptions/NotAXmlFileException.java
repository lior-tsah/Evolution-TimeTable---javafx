package Exceptions;

public class NotAXmlFileException extends Exception {

    private String m_messageError;

    public NotAXmlFileException() {
        this.m_messageError = "This file is not a xml file!";
    }

    @Override
    public String getMessage() {
        return this.m_messageError;
    }
}
