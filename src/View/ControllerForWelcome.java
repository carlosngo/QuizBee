/**
 * Sample Skeleton for 'GUIwelcomePage.fxml' Controller Class
 */

package View;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControllerForWelcome {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="qMaster"
    private Button qMaster; // Value injected by FXMLLoader

    @FXML // fx:id="Particpant"
    private Button Particpant; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert qMaster != null : "fx:id=\"qMaster\" was not injected: check your FXML file 'GUIwelcomePage.fxml'.";
        assert Particpant != null : "fx:id=\"Particpant\" was not injected: check your FXML file 'GUIwelcomePage.fxml'.";

    }
}
