/**
 * Sample Skeleton for 'GUILobby.fxml' Controller Class
 */

package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Driver.QuizBeeApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class LobbyController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="goBack"
    private Button goBack; // Value injected by FXMLLoader

    @FXML // fx:id="max4ListofParticipants"
    private ListView<?> max4ListofParticipants; // Value injected by FXMLLoader

    @FXML // fx:id="startQuiz"
    private Button startQuiz; // Value injected by FXMLLoader

    @FXML // fx:id="selectedQuizName"
    private Text selectedQuizName; // Value injected by FXMLLoader
    
    @FXML
    void startTheQuiz(MouseEvent e) throws IOException, RuntimeException {
    	
    	Parent root = FXMLLoader.load(getClass().getResource("/View/GUIqB.fxml"));
        QuizBeeApplication.getStage().setScene(new Scene(root, 1075, 607));
        QuizBeeApplication.getStage().setTitle("Quiz");
    }

    @FXML
    void goingBack(MouseEvent e) throws IOException, RuntimeException {

    	Parent root = FXMLLoader.load(getClass().getResource("/View/GUIselectQuiz.fxml"));
        QuizBeeApplication.getStage().setScene(new Scene(root, 592, 391));
        QuizBeeApplication.getStage().setTitle("Select Quiz");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        
    }
}
