package classes;

import fxml.LoadFileController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Stopwatch extends Application {
    Button sButton = new Button("Start");
    Text text;
    Timeline timeline;
    int minutes = 0, seconds = 0, millis = 0;
    private AlgorithmThread d;

    public AlgorithmThread getD() {
        return d;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public void setD(AlgorithmThread d) {
        this.d = d;
    }

    boolean sos = true;

    public int getMins() {
        return minutes;
    }

    public void stop() {
        minutes = 0;
        seconds = 0;
        millis = 0;
        if (timeline != null) {
            timeline.pause();
            text.setText("00:00:000");
        }
        if (!sos) {
            sos = true;
            sButton.setText("Start");
        }
    }

    public Text getText() {
        return text;
    }

    void change(Text text) throws InterruptedException {
        if (millis == 1000) {
            seconds++;
            if (d.getMaxTime() != Integer.MAX_VALUE) {
                d.updateTimeProgressBar();
            }
            if (d.getMaxFitness() != Integer.MAX_VALUE) {
                d.updateFitnessProgressBar(LoadFileController.getModel().getDescriptor().getSolutions().getListOfSolutions().get(0).getFitnessGrade());
            }
            millis = 0;
        }
        if (seconds == 60) {
            minutes++;
            seconds = 0;
        }
        text.setText((((minutes / 10) == 0) ? "0" : "") + minutes + ":"
                + (((seconds / 10) == 0) ? "0" : "") + seconds + ":"
                + (((millis / 10) == 0) ? "00" : (((millis / 100) == 0) ? "0" : "")) + millis++);
    }

    @Override
    public void start(Stage stage) {
        if (timeline == null) {
            text = new Text("00:00:000");
            timeline = new Timeline(new KeyFrame(Duration.millis(1), event -> {
                try {
                    change(text);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.setAutoReverse(false);
            timeline.setDelay(Duration.ZERO);

        }
        if (sos) {
            timeline.play();
            sos = false;
            sButton.setText("Pause");
        } else {
            timeline.pause();
            sos = true;
            sButton.setText("Start");
        }
    }
}