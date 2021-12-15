package fxml;

import OurClasses.Fiver;
import OurClasses.Solution;
import classes.Model;
import classes.RawElement;
import genreatedClasses.ETTClass;
import genreatedClasses.ETTTeacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;

public class BestSolutionController implements Initializable {

    @FXML
    private ListView listView;
    @FXML
    private GridPane gridPane;
    @FXML
    private BorderPane borderPane;
    @FXML
    private TableView<RawElement> table;

    private static Model model = LoadFileController.getModel();
    private static Scene prevScene;

    private String currentValue;

    public static void setPrevScene(Scene prevScene) {
        BestSolutionController.prevScene = prevScene;
    }

    @FXML
    void onClickClassButton() {
        listView.setVisible(true);
        table.setVisible(false);
        int size = listView.getItems().size();
        if (size > 0) {
            listView.getItems().subList(0, size).clear();
        }

        gridPane.getChildren().clear();
        for (ETTClass currenClass : model.getDescriptor().getEttDescriptor().getETTTimeTable().getETTClasses().getETTClass()) {
            listView.getItems().add(currenClass.getETTName());
        }
    }

    private void printByClass(ETTClass ettClass, TimeTablePrintController timeTablePrintController) {
        timeTablePrintController.printClass(ettClass, model.getDescriptor());
    }

    @FXML
    void onClickTeacherButton() {
        table.setVisible(false);
        listView.setVisible(true);
        int size = listView.getItems().size();
        if (size > 0) {
            listView.getItems().subList(0, size).clear();
        }

        gridPane.getChildren().clear();

        for (ETTTeacher currentTeacher : model.getDescriptor().getEttDescriptor().getETTTimeTable().getETTTeachers().getETTTeacher()) {
            listView.getItems().add(currentTeacher.getETTName());
        }
    }

    private void printByTeacher(ETTTeacher ettTeacher, TimeTablePrintController timeTablePrintController) {
        timeTablePrintController.printTeacher(ettTeacher, model.getDescriptor());
    }

    @FXML
    void onClickRawButton() {
        table = new TableView<>();
        table.setId("my-table");
        table.getStylesheets().add("css/design.css");
        borderPane.requestFocus();
        listView.setVisible(false);
        table.setVisible(true);
        int size = listView.getItems().size();
        if (size > 0) {
            listView.getItems().subList(0, size).clear();
        }

        createTableView();

    }

    private void createTableView() {
        sortBestSolution();
        createTableViewColumns();
        table.setItems(getAllFivers());
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        borderPane.setCenter(table);
        borderPane.setAlignment(table, Pos.CENTER);
    }


    private void createTableViewColumns() {
        TableColumn<RawElement, Integer> dayColumn = new TableColumn<>("Day");
        dayColumn.setMinWidth(40);
        dayColumn.setCellValueFactory(new PropertyValueFactory<>("day"));

        TableColumn<RawElement, Integer> hourColumn = new TableColumn<>("Hour");
        hourColumn.setMinWidth(40);
        hourColumn.setCellValueFactory(new PropertyValueFactory<>("hour"));

        TableColumn<RawElement, String> classColumn = new TableColumn<>("Class");
        classColumn.setMinWidth(40);
        classColumn.setCellValueFactory(new PropertyValueFactory<>("class_Name"));

        TableColumn<RawElement, String> teacherColumn = new TableColumn<>("Teacher");
        teacherColumn.setMinWidth(40);
        teacherColumn.setCellValueFactory(new PropertyValueFactory<>("teacher_Name"));

        TableColumn<RawElement, String> subjectColumn = new TableColumn<>("Subject");
        subjectColumn.setMinWidth(40);
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject_Name"));

        table.getColumns().addAll(dayColumn, hourColumn, classColumn, teacherColumn, subjectColumn);
    }

    private void sortBestSolution() {
        Solution bestSolution = model.getDescriptor().getSolutions().getListOfSolutions().get(0);
        bestSolution.getPossibleSolution().sort(Comparator.comparing(Fiver::getD).thenComparing(Fiver::getH));
    }

    public ObservableList<RawElement> getAllFivers() {
        ObservableList<RawElement> elements = FXCollections.observableArrayList();
        for (Fiver currentFiver : model.getDescriptor().getSolutions().getListOfSolutions().get(0).getPossibleSolution()) {
            elements.add(new RawElement(currentFiver.getD(), currentFiver.getH(), currentFiver.getC().getETTName(), currentFiver.getT().getETTName(), currentFiver.getS().getName()));
        }
        return elements;
    }


    @FXML
    void onClickCloseWindow(ActionEvent event) {
        listView.getItems().clear();
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(prevScene);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            TimeTablePrintController timeTablePrintController = new TimeTablePrintController();

            if (newValue != null) {
                currentValue = newValue.toString();
                gridPane.getChildren().clear();
                Optional<ETTClass> currentClass = model.getDescriptor().getEttDescriptor().getETTTimeTable().getETTClasses().getETTClass().stream().
                        filter(pp -> pp.getETTName().equals(currentValue)).
                        findFirst();
                if (!currentClass.toString().equals("Optional.empty")) {
                    printByClass(currentClass.get(), timeTablePrintController);

                } else {
                    Optional<ETTTeacher> currentTeacher = model.getDescriptor().getEttDescriptor().getETTTimeTable().getETTTeachers().getETTTeacher().stream().
                            filter(pp -> pp.getETTName().equals(currentValue)).
                            findFirst();
                    printByTeacher(currentTeacher.get(), timeTablePrintController);
                }
            }
        });
    }
}