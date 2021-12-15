package OurClasses;

import java.util.Locale;

public class AspectOriented extends Crossover {
    private String component;

    public AspectOriented(int days, int hours, int cuttingPoints) {
        this.tableOfParentOne = new Fiver[days][hours];
        this.tableOfParentTwo = new Fiver[days][hours];
        this.tableOfCuttingPoints = new int[cuttingPoints];    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    @Override
    public void executeCrossover(Descriptor descriptor) {
        int indexOne = rnd.nextInt(descriptor.getSolutions().getListOfSolutions().size());
        int indexTwo = rnd.nextInt(descriptor.getSolutions().getListOfSolutions().size());
        Solution parentOne = descriptor.getSolutions().getListOfSolutions().get(indexOne);
        Solution parentTwo = descriptor.getSolutions().getListOfSolutions().get(indexTwo);

        component = descriptor.getEttDescriptor().getETTEvolutionEngine().getETTCrossover().getConfiguration().split("=")[1];
        component.toUpperCase(Locale.ROOT);
        sortParents(parentOne, parentTwo);
        insertFiversToTables(parentOne.getPossibleSolution(), parentTwo.getPossibleSolution());
        mixingParents();
        removeParents(indexOne, indexTwo, descriptor);
        parentOne = new Solution();
        parentTwo = new Solution();

        createOffspringFromParentOne(parentOne);
        descriptor.getSolutions().getListOfSolutions().add(parentOne);
        createOffspringFromParentTwo(parentTwo);
        descriptor.getSolutions().getListOfSolutions().add(parentTwo);
    }

    private void sortParents(Solution parentOne, Solution parentTwo) {
        if (component.equals("CLASS")) {
            parentOne.getPossibleSolution().sort((o1, o2) -> o2.getC().getId() - o1.getC().getId());
            parentTwo.getPossibleSolution().sort((o1, o2) -> o2.getC().getId() - o1.getC().getId());

        } else if (component.equals("TEACHER")) {
            parentOne.getPossibleSolution().sort((o1, o2) -> o2.getT().getId() - o1.getT().getId());
            parentTwo.getPossibleSolution().sort((o1, o2) -> o2.getT().getId() - o1.getT().getId());
        }
    }
}