/**
 * Sample Skeleton for 'LogIn.fxml' Controller Class
 */

package Controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import Driver.QuizBeeApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.*;

public class LogInController {
    @FXML
    private ImageView background;
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
        try {
            background.setImage(new Image(getClass().getClassLoader().getResource(
                    "images/green_and_white.png").toURI().toString()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void login() throws IOException {
        String username = usernameTxtFld.getText();
        String password = passwordFld.getText();

        // do something in DB

        // if user is valid, go to quiz master mode
        if (username.equals("admin") && password.equals("password")) {
            Parent root = FXMLLoader.load(getClass().getResource("/View/MasterControls.fxml"));
            QuizBeeApplication.getStage().setScene(new Scene(root, 390, 350));
            QuizBeeApplication.getStage().setTitle("Quiz Master");
        } else {
            JOptionPane.showMessageDialog(null, "Username and Password do not match.",
                    "Log in Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void back() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Home.fxml"));
        QuizBeeApplication.getStage().setScene(new Scene(root, 677, 454));
        QuizBeeApplication.getStage().setTitle("Welcome to Network Quiz Bee!");
    }
}
