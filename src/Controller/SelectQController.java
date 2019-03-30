/**
 * Sample Skeleton for 'GUIselectQuiz.fxml' Controller Class
 */

package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Driver.QuizBeeApplication;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SelectQController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    @FXML // fx:id="Quizzes"
    private ListView<String> Quizzes = new ListView<String>(); // Value injected by FXMLLoader
    @FXML
    private Button refreshButton;
    @FXML
    private Button joinQuizButton;
    
    
    String quizSelected = Quizzes.getSelectionModel().getSelectedItem();
    
    public String getQuizSelected(String quizSelected) {
    	return quizSelected;
    }
    
    
    @FXML
    void readQuizName(MouseEvent e) {  //when the ListView is clicked
    	
    }

    @FXML
    void refresh(MouseEvent e) {  //refresh button

    }
    
    @FXML
    void joinQuiz(MouseEvent e) throws IOException, RuntimeException { //edit
    	
    	Parent root = FXMLLoader.load(getClass().getResource("/View/GUILobby.fxml"));
        QuizBeeApplication.getStage().setScene(new Scene(root, 553, 357));
        QuizBeeApplication.getStage().setTitle("Lobby");
    	
    }
    
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        
    }
}
