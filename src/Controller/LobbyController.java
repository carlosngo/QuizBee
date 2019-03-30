/**
 * Sample Skeleton for 'GUILobby.fxml' Controller Class
 */

package Controller;

import java.awt.event.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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
    void startTheQuiz(ActionEvent event) {

    }

    @FXML
    void goingBack(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        
    }
}
