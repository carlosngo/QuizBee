/**
 * Sample Skeleton for 'GUIquizMasterLogin.fxml' Controller Class
 */

package View;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Controller.ActivateRegistrationController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ControllerForQML {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="goBack"
    private Button backBtn; // Value injected by FXMLLoader

    @FXML // fx:id="User"
    private TextField usernameTxtFld; // Value injected by FXMLLoader

    @FXML // fx:id="logConfirm"
    private Button loginBtn; // Value injected by FXMLLoader

    @FXML // fx:id="pWord"
    private PasswordField passwordFld; // Value injected by FXMLLoader

    @FXML // fx:id="userNameDisplay"
    private Label userNameDisplay; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert backBtn != null : "fx:id=\"goBack\" was not injected: check your FXML file 'GUIquizMasterLogin.fxml'.";
        assert usernameTxtFld != null : "fx:id=\"User\" was not injected: check your FXML file 'GUIquizMasterLogin.fxml'.";
        assert loginBtn != null : "fx:id=\"logConfirm\" was not injected: check your FXML file 'GUIquizMasterLogin.fxml'.";
        assert passwordFld != null : "fx:id=\"pWord\" was not injected: check your FXML file 'GUIquizMasterLogin.fxml'.";
        assert userNameDisplay != null : "fx:id=\"userNameDisplay\" was not injected: check your FXML file 'GUIquizMasterLogin.fxml'.";

    }

    public void login() throws IOException {
        String username = usernameTxtFld.getText();
        String password = passwordFld.getText();

        // do something in DB

        // if user is valid, go to quiz master mode

        Parent root = FXMLLoader.load(getClass().getResource("/View/QuizMasterAddDelete.fxml"));
        ActivateRegistrationController.getStage().setScene(new Scene(root, 390, 350));
    }

    public void back() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/GUIwelcomePage.fxml"));
        ActivateRegistrationController.getStage().setScene(new Scene(root, 677, 454));
    }
}
