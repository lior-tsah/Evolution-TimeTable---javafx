package fxml;

import classes.AlgorithmThread;
import classes.GenerationProgress;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class StartAlgorithmController {
    @FXML
    private Label generationsLabel;
    @FXML
    private Label fitnessLabel;
    @FXML
    private ProgressBar generationsProgressBar;
    @FXML
    private ProgressBar fitnessProgressBar;
    @FXML
    private Label timeLabel;
    @FXML
    private Button StartButton;
    @FXML
    private Button stopButton;
    @FXML
    private Button bestSolutionButton;
    @FXML
    private Button settingButton;
    @FXML
    private ProgressBar timeProgressBar;
    @FXML
    private Slider generationToShowSlideBar;
    @FXML
    private TableView<GenerationProgress> tableView;

    private static LoadFileController mainController;
    private static AlgorithmThread algorithmThread = new AlgorithmThread();


    public ProgressBar getGenerationsProgressBar() {
        return generationsProgressBar;
    }

    public ProgressBar getFitnessProgressBar() {
        return fitnessProgressBar;
    }

    public ProgressBar getTimeProgressBar() {
        return timeProgressBar;
    }

    public static AlgorithmThread getData() {
        return algorithmThread;
    }

    public static void setData(AlgorithmThread thread) {
        algorithmThread = thread;
    }

    public Slider getGenerationToShowSlideBar() {
        return generationToShowSlideBar;
    }

    public static void setMainController(LoadFileController mainController) {
        StartAlgorithmController.mainController = mainController;
    }

    private static Scene prevScene;

    public static void setPrevScene(Scene prevScene) {
        StartAlgorithmController.prevScene = prevScene;
    }

    private void createTableViewColumns() {
        TableColumn<GenerationProgress, Integer> generationColumn = new TableColumn<>("Generation");
        generationColumn.setMinWidth(40);
        generationColumn.setCellValueFactory(new PropertyValueFactory<>("generation"));

        TableColumn<GenerationProgress, Integer> fitnessGradeColumn = new TableColumn<>("Best Fitness Grade");
        fitnessGradeColumn.setMinWidth(40);
        fitnessGradeColumn.setCellValueFactory(new PropertyValueFactory<>("fitnessGrade"));

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.getColumns().addAll(generationColumn, fitnessGradeColumn);
    }

    public TableView<GenerationProgress> getTableView() {
        return tableView;
    }

    @FXML
    void onClickStartButton(ActionEvent event) {
        if (((Button) event.getSource()).getText().equals("Start")) {
            handleStartClick();
        }
        checkAskedConditions(event);

        stopButton.setDisable(false);
        bestSolutionButton.setDisable(false);
        settingButton.setDisable(!((Button) event.getSource()).getText().equals("Resume"));
    }

    private void checkAskedConditions(ActionEvent event) {
        int maxFitness = (int) mainController.getFitnessSlideBar().getValue();
        int maxTime = (int) mainController.getTimeSlideBar().getValue();
        int maxGenerations = (int) mainController.getGenerationsSlideBar().getValue();
        if (maxTime == 0) {
            timeLabel.setVisible(false);
            timeProgressBar.setVisible(false);
        }
        if (maxFitness == 0) {
            fitnessLabel.setVisible(false);
            fitnessProgressBar.setVisible(false);
        }
        if (maxGenerations == 0) {
            generationsLabel.setVisible(false);
            generationsProgressBar.setVisible(false);
        }
        LoadFileController.getData().handleStartButtonClick(LoadFileController.getModel().getDescriptor(), event
                , maxFitness != 0 ? maxFitness : Integer.MAX_VALUE
                , maxGenerations != 0 ? maxGenerations : Integer.MAX_VALUE
                , maxTime != 0 ? maxTime : Integer.MAX_VALUE
                , (int) generationToShowSlideBar.getValue());
    }

    private void handleStartClick() {
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setId("my-table");
        tableView.getStylesheets().add("css/design.css");
        createTableViewColumns();
        tableView.setVisible(true);
        algorithmThread.setStartAlgorithmController(this);
    }

    @FXML
    void onClickStopButton() throws InterruptedException {
        LoadFileController.getData().handleStopButtonClick();
        settingButton.setDisable(true);
        StartButton.setText("Start");
    }

    @FXML
    void onClickBestSolution(ActionEvent event) throws IOException {
        Parent viewDataParent = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("bestSolution.fxml"))));
        Scene viewDataScene = new Scene(viewDataParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        BestSolutionController.setPrevScene(window.getScene());
        window.setTitle("Best Solution");
        window.setScene(viewDataScene);
        window.show();
    }

    @FXML
    void onClickCloseWindow(ActionEvent event) throws InterruptedException {
        tableView.setVisible(false);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(prevScene);
        LoadFileController.getData().handleStopButtonClick();
        this.timeProgressBar.setProgress(0);
        window.show();
    }

    @FXML
    void onClickSettingsButton(ActionEvent event) throws IOException {

        Parent viewDataParent = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("settings.fxml"))));
        Scene viewDataScene = new Scene(viewDataParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SettingsController.setPrevScene(window.getScene());
        SettingsController.setDescriptor(LoadFileController.getModel().getDescriptor());
        window.setTitle("Settings");
        window.setScene(viewDataScene);
        window.show();
    }

    public Button getStartButton() {
        return StartButton;
    }

    public Button getStopButton() {
        return stopButton;
    }
}