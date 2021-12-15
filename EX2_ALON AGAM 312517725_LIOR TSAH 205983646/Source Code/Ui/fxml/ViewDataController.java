package fxml;

import classes.Printer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ViewDataController {
    @FXML
    private Button loadDataButton;
    @FXML
    private TextArea viewDataTextArea;

    private static Scene prevScene;

    public static void setPrevScene(Scene prevScene) {
        ViewDataController.prevScene = prevScene;
    }

    @FXML
    void onClickLoadData() {
        StringBuilder fieldContent1 = new StringBuilder();
        Printer.printAll(LoadFileController.getModel().getDescriptor(), viewDataTextArea, fieldContent1);
        loadDataButton.setDisable(true);
    }

    @FXML
    void onClickCloseWindow(ActionEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(prevScene);
        window.show();
    }
}