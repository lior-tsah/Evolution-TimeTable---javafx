package Exceptions;

public class ElitismSizeException extends Exception {

    private String m_messageError;

    public ElitismSizeException() {
        this.m_messageError = "The size of the elitism is bigger then the size of the initial population!";
    }

    @Override
    public String getMessage() {
        return this.m_messageError;
    }
}
