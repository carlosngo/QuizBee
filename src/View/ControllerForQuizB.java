/**
 * Sample Skeleton for 'GUIqB.fxml' Controller Class
 */

package View;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;

public class ControllerForQuizB {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

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


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert score != null : "fx:id=\"score\" was not injected: check your FXML file 'GUIqB.fxml'.";
        assert timer != null : "fx:id=\"timer\" was not injected: check your FXML file 'GUIqB.fxml'.";
        assert correctOrWrong != null : "fx:id=\"correctOrWrong\" was not injected: check your FXML file 'GUIqB.fxml'.";
        assert pickD != null : "fx:id=\"pickD\" was not injected: check your FXML file 'GUIqB.fxml'.";
        assert pickC != null : "fx:id=\"pickC\" was not injected: check your FXML file 'GUIqB.fxml'.";
        assert pickB != null : "fx:id=\"pickB\" was not injected: check your FXML file 'GUIqB.fxml'.";
        assert pickA != null : "fx:id=\"pickA\" was not injected: check your FXML file 'GUIqB.fxml'.";
        assert userName != null : "fx:id=\"userName\" was not injected: check your FXML file 'GUIqB.fxml'.";
        assert rightAns != null : "fx:id=\"rightAns\" was not injected: check your FXML file 'GUIqB.fxml'.";
        assert questionDisplay != null : "fx:id=\"questionDisplay\" was not injected: check your FXML file 'GUIqB.fxml'.";

    }
}
