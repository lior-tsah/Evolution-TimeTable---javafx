package fxml;

import OurClasses.*;
import genreatedClasses.ETTMutation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    @FXML
    private MenuItem truncationMenuItem;
    @FXML
    private MenuItem rouletteWheelMenuItem;
    @FXML
    private Label aspectLabel;
    @FXML
    private Label componentLabel;
    @FXML
    private Button applySettingsButton;
    @FXML
    private MenuItem aspectOrientedMenuItem;
    @FXML
    private MenuItem dayTimeOrientedMenuItem;
    @FXML
    private CheckBox selectionTypeCheckBox;
    @FXML
    private MenuButton selectionTypeMenu;
    @FXML
    private CheckBox crossoverTypeCheckBox;
    @FXML
    private MenuButton crossoverTypeMenu;
    @FXML
    private CheckBox mutationTypeCheckBox;
    @FXML
    private MenuButton mutationTypeMenu;
    @FXML
    private Label topPercentLabel;
    @FXML
    private Slider topPercentSlideBar;
    @FXML
    private Label elitismLabel;
    @FXML
    private Slider elitismSlideBar;
    @FXML
    private Label cuttingPointsLabel;
    @FXML
    private Slider cuttingPointsSlideBar;
    @FXML
    private MenuButton aspectMenu;
    @FXML
    private Label probabilityLabel;
    @FXML
    private Slider probabilitySlideBar;
    @FXML
    private MenuButton componentMenu;
    @FXML
    private Label maxTupplesLabel;
    @FXML
    private Slider maxTupplesSlideBar;
    @FXML
    private Label totalTupplesLabel;
    @FXML
    private Slider totalTupplesSlideBar;
    private static Scene prevScene;
    private static Descriptor descriptor;

    public static Descriptor getDescriptor() {
        return descriptor;
    }

    public static void setDescriptor(Descriptor descriptor) {
        SettingsController.descriptor = descriptor;
    }

    public Scene getPrevScene() {
        return SettingsController.prevScene;
    }

    public static void setPrevScene(Scene prevScene) {
        SettingsController.prevScene = prevScene;
    }

    @FXML
    public void onClickBackWindow(javafx.event.ActionEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(prevScene);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectionTypeMenu.disableProperty().bind(selectionTypeCheckBox.selectedProperty().not());
        crossoverTypeMenu.disableProperty().bind(crossoverTypeCheckBox.selectedProperty().not());
        mutationTypeMenu.disableProperty().bind(mutationTypeCheckBox.selectedProperty().not());

        applySettingsButton.disableProperty().bind
                ((mutationTypeCheckBox.selectedProperty().not())
                        .and(crossoverTypeCheckBox.selectedProperty().not())
                        .and(selectionTypeCheckBox.selectedProperty().not()));
    }

    @FXML
    public void truncationChooseAction() {
        selectionTypeMenu.setText("Truncation");
        topPercentLabel.setVisible(true);
        topPercentSlideBar.setVisible(true);
        elitismLabel.setVisible(true);
        elitismSlideBar.setVisible(true);
    }

    @FXML
    public void rouletteWheelChooseAction() {
        selectionTypeMenu.setText("Roulette Wheel");
        topPercentLabel.setVisible(false);
        topPercentSlideBar.setVisible(false);
        elitismLabel.setVisible(true);
        elitismSlideBar.setVisible(true);
    }

    @FXML
    public void aspectOrientedChooseAction() {
        crossoverTypeMenu.setText("Aspect Oriented");
        aspectMenu.setVisible(true);
        cuttingPointsLabel.setVisible(true);
        cuttingPointsSlideBar.setVisible(true);
        aspectLabel.setVisible(true);
    }

    @FXML
    public void teacherAspectChooseAction() {aspectMenu.setText("TEACHER");}

    @FXML
    public void classAspectChooseAction() {
        aspectMenu.setText("CLASS");
    }

    @FXML
    public void classComponentChooseAction() {
        componentMenu.setText("C");
    }

    @FXML
    public void teacherComponentChooseAction() {
        componentMenu.setText("T");
    }

    @FXML
    public void subjectComponentChooseAction() {
        componentMenu.setText("S");
    }

    @FXML
    public void hourComponentChooseAction() {
        componentMenu.setText("H");
    }

    @FXML
    public void dayComponentChooseAction() {
        componentMenu.setText("D");
    }

    @FXML
    public void flippingChooseAction() {
        mutationTypeMenu.setText("Flipping");
        this.maxTupplesLabel.setVisible(true);
        this.totalTupplesLabel.setVisible(false);
        this.totalTupplesSlideBar.setVisible(false);
        this.maxTupplesSlideBar.setVisible(true);
        this.componentMenu.setVisible(true);
        probabilityLabel.setVisible(true);
        probabilitySlideBar.setVisible(true);
        componentLabel.setVisible(true);
    }

    @FXML
    public void sizerChooseAction() {
        mutationTypeMenu.setText("Sizer");
        probabilitySlideBar.setVisible(true);
        probabilityLabel.setVisible(true);
        this.maxTupplesLabel.setVisible(false);
        this.totalTupplesLabel.setVisible(true);
        this.totalTupplesSlideBar.setVisible(true);
        this.maxTupplesSlideBar.setVisible(false);
        this.componentMenu.setVisible(false);
        componentLabel.setVisible(false);

    }

    @FXML
    public void dayTimeOrientedChooseAction() {
        crossoverTypeMenu.setText("Day Time Oriented");
        aspectMenu.setVisible(false);
        cuttingPointsLabel.setVisible(true);
        cuttingPointsSlideBar.setVisible(true);
        aspectLabel.setVisible(false);
    }

    @FXML
    void onClickSelectionCheckBox() {
        if (descriptor.getEttDescriptor().getETTEvolutionEngine().getETTSelection().getType().equals("Truncation")) {
            setTruncation();
        } else {
            setRouletteWheel();
        }

    }

    private void setRouletteWheel() {
        selectionTypeMenu.setText("Roulette Wheel");
        truncationMenuItem.setVisible(false);
        hideAllSelection();
        elitismLabel.setVisible(selectionTypeCheckBox.selectedProperty().getValue());
        elitismSlideBar.setVisible(selectionTypeCheckBox.selectedProperty().getValue());
    }

    private void setTruncation() {
        selectionTypeMenu.setText("Truncation");
        rouletteWheelMenuItem.setVisible(false);
        hideAllSelection();
        topPercentLabel.setVisible(selectionTypeCheckBox.selectedProperty().getValue());
        topPercentSlideBar.setVisible(selectionTypeCheckBox.selectedProperty().getValue());
        elitismLabel.setVisible(selectionTypeCheckBox.selectedProperty().getValue());
        elitismSlideBar.setVisible(selectionTypeCheckBox.selectedProperty().getValue());
    }

    private void hideAllSelection() {
        this.topPercentLabel.setVisible(false);
        this.topPercentSlideBar.setVisible(false);
        this.elitismLabel.setVisible(false);
        this.elitismSlideBar.setVisible(false);
    }

    @FXML
    void onClickCrossoverCheckBox() {
        if (descriptor.getEttDescriptor().getETTEvolutionEngine().getETTCrossover().getName().equals("Aspect Oriented")) {
            setAspectOriented();
        } else {
            setDayTimeOriented();
        }
    }

    private void setDayTimeOriented() {
        crossoverTypeMenu.setText("Day Time Oriented");
        aspectOrientedMenuItem.setVisible(false);
        hideAllCrossover();
        cuttingPointsLabel.setVisible(crossoverTypeCheckBox.selectedProperty().getValue());
        cuttingPointsSlideBar.setVisible(crossoverTypeCheckBox.selectedProperty().getValue());
    }

    private void setAspectOriented() {
        crossoverTypeMenu.setText("Aspect Oriented");
        dayTimeOrientedMenuItem.setVisible(false);
        hideAllCrossover();
        aspectMenu.setVisible(crossoverTypeCheckBox.selectedProperty().getValue());
        cuttingPointsLabel.setVisible(crossoverTypeCheckBox.selectedProperty().getValue());
        cuttingPointsSlideBar.setVisible(crossoverTypeCheckBox.selectedProperty().getValue());
        aspectLabel.setVisible(crossoverTypeCheckBox.selectedProperty().getValue());
    }

    private void hideAllCrossover() {
        this.cuttingPointsSlideBar.setVisible(false);
        this.cuttingPointsLabel.setVisible(false);
        this.aspectLabel.setVisible(false);
        this.aspectMenu.setVisible(false);
    }


    @FXML
    void onClickMutationCheckBox() {
        mutationTypeMenu.setText("Sizer");
        hideAllMutations();
        this.totalTupplesLabel.setVisible(mutationTypeCheckBox.selectedProperty().getValue());
        this.totalTupplesSlideBar.setVisible(mutationTypeCheckBox.selectedProperty().getValue());
        this.probabilityLabel.setVisible(mutationTypeCheckBox.selectedProperty().getValue());
        this.probabilitySlideBar.setVisible(mutationTypeCheckBox.selectedProperty().getValue());
    }

    private void hideAllMutations() {
        this.totalTupplesLabel.setVisible(false);
        this.totalTupplesSlideBar.setVisible(false);
        this.maxTupplesLabel.setVisible(false);
        this.maxTupplesSlideBar.setVisible(false);
        this.probabilityLabel.setVisible(false);
        this.probabilitySlideBar.setVisible(false);
        this.componentMenu.setVisible(false);
        this.componentLabel.setVisible(false);
    }

    public void onClickApplyButton(ActionEvent event) {
        descriptor.getEttDescriptor().getETTEvolutionEngine().getETTSelection().setETTElitism((int) elitismSlideBar.getValue());
        checkSelectionCheckBox();
        checkCrossoverCheckBox();
        checkMutationCheckBox();

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(prevScene);
        window.show();
    }

    private void checkMutationCheckBox() {
        if (mutationTypeCheckBox.selectedProperty().getValue()) {
            if (mutationTypeMenu.getText().equals("Sizer")) {
                createNewSizer();
            } else if (mutationTypeMenu.getText().equals("Flipping")) {
                createNewFlipping();
            }
        }
    }

    private void checkCrossoverCheckBox() {
        if (crossoverTypeCheckBox.selectedProperty().getValue()) {
            int days = descriptor.getEttDescriptor().getETTTimeTable().getDays();
            int hours = descriptor.getEttDescriptor().getETTTimeTable().getHours();
            int cuttingPoints = (int) cuttingPointsSlideBar.getValue();

            if (crossoverTypeMenu.getText().equals("Aspect Oriented")) {
                createNewAspectOriented(days, hours, cuttingPoints);
            } else {
                createNewDayTimeOriented(days, hours, cuttingPoints);
            }
        }
    }

    private void checkSelectionCheckBox() {
        if (selectionTypeCheckBox.selectedProperty().getValue()) {
            if (selectionTypeMenu.getText().equals("Truncation")) {
                createNewTruncation();
            } else {
                createNewRouletteWheel();
            }
        }
    }

    private void createNewFlipping() {
        ETTMutation mutation = descriptor.getEttDescriptor().getETTEvolutionEngine().getETTMutations().getETTMutation().stream()
                .filter(m -> "Flipping".equals(m.getName()))
                .findAny()
                .orElse(null);
        if (mutation != null) {
            addNewFlipping(mutation);

        } else {
            addNewEttMutationFlipping();
        }
    }

    private void addNewEttMutationFlipping() {
        ETTMutation ettMutation = new ETTMutation();
        ettMutation.setName("Flipping");
        ettMutation.setProbability(probabilitySlideBar.getValue());
        ettMutation.setConfiguration("MaxTupples=" + (int) maxTupplesSlideBar.getValue() + ",Component=" + componentMenu.getText());
        Flipping newMutation = new Flipping(ettMutation);
        newMutation.setProbability(probabilitySlideBar.getValue());
        newMutation.setConfiguration("MaxTupples=" + (int) maxTupplesSlideBar.getValue() + ",Component=" + componentMenu.getText());
        newMutation.setComponentToChange(componentMenu.getText());
        newMutation.setMaxTuples((int) maxTupplesSlideBar.getValue());
        descriptor.getEvolutionEngine().getM_Mutations().getMutationList().add(newMutation);
        descriptor.getEttDescriptor().getETTEvolutionEngine().getETTMutations().getETTMutation().add(ettMutation);
    }

    private void addNewFlipping(ETTMutation mutation) {
        Flipping newFlipping = new Flipping(mutation);
        newFlipping.getM_ETTMutation().setProbability(probabilitySlideBar.getValue());
        newFlipping.getM_ETTMutation().setName("Flipping");
        newFlipping.setName("Flipping");
        newFlipping.setProbability(probabilitySlideBar.getValue());
        newFlipping.getM_ETTMutation().setConfiguration("MaxTupples=" + (int) maxTupplesSlideBar.getValue() + ",Component=" + componentMenu.getText());
        newFlipping.setMaxTuples((int) maxTupplesSlideBar.getValue());
        newFlipping.getM_ETTMutation().setProbability(probabilitySlideBar.getValue());
        newFlipping.setComponentToChange(componentMenu.getText());
        descriptor.getEvolutionEngine().getM_Mutations().getMutationList().add(newFlipping);
    }

    private void createNewSizer() {
        ETTMutation mutation = descriptor.getEttDescriptor().getETTEvolutionEngine().getETTMutations().getETTMutation().stream()
                .filter(m -> "Sizer".equals(m.getName()))
                .findAny()
                .orElse(null);
        if (mutation != null) {
            addNewSizer(mutation);
        } else {
            addNewEttMutationSizer();
        }
    }

    private void addNewSizer(ETTMutation mutation) {
        Sizer newSizer = new Sizer(mutation);
        newSizer.getM_ETTMutation().setProbability(probabilitySlideBar.getValue());
        newSizer.getM_ETTMutation().setName("Sizer");
        newSizer.setName("sizer");
        newSizer.setProbability(probabilitySlideBar.getValue());
        newSizer.getM_ETTMutation().setConfiguration("TotalTupples=" + (int) totalTupplesSlideBar.getValue());
        newSizer.getM_ETTMutation().setProbability(probabilitySlideBar.getValue());
        newSizer.setTotalTupples((int) totalTupplesSlideBar.getValue());
        descriptor.getEvolutionEngine().getM_Mutations().getMutationList().add(newSizer);
    }

    private void addNewEttMutationSizer() {
        ETTMutation ettMutation = new ETTMutation();
        ettMutation.setName("Sizer");
        ettMutation.setProbability(probabilitySlideBar.getValue());
        ettMutation.setConfiguration("TotalTupples=" + (int) totalTupplesSlideBar.getValue());
        Sizer newMutation = new Sizer(ettMutation);
        newMutation.setName("sizer");
        newMutation.setProbability(probabilitySlideBar.getValue());
        newMutation.setTotalTupples((int) totalTupplesSlideBar.getValue());
        descriptor.getEttDescriptor().getETTEvolutionEngine().getETTMutations().getETTMutation().add(ettMutation);
        descriptor.getEvolutionEngine().getM_Mutations().getMutationList().add(newMutation);
    }

    private void createNewDayTimeOriented(int days, int hours, int cuttingPoints) {
        DayTimeOriented newDayTimeOriented = new DayTimeOriented(days, hours, cuttingPoints);
        newDayTimeOriented.setM_ETTCrossover(descriptor.getEttDescriptor().getETTEvolutionEngine().getETTCrossover());
        newDayTimeOriented.getM_ETTCrossover().setName("DayTimeOriented");
        descriptor.getEvolutionEngine().setCrossover(newDayTimeOriented);
        newDayTimeOriented.getM_ETTCrossover().setCuttingPoints((int) cuttingPointsSlideBar.getValue());
        newDayTimeOriented.setName("DayTimeOriented");
        newDayTimeOriented.getM_ETTCrossover().setCuttingPoints(cuttingPoints);
        newDayTimeOriented.setCuttingPoints(cuttingPoints);
    }

    private void createNewAspectOriented(int days, int hours, int cuttingPoints) {
        AspectOriented newAspectOriented = new AspectOriented(days, hours, cuttingPoints);
        newAspectOriented.setM_ETTCrossover(descriptor.getEttDescriptor().getETTEvolutionEngine().getETTCrossover());
        newAspectOriented.getM_ETTCrossover().setName("AspectOriented");
        newAspectOriented.setComponent(aspectMenu.getText());
        newAspectOriented.setName("AspectOriented");
        newAspectOriented.setCuttingPoints(cuttingPoints);
        newAspectOriented.getM_ETTCrossover().setCuttingPoints(cuttingPoints);
        descriptor.getEvolutionEngine().setCrossover(newAspectOriented);
    }

    private void createNewRouletteWheel() {
        RouletteWheel newRouletteWheel = new RouletteWheel();
        newRouletteWheel.setM_ETTSelection(descriptor.getEttDescriptor().getETTEvolutionEngine().getETTSelection());
        newRouletteWheel.setElitism((int) (descriptor.getEttDescriptor().getETTEvolutionEngine().getETTInitialPopulation().getSize() * ((int) elitismSlideBar.getValue() * 0.01)));
        newRouletteWheel.getM_ETTSelection().setType("RouletteWheel");
        descriptor.getEvolutionEngine().setSelection(newRouletteWheel);
    }

    private void createNewTruncation() {
        Truncation newTruncation = new Truncation();
        newTruncation.setM_ETTSelection(descriptor.getEttDescriptor().getETTEvolutionEngine().getETTSelection());
        newTruncation.setTopPercent((int) topPercentSlideBar.getValue());
        newTruncation.setElitism((int) (descriptor.getEttDescriptor().getETTEvolutionEngine().getETTInitialPopulation().getSize() * ((int) elitismSlideBar.getValue() * 0.01)));
        newTruncation.getM_ETTSelection().setType("Truncation");
        newTruncation.getM_ETTSelection().setConfiguration("Top Percent=" + (int) topPercentSlideBar.getValue());
        descriptor.getEvolutionEngine().setSelection(newTruncation);
    }
}