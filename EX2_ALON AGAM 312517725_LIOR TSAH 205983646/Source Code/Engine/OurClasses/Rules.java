package OurClasses;

import genreatedClasses.ETTRule;
import genreatedClasses.ETTRules;

import java.util.ArrayList;
import java.util.List;

public class Rules {
    private ETTRules m_ETTRules;
    private int m_HardRulesWeight;
    private List<Rule> m_Rule;

    public Rules(ETTRules ettRules) {
        m_ETTRules = ettRules;
        m_HardRulesWeight = m_ETTRules.getHardRulesWeight();
        m_Rule = new ArrayList<>();
        for (ETTRule currentRule : m_ETTRules.getETTRule()) {
            m_Rule.add(new Rule(currentRule));
        }
    }

    public int getM_HardRulesWeight() {
        return m_HardRulesWeight;
    }

    public void setM_HardRulesWeight(int m_HardRulesWeight) {
        this.m_HardRulesWeight = m_HardRulesWeight;
    }

    public List<Rule> getM_Rule() {
        return m_Rule;
    }

    public void setM_Rule(List<Rule> m_Rule) {
        this.m_Rule = m_Rule;
    }

    public ETTRules getM_ETTRules() {
        return m_ETTRules;
    }

    public void setM_ETTRules(ETTRules m_ETTRules) {
        this.m_ETTRules = m_ETTRules;
    }
}
