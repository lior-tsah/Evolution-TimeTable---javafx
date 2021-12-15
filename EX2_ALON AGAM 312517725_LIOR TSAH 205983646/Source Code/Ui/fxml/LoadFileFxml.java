package fxml;

import classes.Model;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;

public class LoadFileFxml extends Application {

    @Override
    public void start(Stage primaryStage) {
        Model model = new Model();

        primaryStage.setTitle("Evolutionary Time Table");
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL url = getClass().getResource("loadFile.fxml");
        fxmlLoader.setLocation(url);
        Parent root = null;
        try {
            assert url != null;
            root = fxmlLoader.load(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        LoadFileController loadFileController = fxmlLoader.getController();
        loadFileController.setModel(model);
        assert root != null;
        Scene scene = new Scene(root, 1000, 537);
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(event -> {
            try {
                loadFileController.getData().handleStopButtonClick();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}