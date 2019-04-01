/**
 * Sample Skeleton for 'Room.fxml' Controller Class
 */

package Controller;

import java.net.URISyntaxException;
import java.util.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Driver.QuizBeeApplication;
import Model.Client;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class RoomController {
    @FXML
    private ImageView background;
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="goBack"
    private Button goBack; // Value injected by FXMLLoader

    @FXML // fx:id="max4ListofParticipants"
    private ListView<String> max4ListofParticipants; // Value injected by FXMLLoader

    @FXML // fx:id="startQuiz"
    private Button startQuiz; // Value injected by FXMLLoader

    @FXML // fx:id="selectedQuizName"
    private Text selectedQuizName; // Value injected by FXMLLoader

    private Client client = Client.getInstance();

    @FXML
    void startTheQuiz() {
        client.startQuiz();
    }

    public void switchNextScene() {
        Platform.runLater(() -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/View/QuizProper.fxml"));
                QuizBeeApplication.getStage().setScene(new Scene(root, 1000, 607));
                QuizBeeApplication.getStage().setTitle("Quiz");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    void goingBack() {
        client.leaveQuiz();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/Lobby.fxml"));
            QuizBeeApplication.getStage().setScene(new Scene(root, 650, 450));
            QuizBeeApplication.getStage().setTitle("Select Quiz");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void update() {
        TreeMap<String, Integer> map = client.getCurrentQuiz().getParticipants();
        ArrayList<String> list = new ArrayList<>(map.keySet());
        Platform.runLater(() -> {
            max4ListofParticipants.setItems(FXCollections.observableArrayList(list));
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
        client.setLobbyController(this);
        selectedQuizName.setText(client.getCurrentQuiz().getName());
        update();
    }
}
