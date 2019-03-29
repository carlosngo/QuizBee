/**
 * Sample Skeleton for 'GUIselectQuiz.fxml' Controller Class
 */

package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class SelectQController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="Quizzes"
    private ListView<?> Quizzes; // Value injected by FXMLLoader
    
    @FXML
    private Button refreshButton;

    @FXML
    void refresh(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert Quizzes != null : "fx:id=\"Quizzes\" was not injected: check your FXML file 'GUIselectQuiz.fxml'.";

    }
}
