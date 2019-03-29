/**
 * Sample Skeleton for 'GUIwelcomePage.fxml' Controller Class
 */

package View;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Driver.QuizBeeApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class ControllerForWelcome {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="qMaster"
    private Button quizMasterBtn; // Value injected by FXMLLoader

    @FXML // fx:id="Particpant"
    private Button participantBtn; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert quizMasterBtn != null : "fx:id=\"qMaster\" was not injected: check your FXML file 'GUIwelcomePage.fxml'.";
        assert participantBtn != null : "fx:id=\"Particpant\" was not injected: check your FXML file 'GUIwelcomePage.fxml'.";
    }

    public void loginAsQuizMaster() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/GUIquizMasterLogin.fxml"));
        QuizBeeApplication.getStage().setScene(new Scene(root, 491, 332));
        QuizBeeApplication.getStage().setTitle("Login as Quiz Master");
    }

    public void playAsParticipant() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/GUIselectQuiz.fxml"));
        QuizBeeApplication.getStage().setScene(new Scene(root, 592, 391));
        QuizBeeApplication.getStage().setTitle("Select a quiz");
    }
}
