/**
 * Sample Skeleton for 'GUIselectQuiz.fxml' Controller Class
 */

package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import Driver.QuizBeeApplication;
import Model.*;

import javafx.application.Platform;
import javafx.collections.FXCollections;
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
    private ListView<String> quizzes = new ListView<String>(); // Value injected by FXMLLoader
    @FXML
    private Button refreshButton;
    @FXML
    private Button joinQuizButton;

    private Client client = Client.getInstance();
    
    public String getSelectedQuiz() {
    	return quizzes.getSelectionModel().getSelectedItem();
    }
    
    
    @FXML
    void readQuizName(MouseEvent e) {  //when the ListView is clicked
    	
    }

    @FXML
    void refresh(MouseEvent e) {  //refresh button
        quizzes.setItems(FXCollections.observableArrayList(getQuizzes()));
    }
    
    @FXML
    void joinQuiz(MouseEvent e) throws IOException, RuntimeException { //edit
    	client.joinQuiz(getSelectedQuiz());
    	Parent root = FXMLLoader.load(getClass().getResource("/View/GUILobby.fxml"));
        QuizBeeApplication.getStage().setScene(new Scene(root, 553, 357));
        QuizBeeApplication.getStage().setTitle("Lobby");
    	
    }
    
    public ArrayList<String> getQuizzes() {
        ArrayList<String> quizNames = new ArrayList<>();
        for (Quiz quiz : client.getQuizzes()) {
            quizNames.add(quiz.getName());
        }
        return quizNames;
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        quizzes.setItems(FXCollections.observableArrayList(getQuizzes()));
    }
}
