/**
 * Sample Skeleton for 'Home.fxml' Controller Class
 */

package Controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import Driver.QuizBeeApplication;
import Model.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.*;

public class HomeController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    private ImageView background;

    @FXML // fx:id="qMaster"
    private Button quizMasterBtn; // Value injected by FXMLLoader

    @FXML // fx:id="Particpant"
    private Button participantBtn; // Value injected by FXMLLoader

    private Client client = Client.getInstance();

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        try {
            background.setImage(new Image(getClass().getClassLoader().getResource(
                    "images/green_and_white.png").toURI().toString()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void loginAsQuizMaster() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/LogIn.fxml"));
        QuizBeeApplication.getStage().setScene(new Scene(root, 518, 298));
        QuizBeeApplication.getStage().setTitle("Login as Quiz Master");
    }

    public void playAsParticipant() throws IOException {
        String name;
        boolean success = false;
        while (!success) {
            name = "";
            while (name.equals(""))
                name = JOptionPane.showInputDialog(null, "Please input your name.");
            if (client.setName(name)) success = true;
            else JOptionPane.showMessageDialog(null, "Name is already taken.");
        }
        JOptionPane.showMessageDialog(null, "Registration successful.");
        Parent root = FXMLLoader.load(getClass().getResource("/View/Lobby.fxml"));
        QuizBeeApplication.getStage().setScene(new Scene(root, 650, 450));
        QuizBeeApplication.getStage().setTitle("Select a quiz");
    }
}
