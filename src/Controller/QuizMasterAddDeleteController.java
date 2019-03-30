package Controller;

import Driver.QuizBeeApplication;
import Model.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class QuizMasterAddDeleteController {
    @FXML Label welcomeUsernameLbl, logoutLbl;
    @FXML Button addQuizBtn, deleteQuizBtn;

    private final Client client = Client.getInstance();

    public void addQuiz() throws IOException, RuntimeException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/AddQuiz.fxml"));
        QuizBeeApplication.getStage().setScene(new Scene(root, 590, 538));
        QuizBeeApplication.getStage().setTitle("Add Quiz");
    }

    public void deleteQuiz() throws IOException, RuntimeException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/DeleteQuiz.fxml"));
        QuizBeeApplication.getStage().setScene(new Scene(root, 474, 294));
        QuizBeeApplication.getStage().setTitle("Delete Quiz");
    }

    public void logout() throws IOException, RuntimeException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/GUIwelcomePage.fxml"));
        QuizBeeApplication.getStage().setScene(new Scene(root, 677, 454));
        QuizBeeApplication.getStage().setTitle("Welcome to Network Quiz Bee!");
    }
}
