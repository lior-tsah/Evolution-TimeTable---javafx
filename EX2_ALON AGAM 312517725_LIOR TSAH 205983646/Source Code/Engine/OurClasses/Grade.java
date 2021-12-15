package OurClasses;

import genreatedClasses.ETTRule;

import java.util.HashMap;
import java.util.Map;

public class Grade {
    private HashMap<ETTRule, Integer> listOfGradesByRule;
    private double averageSoftRules = 0.0;
    private double averageHardRules = 0.0;

    public Grade() {
        listOfGradesByRule = new HashMap<>();
    }


    public HashMap<ETTRule, Integer> getListOfGradesByRule() {
        return listOfGradesByRule;
    }


    public int calculateAveragesGradesOfSolutionByHardRuleWeight(int hardRulesWeight) {
        int countSoft = 0, countHard = 0;
        for (Map.Entry<ETTRule, Integer> entry : listOfGradesByRule.entrySet()) {
            ETTRule key = entry.getKey();
            Integer value = entry.getValue();

            if (key.getType().equals("Hard")) {
                averageHardRules += value;
                countHard++;
            } else {
                averageSoftRules += value;
                countSoft++;
            }
        }
        averageHardRules /= countHard;
        averageSoftRules /= countSoft;

        int fitness = (int) (averageHardRules * (hardRulesWeight / 100.0) + averageSoftRules * ((100 - hardRulesWeight) / 100.0));
        return fitness;
    }

    public void setListOfGradesByRule(HashMap<ETTRule, Integer> listOfGradesByRule) {
        this.listOfGradesByRule = listOfGradesByRule;
    }

    public double getAverageSoftRules() {
        return averageSoftRules;
    }

    public void setAverageSoftRules(double averageSoftRules) {
        this.averageSoftRules = averageSoftRules;
    }

    public double getAverageHardRules() {
        return averageHardRules;
    }

    public void setAverageHardRules(double averageHardRules) {
        this.averageHardRules = averageHardRules;
    }
}