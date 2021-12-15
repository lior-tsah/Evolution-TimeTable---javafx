package fxml;

import OurClasses.Descriptor;
import genreatedClasses.ETTClass;
import genreatedClasses.ETTTeacher;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

public class TimeTablePrintController {
    private TextArea tf = new TextArea();
    private GridPane root = new GridPane();
    private Stage primaryStage = new Stage();
    private BorderPane borderPane = new BorderPane();
    private Button button = new Button("Close Window");
    private TextArea textArea = new TextArea();


    public void printTeacher(ETTTeacher ettTeacher, Descriptor descriptor) {
        initialTimeTable(descriptor, root);
        insertTeacherToTimeTable(ettTeacher, descriptor, root);
        addNodesToBorderPane(descriptor);
        primaryStage.setTitle("Time Table Of " + ettTeacher.getETTName());
        borderPane.setStyle("-fx-background-color: Beige;");
    }

    private void addNodesToBorderPane(Descriptor descriptor) {
        primaryStage = new Stage();
        StringBuilder stringBuilder = new StringBuilder();
        Scene scene = new Scene(borderPane, (descriptor.getEttDescriptor().getETTTimeTable().getDays() + 1) * 265, (descriptor.getEttDescriptor().getETTTimeTable().getHours() + 1) * 110);
        primaryStage.setScene(scene);
        root.requestFocus();
        root.setPrefWidth((descriptor.getEttDescriptor().getETTTimeTable().getDays() + 1) * 220);
        borderPane.setCenter(root);
        button.setOnAction(event -> primaryStage.close());
        borderPane.setBottom(button);
        textArea.setMaxWidth(200);
        textArea.setPrefHeight((descriptor.getEttDescriptor().getETTTimeTable().getHours() + 1) * 100);
        textArea.setMaxHeight((descriptor.getEttDescriptor().getETTTimeTable().getHours() + 1) * 100);
        borderPane.setRight(textArea);
        button.setOnAction(event -> primaryStage.close());
        borderPane.setAlignment(button, Pos.CENTER);
        borderPane.setAlignment(root, Pos.CENTER);
        borderPane.requestFocus();
        descriptor.getSolutions().getListOfSolutions().get(0).getGrade().getListOfGradesByRule().forEach((k, v) -> stringBuilder.append("Name:").append(k.getETTRuleId()).append("\nGrade:").append(v).append("\n").append((k.getETTRuleId().equals("Sequentiality") ? "Total Hours:" + k.getETTConfiguration().split("=")[1]+"\n\n\n" : "\n\n")));
        textArea.setText(stringBuilder.toString());
        textArea.getStylesheets().add("css/design.css");
        button.getStylesheets().add("css/design.css");
        textArea.setStyle("-fx-control-inner-background:#9d4024; -fx-font-family: 'Arial Rounded MT Bold'; -fx-highlight-fill: #FFFFE0; -fx-highlight-text-fill: #000000; -fx-text-fill: #FFFFE0; ");
        primaryStage.show();
    }


    private void insertTeacherToTimeTable(ETTTeacher ettTeacher, Descriptor descriptor, GridPane root) {
        Set<OurClasses.Fiver> fiverSet = new HashSet<>();
        for (OurClasses.Fiver currentFiver : descriptor.getSolutions().getListOfSolutions().get(0).getPossibleSolution()) {
            if (currentFiver.getT().equals(ettTeacher)) {
                fiverSet.add(currentFiver);
            }
        }

        OurClasses.Fiver[] arr = new OurClasses.Fiver[fiverSet.size()];
        fiverSet.toArray(arr);
        for (int i = 0; i < fiverSet.size(); i++) {
            tf = new TextArea();
            tf.setPrefWidth(220);
            tf.setPrefHeight(100);
            tf.setMaxHeight(100);
            tf.setEditable(false);
            tf.setStyle("-fx-control-inner-background:#9d4024; -fx-font-family: 'Arial Rounded MT Bold'; -fx-highlight-fill: #FFFFE0; -fx-highlight-text-fill: #000000; -fx-text-fill: #FFFFE0; ");
            tf.setText("Class id:" + arr[i].getC().getId() + "\n" + "Class Name:" + arr[i].getC().getETTName() + "\n" + "Subject id:" + arr[i].getS().getId() + "\n" + "Subject Name:" + arr[i].getS().getName());
            root.setRowIndex(tf, arr[i].getH());
            root.setColumnIndex(tf, arr[i].getD());
            root.getChildren().add(tf);
        }
    }

    public void printClass(ETTClass ettClass, Descriptor descriptor) {
        initialTimeTable(descriptor, root);
        insertClassToTimeTable(ettClass, descriptor, root);
        addNodesToBorderPane(descriptor);
        primaryStage.setTitle("Time Table Of " + ettClass.getETTName() + " Class");
        borderPane.setStyle("-fx-background-color: Beige;");
    }

    private void initialTimeTable(Descriptor descriptor, GridPane root) {
        int length = descriptor.getEttDescriptor().getETTTimeTable().getHours();
        int width = descriptor.getEttDescriptor().getETTTimeTable().getDays();
        createDaysInTimeTable(root, width);
        createHoursInTimeTable(root, length);
        createTimeTable(root, length, width);
    }

    private void insertClassToTimeTable(ETTClass ettClass, Descriptor descriptor, GridPane root) {
        Set<OurClasses.Fiver> fiverSet = new HashSet<>();
        for (OurClasses.Fiver currentFiver : descriptor.getSolutions().getListOfSolutions().get(0).getPossibleSolution()) {
            if (currentFiver.getC().equals(ettClass)) {
                fiverSet.add(currentFiver);
            }
        }
        OurClasses.Fiver[] arr = new OurClasses.Fiver[fiverSet.size()];
        fiverSet.toArray(arr);
        for (int i = 0; i < fiverSet.size(); i++) {
            tf = new TextArea();
            tf.setPrefWidth(240);
            tf.setPrefHeight(100);
            tf.setMaxHeight(100);
            tf.setEditable(false);
            tf.setStyle("-fx-control-inner-background:#9d4024; -fx-font-family: 'Arial Rounded MT Bold'; -fx-highlight-fill: #FFFFE0; -fx-highlight-text-fill: #000000; -fx-text-fill: #FFFFE0; ");

            tf.setText("Teacher id:" + arr[i].getT().getId() + "\n" + "Teacher Name:" + arr[i].getT().getETTName() + "\n" + "Subject id:" + arr[i].getS().getId() + "\n" + "Subject Name:" + arr[i].getS().getName());
            root.setRowIndex(tf, arr[i].getH());
            root.setColumnIndex(tf, arr[i].getD());
            root.getChildren().add(tf);
        }
    }


    private void createTimeTable(GridPane root, int length, int width) {
        for (int y = 1; y <= length; y++) {
            for (int x = 1; x <= width; x++) {
                tf = new TextArea();
                tf.setPrefWidth(180);
                tf.setPrefHeight(100);
                tf.setMaxHeight(100);
                tf.setText("");
                tf.setEditable(false);
                tf.setStyle("-fx-control-inner-background:#9d4024; -fx-font-family: 'Arial Rounded MT Bold'; -fx-highlight-fill: #FFFFE0; -fx-highlight-text-fill: #000000; -fx-text-fill: #FFFFE0; ");
                root.setRowIndex(tf, y);
                root.setColumnIndex(tf, x);
                root.getChildren().add(tf);
            }
        }
    }

    private void createHoursInTimeTable(GridPane root, int length) {
        for (int x = 0; x < length; x++) {
            tf = new TextArea();
            tf.setPrefWidth(180);
            tf.setPrefHeight(100);
            tf.setMaxHeight(100);
            tf.setText(String.valueOf(x + 1));
            tf.setStyle("-fx-control-inner-background:#9d4024; -fx-font-family: 'Arial Rounded MT Bold'; -fx-highlight-fill: #FFFFE0; -fx-highlight-text-fill: #000000; -fx-text-fill: #FFFFE0; ");
            tf.setEditable(false);
            root.setRowIndex(tf, x + 1);
            root.setColumnIndex(tf, 0);
            root.getChildren().add(tf);
        }
    }

    private void createDaysInTimeTable(GridPane root, int width) {
        for (int x = 0; x <= width; x++) {
            tf = new TextArea();
            tf.setPrefWidth(180);
            tf.setPrefHeight(100);
            tf.setMaxHeight(100);

            if (x == 0) {
                tf.setText("Hours/Days");
            } else {
                tf.setText(String.valueOf(x));
            }
            tf.setStyle("-fx-control-inner-background:#9d4024; -fx-font-family: 'Arial Rounded MT Bold'; -fx-highlight-fill: #FFFFE0; -fx-highlight-text-fill: #000000; -fx-text-fill: #FFFFE0; ");
            tf.setEditable(false);
            root.setRowIndex(tf, 0);
            root.setColumnIndex(tf, x);
            root.getChildren().add(tf);
        }
    }
}