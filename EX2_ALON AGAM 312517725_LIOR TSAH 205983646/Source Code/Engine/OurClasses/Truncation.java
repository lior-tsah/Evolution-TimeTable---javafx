package OurClasses;

import genreatedClasses.ETTRule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Truncation extends Selection {
    protected int topPercent;

    public int getTopPercent() {
        return topPercent;
    }

    public void setTopPercent(int topPercent) {
        this.topPercent = topPercent;
    }

    private void selectTopSolutions(Descriptor descriptor) {
        this.topPercent = Integer.parseInt(descriptor.getEttDescriptor().getETTEvolutionEngine().getETTSelection().getConfiguration().split("=")[1])
                * descriptor.getEttDescriptor().getETTEvolutionEngine().getETTInitialPopulation().getSize() / 100;
    }

    public void executeSelection(Descriptor descriptor) {
        listOfSolutions = new ArrayList<>();
        sortListOfSolutionsByFitness(descriptor);
        selectTopSolutions(descriptor);

        for (int i = 0; i < topPercent; i++) {
            listOfSolutions.add(descriptor.getSolutions().getListOfSolutions().get(i));
        }
        descriptor.getSolutions().setListOfSolutions(listOfSolutions);
    }
}