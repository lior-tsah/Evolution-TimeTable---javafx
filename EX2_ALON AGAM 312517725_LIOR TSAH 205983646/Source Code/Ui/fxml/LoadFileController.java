package fxml;

import classes.AlgorithmThread;
import classes.Model;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoadFileController implements Initializable {

    @FXML
    private Button viewDataButton;
    @FXML
    private Slider generationsSlideBar;
    @FXML
    private Slider fitnessSlideBar;
    @FXML
    private Slider timeSlideBar;
    @FXML
    private Button startAlgorithmButton;
    @FXML
    private CheckBox generationsCheckBox;
    @FXML
    private CheckBox fitnessCheckBox;
    @FXML
    private CheckBox timeCheckBox;


    private Stage primaryStage;
    private static Model model;
    private BooleanProperty isPaused = new SimpleBooleanProperty(true);
    private static AlgorithmThread data = new AlgorithmThread();

    public boolean isIsPaused() {
        return isPaused.get();
    }

    public BooleanProperty isPausedProperty() {
        return isPaused;
    }

    public void setIsPaused(boolean isPaused) {
        this.isPaused.set(isPaused);
    }

    public static Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public static AlgorithmThread getData() {
        return data;
    }

    public Slider getGenerationsSlideBar() {
        return generationsSlideBar;
    }

    public Slider getFitnessSlideBar() {
        return fitnessSlideBar;
    }

    public Slider getTimeSlideBar() {
        return timeSlideBar;
    }

    @FXML
    void onClickLoadFile() {


        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select xml file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("xml files", "*.xml"));
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        try {
            model.getCheckXmlFile().checkFile(selectedFile, model.getDescriptor());
            viewDataButton.setDisable(false);
            generationsCheckBox.setDisable(false);
            fitnessCheckBox.setDisable(false);
            timeCheckBox.setDisable(false);
            JOptionPane.showMessageDialog(null, "The file has been loaded!", "The file has been loaded!", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @FXML
    void onClickExit() {
        Platform.exit();
        System.exit(0);
    }


    @FXML
    void onClickViewData(ActionEvent event) throws IOException {
        Parent viewDataParent = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("viewData.fxml"))));
        Scene viewDataScene = new Scene(viewDataParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ViewDataController.setPrevScene(window.getScene());
        window.setTitle("View Data");
        window.setScene(viewDataScene);
        window.show();
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    void onClickGenerationCheckBox() {
        if (generationsSlideBar.isDisabled()) {
            generationsSlideBar.setValue(0);
        }
    }

    @FXML
    void onClickFitnessCheckBox() {
        if (fitnessSlideBar.isDisabled()) {
            fitnessSlideBar.setValue(0);
        }
    }

    @FXML
    void onClickTimeCheckBox() {
        if (timeSlideBar.isDisabled()) {
            timeSlideBar.setValue(0);
        }
    }

    @FXML
    void onClickStartAlgorithm(ActionEvent event) throws IOException {
        Parent viewDataParent = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("startAlgorithm.fxml"))));
        Scene viewDataScene = new Scene(viewDataParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        StartAlgorithmController.setPrevScene(window.getScene());
        window.setTitle("Start Algorithm");
        window.setScene(viewDataScene);
        StartAlgorithmController.setMainController(this);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fitnessSlideBar.disableProperty().bind(fitnessCheckBox.selectedProperty().not());
        timeSlideBar.disableProperty().bind(timeCheckBox.selectedProperty().not());
        generationsSlideBar.disableProperty().bind(generationsCheckBox.selectedProperty().not());
        startAlgorithmButton.disableProperty().bind
                ((generationsSlideBar.disableProperty()).or(generationsSlideBar.valueProperty().greaterThan(0).not())
                        .and(fitnessSlideBar.disableProperty().or(fitnessSlideBar.valueProperty().greaterThan(0).not()).and((timeSlideBar.disableProperty().or(timeSlideBar.valueProperty().greaterThan(0).not())))));
    }
}