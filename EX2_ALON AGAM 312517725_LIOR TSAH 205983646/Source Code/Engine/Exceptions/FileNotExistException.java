package Exceptions;

public class FileNotExistException extends Exception {

    private String m_messageError;

    public FileNotExistException() {
        this.m_messageError = "The file is not exist!";
    }

    @Override
    public String getMessage() {
        return this.m_messageError;
    }
}
