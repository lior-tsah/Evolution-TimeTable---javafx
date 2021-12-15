package OurClasses;

import genreatedClasses.ETTCrossover;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class Crossover {
    protected ETTCrossover m_ETTCrossover;
    protected Random rnd = new Random();
    protected Fiver[][] tableOfParentOne;
    protected Fiver[][] tableOfParentTwo;
    protected int[] tableOfCuttingPoints;
    protected String name;
    protected int cuttingPoints;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCuttingPoints() {
        return cuttingPoints;
    }

    public void setCuttingPoints(int cuttingPoints) {
        this.cuttingPoints = cuttingPoints;
    }

    public ETTCrossover getM_ETTCrossover() {
        return m_ETTCrossover;
    }

    public void setM_ETTCrossover(ETTCrossover m_ETTCrossover) {
        this.m_ETTCrossover = m_ETTCrossover;
    }

    public abstract void executeCrossover(Descriptor descriptor);

    protected void insertFiversToTables(List<Fiver> listOne, List<Fiver> listTwo) {

        for (Fiver currentFiver : listOne) {
            if (currentFiver.getD() - 1 < 6)
                tableOfParentOne[currentFiver.getD() - 1][currentFiver.getH() - 1] = currentFiver;

        }

        for (Fiver currentFiver : listTwo) {
            if (currentFiver.getD() - 1 < 6)
                tableOfParentTwo[currentFiver.getD() - 1][currentFiver.getH() - 1] = currentFiver;

        }
    }


    protected void removeParents(int indexOne, int indexTwo, Descriptor descriptor) {
        descriptor.getSolutions().getListOfSolutions().remove(indexOne);
        if (indexTwo == 0) {
            descriptor.getSolutions().getListOfSolutions().remove(indexTwo);
        } else {
            descriptor.getSolutions().getListOfSolutions().remove(indexTwo - 1);
        }

    }

    protected void createOffspringFromParentOne(Solution parentOne) {
        for (Fiver[] elementsArray : tableOfParentOne) {
            for (Fiver element : elementsArray) {
                if (element != null) {
                    parentOne.getPossibleSolution().add(element);
                }
            }
        }
    }

    protected void createOffspringFromParentTwo(Solution parentTwo) {

        for (Fiver[] elementsArray : tableOfParentTwo) {
            for (Fiver element : elementsArray) {
                if (element != null) {
                    parentTwo.getPossibleSolution().add(element);
                }
            }
        }
    }

    protected void mixingParents() {
        for (int i = 0; i < tableOfCuttingPoints.length; i++) {
            tableOfCuttingPoints[i] = rnd.nextInt(tableOfParentOne.length);
        }
        Arrays.sort(tableOfCuttingPoints);
        swapParents();

    }

    protected void swapParents() {
        Fiver[] temp;

        for (int i = 0; i < tableOfCuttingPoints.length - 1; i += 2) {
            for (int j = tableOfCuttingPoints[i]; j < tableOfCuttingPoints[i + 1]; j++) {
                temp = tableOfParentOne[j];
                tableOfParentOne[j] = tableOfParentTwo[j];
                tableOfParentTwo[j] = temp;
            }
        }
    }
}
