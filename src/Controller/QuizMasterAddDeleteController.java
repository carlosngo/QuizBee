package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class QuizMasterAddDeleteController {
    @FXML Label welcomeUsernameLbl, logoutLbl;
    @FXML Button addQuizBtn, deleteQuizBtn;

    public void addQuiz() throws IOException, RuntimeException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/AddQuiz.fxml"));
        ActivateRegistrationController.getStage().setScene(new Scene(root, 590, 538));
        ActivateRegistrationController.getStage().setTitle("Add Quiz");
    }
    public void deleteQuiz() throws IOException, RuntimeException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/DeleteQuiz.fxml"));
        ActivateRegistrationController.getStage().setScene(new Scene(root, 474, 294));
        ActivateRegistrationController.getStage().setTitle("Delete Quiz");
    }
    public void logout() throws IOException, RuntimeException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/GUIwelcomePage.fxml"));
        ActivateRegistrationController.getStage().setScene(new Scene(root, 677, 454));
        ActivateRegistrationController.getStage().setTitle("Welcome to Network Quiz Bee!");
    }
}
