package classes;

import OurClasses.Descriptor;

public class MyRunnable implements Runnable {
    Descriptor descriptor;
    private int maxGenerations;
    private int generationToShow;
    private int maxTime;
    private int maxFitness;
    private AlgorithmThread algorithmThread;


    public MyRunnable(Descriptor descriptor, int numOfGenerations, int maxFitness, int maxTime, int generationToShow, AlgorithmThread algorithmThread) {
        this.descriptor = descriptor;
        this.maxGenerations = numOfGenerations;
        this.generationToShow = generationToShow;
        this.maxTime = maxTime;
        this.maxFitness = maxFitness;
        this.algorithmThread = algorithmThread;
    }

    @Override
    public void run() {
        algorithmThread.startAlgorithmUI(descriptor, maxGenerations, maxFitness, maxTime, generationToShow);
    }
}