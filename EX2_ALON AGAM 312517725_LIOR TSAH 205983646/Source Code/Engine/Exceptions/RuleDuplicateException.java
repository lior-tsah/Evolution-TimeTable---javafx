package Exceptions;

public class RuleDuplicateException extends Exception {

    private String m_messageError;

    public RuleDuplicateException(String ettRuleId) {
        m_messageError = "The rule: " + ettRuleId + " cannot be duplicate!";
    }

    @Override
    public String getMessage() {
        return this.m_messageError;
    }
}
