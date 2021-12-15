package OurClasses;

import java.util.ArrayList;
import java.util.List;

public class RouletteWheel extends Selection {
    @Override
    public void executeSelection(Descriptor descriptor) {
        sortListOfSolutionsByFitness(descriptor);
        createWheelByFitnessGrade(descriptor);
        startRouletteWheelSelection(descriptor);
    }

    private void createWheelByFitnessGrade(Descriptor descriptor) {
        int sum = 0;
        for (Solution currentSolution : descriptor.getSolutions().getListOfSolutions()) {
            sum += currentSolution.getFitnessGrade();
            //listOfSolutions.add(currentSolution);
        }

        for (Solution currentSolution : descriptor.getSolutions().getListOfSolutions()) {
            currentSolution.setProbability((double) currentSolution.getFitnessGrade() / sum);
        }
    }

    private void startRouletteWheelSelection(Descriptor descriptor) {
        double sum = 0;
        int size = listOfSolutions.size();

        for (int i = 0; i < descriptor.getEttDescriptor().getETTEvolutionEngine().getETTInitialPopulation().getSize() / 2 - size; i++) {
            for (int j = 0; j < 2; j++) {
                double num = Math.random();
                while (sum < num) {
                    sum += descriptor.getSolutions().getListOfSolutions().get(i).getProbability();
                }
                listOfSolutions.add(descriptor.getSolutions().getListOfSolutions().get(i));
            }
            sum = 0;
        }
    }
}