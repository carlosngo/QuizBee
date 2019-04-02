/**
 * Sample Skeleton for 'QuizProper.fxml' Controller Class
 */

package Controller;

import Driver.QuizBeeApplication;
import Model.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class QuizProperController {
    @FXML
    private ImageView background;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    
    @FXML
    private ListView<String> listOfParticipants;

    @FXML // fx:id="score"
    private Label score; // Value injected by FXMLLoader

    @FXML // fx:id="timer"
    private ProgressIndicator timer; // Value injected by FXMLLoader

    @FXML // fx:id="correctOrWrong"
    private Label correctOrWrong; // Value injected by FXMLLoader

    @FXML // fx:id="pickD"
    private Button pickD; // Value injected by FXMLLoader

    @FXML // fx:id="pickC"
    private Button pickC; // Value injected by FXMLLoader

    @FXML // fx:id="pickB"
    private Button pickB; // Value injected by FXMLLoader

    @FXML // fx:id="pickA"
    private Button pickA; // Value injected by FXMLLoader

    @FXML // fx:id="userName"
    private Label userName; // Value injected by FXMLLoader

    @FXML // fx:id="rightAns"
    private Label rightAns; // Value injected by FXMLLoader

    @FXML // fx:id="questionDisplay"
    private TextArea questionDisplay; // Value injected by FXMLLoader

    private Client client = Client.getInstance();

    private ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
    private long timePassed = 0;

    @FXML
    public void clickA() {
        client.setChoice(0);
        showAnswer();
    }

    @FXML
    public void clickB() {
        client.setChoice(1);
        showAnswer();
    }

    @FXML
    public void clickC() {
        client.setChoice(2);
        showAnswer();
    }

    @FXML
    public void clickD() {
        client.setChoice(3);
        showAnswer();
    }

    public void switchNextScene() {
        Platform.runLater(() -> {
            ses.shutdown();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/View/ResultScreen.fxml"));
                QuizBeeApplication.getStage().setScene(new Scene(root, 562, 435));
                QuizBeeApplication.getStage().setTitle("Quiz Results");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void showAnswer() {
        rightAns.setVisible(true);
        pickA.setDisable(true);
        pickB.setDisable(true);
        pickC.setDisable(true);
        pickD.setDisable(true);
//        try {
//            QuizBeeApplication.getStage().getScene().setOnMouseClicked();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public void disableControls() {
        Platform.runLater(() -> {
            pickA.setDisable(true);
            pickB.setDisable(true);
            pickC.setDisable(true);
            pickD.setDisable(true);
        });

    }

    public void update(Question question) {
        Platform.runLater(() -> {
            System.out.println("Updating question...");
            questionDisplay.setText(question.getPrompt());
            pickA.setText("A." + question.getChoices().get(0));
            pickB.setText("B." + question.getChoices().get(1));
            pickC.setText("C." + question.getChoices().get(2));
            pickD.setText("D." + question.getChoices().get(3));
            rightAns.setVisible(false);
            pickA.setDisable(false);
            pickB.setDisable(false);
            pickC.setDisable(false);
            pickD.setDisable(false);
            rightAns.setText("Correct answer is " + (char)(question.getAnswer() + 'A'));
        });
    }

    public void update(TreeMap<String, Integer> participants) {
        Platform.runLater(() -> {
            System.out.println("Updating participants...");
            ArrayList<String> info = new ArrayList<>();
            for (String participant : participants.keySet()) {
                if (participant.equals(client.getName())) score.setText(participants.get(participant) + "");
                info.add(participant + " " + participants.get(participant));
            }
            listOfParticipants.setItems(FXCollections.observableArrayList(info));
            System.out.println("Successfully updated participants.");
        });
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        try {
            background.setImage(new Image(getClass().getClassLoader().getResource(
                    "images/green_and_white.png").toURI().toString()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        userName.setText(client.getName());
        score.setText("0");
        listOfParticipants.setStyle("-fx-font-size: 15; -fx-font-style: Britannic Bold;");
        questionDisplay.setEditable(false);
        client.setQuizBController(this);
        ses.scheduleAtFixedRate(() -> {
            timer.setProgress(timePassed * 1.0 / Quiz.DURATION);
            long remainingTime = Quiz.DURATION - timePassed;
            String hms = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(remainingTime) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(remainingTime)),
                    TimeUnit.MILLISECONDS.toSeconds(remainingTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(remainingTime)));

            Text timerText = (Text) timer.lookup(".percentage");
            if (timerText != null) timerText.setText(hms);

            timePassed += 1000;
        }, 0, 1, TimeUnit.SECONDS);  // execute every x seconds
        client.administerQuiz();
    }
}
