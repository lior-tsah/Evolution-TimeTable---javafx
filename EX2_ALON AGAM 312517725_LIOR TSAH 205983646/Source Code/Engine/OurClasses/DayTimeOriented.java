package OurClasses;

import java.util.Comparator;

public class DayTimeOriented extends Crossover {

    public DayTimeOriented(int days, int hours, int cuttingPoints) {
        this.tableOfParentOne = new Fiver[days][hours];
        this.tableOfParentTwo = new Fiver[days][hours];
        this.tableOfCuttingPoints = new int[cuttingPoints];
    }

    @Override
    public void executeCrossover(Descriptor descriptor) {
        int indexOne = rnd.nextInt(descriptor.getSolutions().getListOfSolutions().size());
        int indexTwo = rnd.nextInt(descriptor.getSolutions().getListOfSolutions().size());
        Solution parentOne = descriptor.getSolutions().getListOfSolutions().get(indexOne);
        Solution parentTwo = descriptor.getSolutions().getListOfSolutions().get(indexTwo);

        parentOne.getPossibleSolution().sort(Comparator.comparing(Fiver::getD).thenComparing(Fiver::getH));
        parentTwo.getPossibleSolution().sort(Comparator.comparing(Fiver::getD).thenComparing(Fiver::getH));
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
}