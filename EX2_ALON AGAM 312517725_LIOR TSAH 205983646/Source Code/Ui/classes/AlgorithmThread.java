package classes;

import OurClasses.Descriptor;
import fxml.StartAlgorithmController;
import fxml.LoadFileController;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javax.swing.*;
import java.math.BigDecimal;

public class AlgorithmThread {
    private Stopwatch stopwatch = new Stopwatch();
    private BooleanProperty isPaused = new SimpleBooleanProperty(false);
    private MyRunnable myRunnable = null;
    private Thread algorithmThread;
    private Stage stage = new Stage();
    private int maxTime;
    private int maxFitness;
    private int maxGenerations;
    private static StartAlgorithmController startAlgorithmController;
    private BigDecimal timeProgress = new BigDecimal(String.format("%.2f", 0.0));
    private BigDecimal fitnessProgress = new BigDecimal(String.format("%.2f", 0.0));
    private BigDecimal generationsProgress = new BigDecimal(String.format("%.2f", 0.0));
    private boolean flg = true;
    private int prevFitness = 0;
    private ObservableList<GenerationProgress> elements = FXCollections.observableArrayList();

    public int getMaxTime() {
        return maxTime;
    }

    public boolean IsPaused() {
        return isPaused.get();
    }

    public BooleanProperty isPausedProperty() {
        return isPaused;
    }

    public void setIsPaused(boolean isPaused) {
        this.isPaused.set(isPaused);
    }

    public void setStartAlgorithmController(StartAlgorithmController startAlgorithmController) {
        this.startAlgorithmController = startAlgorithmController;
    }

    public int getMaxFitness() {
        return this.maxFitness;
    }


    public ObservableList<GenerationProgress> insertToTable(int fitnessGrade, int i) {
        elements.add(new GenerationProgress(i, fitnessGrade));
        return elements;
    }

    public void startAlgorithmUI(Descriptor descriptor, int maxGenerations, int maxFitness, int maxTime, int generationToShow) {
        descriptor.loadDescriptor();
        descriptor.getEvolutionEngine().startAlgorithm(descriptor);
        if ((int) startAlgorithmController.getGenerationToShowSlideBar().getValue() == 0) {
            generationToShow = Integer.MAX_VALUE;
        }

        try {
            for (int i = 0; i <= maxGenerations; i++) {
                Thread.sleep(1);
                descriptor.getEvolutionEngine().executeSelection(descriptor);
                descriptor.getEvolutionEngine().executeCrossover(descriptor);
                while (descriptor.getSolutions().getListOfSolutions().size() < descriptor.getEvolutionEngine().getM_InitialPopulation().getM_Size()) {
                    descriptor.getEvolutionEngine().executeMutations(descriptor);
                }
                descriptor.getSolutions().calculateGrades(descriptor);

                if (i % generationToShow == 0) {
                    if (i != 0) {
                        this.updateGenreationsProgressBar(generationToShow);
                    }
                    addDataToTable(i, descriptor);
                }

                checkConditions(i, descriptor);
                handlePauseClick();
            }

        } catch (InterruptedException e) {
            flg = true;
        }
    }

    private synchronized void handlePauseClick() {
        while (isPaused.getValue()) {
            try {
                JOptionPane.showMessageDialog(null, "The algorithm has been paused by user!!", "Pause Message Dialog", JOptionPane.INFORMATION_MESSAGE);
                this.wait();
            } catch (InterruptedException e) {
            }
        }
    }

    private void addDataToTable(int i, Descriptor descriptor) {
        startAlgorithmController.getTableView().setVisible(true);
        startAlgorithmController.getTableView().setItems(insertToTable(i, descriptor.getSolutions().getListOfSolutions().get(0).getFitnessGrade()));
        descriptor.getEvolutionEngine().checkDifference(i, descriptor.getSolutions().getListOfSolutions());
    }

    private void checkConditions(int i, Descriptor descriptor) throws InterruptedException {
        if (maxTime == stopwatch.getMins()) {
            JOptionPane.showMessageDialog(null, "Max Time Condition Ends!", "Time Condition Status", JOptionPane.INFORMATION_MESSAGE);
            handleStopButtonClick();
        } else if (i == maxGenerations) {
            startAlgorithmController.getGenerationsProgressBar().setProgress(1.0);
            JOptionPane.showMessageDialog(null, "Max Generation Condition Ends!", "Generation Condition Status", JOptionPane.INFORMATION_MESSAGE);
            handleStopButtonClick();
        } else if (descriptor.getSolutions().getListOfSolutions().get(0).getFitnessGrade() >= maxFitness) {
            JOptionPane.showMessageDialog(null, "Max Fitness Condition Ends!", "Fitness Condition Status", JOptionPane.INFORMATION_MESSAGE);
            handleStopButtonClick();
        }
    }


    public synchronized void handleStartButtonClick(Descriptor descriptor, ActionEvent event, int maxFitness
            , int maxGenerations, int maxTime, int generationToShow) {

        stopwatch.setD(this);
        this.maxTime = maxTime;
        this.maxFitness = maxFitness;
        this.maxGenerations = maxGenerations;
        if (((Button) event.getSource()).getText().equals("Start")) {
            generationsProgress = new BigDecimal(String.format("%.2f", 0.0));
            startAlgorithmController.getGenerationsProgressBar().setProgress(generationsProgress.doubleValue());
            startAlgorithmController.getFitnessProgressBar().setProgress(generationsProgress.doubleValue());
            startAlgorithmController.getTimeProgressBar().setProgress(generationsProgress.doubleValue());
            stopwatch.start(stage);
            if (flg) {
                myRunnable = new MyRunnable(descriptor, maxGenerations, maxFitness, maxTime, generationToShow, this);
                algorithmThread = new Thread(myRunnable);
                algorithmThread.setName("AlgorithmThread");
                algorithmThread.start();
                flg = false;
            }

        } else if (((Button) event.getSource()).getText().equals("Pause")) {
            this.setIsPaused(true);
            stopwatch.start(stage);

        } else if (((Button) event.getSource()).getText().equals("Resume")) {
            stopwatch.start(stage);
            this.setIsPaused(false);
            this.notifyAll();
        }

        ((Button) event.getSource()).setText((((Button) event.getSource()).getText().equals("Pause") ? "Resume" : "Pause"));
    }

    public void handleStopButtonClick() throws InterruptedException {
        elements = FXCollections.observableArrayList();

        if (startAlgorithmController != null) {
            startAlgorithmController.getStartButton().setDisable(true);
            startAlgorithmController.getStopButton().setDisable(true);
        }

        timeProgress = new BigDecimal(String.format("%.2f", 0.0));
        fitnessProgress = new BigDecimal(String.format("%.2f", 0.0));
        generationsProgress = new BigDecimal(String.format("%.2f", 0.0));
        prevFitness = 0;

        resetProgressesBar();

        stopwatch.stop();
        synchronized (this) {
            this.setIsPaused(false);
            this.notifyAll();
        }
        if (algorithmThread != null) {
            while (algorithmThread.isAlive()) {
                algorithmThread.interrupt();
                algorithmThread.join();
            }
        }
    }


    private void resetProgressesBar() {
        if (startAlgorithmController != null && !Thread.currentThread().getName().equals("AlgorithmThread")) {
            startAlgorithmController.getTimeProgressBar().setProgress(0);
        }
        if (startAlgorithmController != null && !Thread.currentThread().getName().equals("AlgorithmThread")) {
            startAlgorithmController.getFitnessProgressBar().setProgress(0);
        }
        if (startAlgorithmController != null && !Thread.currentThread().getName().equals("AlgorithmThread")) {
            startAlgorithmController.getGenerationsProgressBar().setProgress(0);
        }
    }

    public void updateTimeProgressBar() {
        if (timeProgress.doubleValue() < 1) {
            timeProgress = new BigDecimal(String.format("%.2f", timeProgress.doubleValue() + (1.0 / (LoadFileController.getData().getMaxTime() * 60.0))));
            startAlgorithmController.getTimeProgressBar().setProgress(timeProgress.doubleValue());
        }
    }

    public void updateFitnessProgressBar(int fitnessGrade) {
        if (fitnessProgress.doubleValue() < 1) {
            fitnessProgress = new BigDecimal(String.format("%.2f", fitnessProgress.doubleValue() + (double) (fitnessGrade - prevFitness) / maxFitness));
            prevFitness = fitnessGrade;
            startAlgorithmController.getFitnessProgressBar().setProgress(fitnessProgress.doubleValue());
        }
    }

    public void updateGenreationsProgressBar(int currentGeneration) throws InterruptedException {
        if (fitnessProgress.doubleValue() < 1) {
            generationsProgress = new BigDecimal(String.format("%.2f", generationsProgress.doubleValue() + (double) currentGeneration / maxGenerations));
            startAlgorithmController.getGenerationsProgressBar().setProgress(generationsProgress.doubleValue());
        }
    }
}