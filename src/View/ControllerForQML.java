/**
 * Sample Skeleton for 'GUIquizMasterLogin.fxml' Controller Class
 */

package View;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
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
    private Button goBack; // Value injected by FXMLLoader

    @FXML // fx:id="User"
    private TextField User; // Value injected by FXMLLoader

    @FXML // fx:id="logConfirm"
    private Button logConfirm; // Value injected by FXMLLoader

    @FXML // fx:id="pWord"
    private PasswordField pWord; // Value injected by FXMLLoader

    @FXML // fx:id="userNameDisplay"
    private Label userNameDisplay; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert goBack != null : "fx:id=\"goBack\" was not injected: check your FXML file 'GUIquizMasterLogin.fxml'.";
        assert User != null : "fx:id=\"User\" was not injected: check your FXML file 'GUIquizMasterLogin.fxml'.";
        assert logConfirm != null : "fx:id=\"logConfirm\" was not injected: check your FXML file 'GUIquizMasterLogin.fxml'.";
        assert pWord != null : "fx:id=\"pWord\" was not injected: check your FXML file 'GUIquizMasterLogin.fxml'.";
        assert userNameDisplay != null : "fx:id=\"userNameDisplay\" was not injected: check your FXML file 'GUIquizMasterLogin.fxml'.";

    }
}
