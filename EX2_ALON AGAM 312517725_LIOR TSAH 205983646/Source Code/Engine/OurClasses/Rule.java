package OurClasses;


import genreatedClasses.ETTRule;

public class Rule {
    private ETTRule m_ETTRule;
    private String m_Type;
    private String m_RuleId;

    public Rule(ETTRule currentRule) {
        m_ETTRule = currentRule;
        m_Type = currentRule.getType();
        m_RuleId = currentRule.getETTRuleId();
    }

    public String getM_Type() {
        return m_Type;
    }

    public void setM_Type(String m_Type) {
        this.m_Type = m_Type;
    }

    public String getM_RuleId() {
        return m_RuleId;
    }

    public void setM_RuleId(String m_RuleId) {
        this.m_RuleId = m_RuleId;
    }

    public ETTRule getM_ETTRule() {
        return m_ETTRule;
    }

    public void setM_ETTRule(ETTRule m_ETTRule) {
        this.m_ETTRule = m_ETTRule;
    }
}
