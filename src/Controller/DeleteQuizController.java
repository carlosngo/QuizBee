package Controller;

import java.util.*;

import Driver.QuizBeeApplication;
import Model.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class DeleteQuizController {
    @FXML Label usernameLbl;
    @FXML ComboBox<String> quizNameCBox;
    @FXML Button deleteBtn, cancelBtn;

    private final Client client = Client.getInstance();

    public void initialize() {
        // Hi + username
        usernameLbl.setText("Delete Quiz");

        // List of quiz names

        // if quiz name is not empty, add it to the combo box
        ArrayList<String> quizNames = new ArrayList<>();
        ArrayList<Quiz> quizzes = client.getQuizzes();
        for (int i = 0; i < quizzes.size(); i++) quizNames.add(quizzes.get(i).getName());
        quizNameCBox.getItems().addAll(quizNames);
        // else, use the command below, so that it won't have a null pointer exception :D
        // quizNameCBox.getItems().addAll("");

        quizNameCBox.getSelectionModel().selectFirst();
    }

    public void delete() {
        String quizToBeDeleted = quizNameCBox.getSelectionModel().getSelectedItem();
        quizNameCBox.getItems().remove(quizToBeDeleted);
        client.deleteQuiz(quizToBeDeleted);
    }

    public void cancel () throws IOException, RuntimeException{
        Parent root = FXMLLoader.load(getClass().getResource("/View/QuizMasterAddDelete.fxml"));
        QuizBeeApplication.getStage().setScene(new Scene(root, 390, 350));
    }

}
