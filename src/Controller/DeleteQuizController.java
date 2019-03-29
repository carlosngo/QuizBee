package Controller;

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
        usernameLbl.setText("Hi Carlos");

        // List of quiz names

        // if quiz name is not empty, add it to the combo box
        quizNameCBox.getItems().addAll("Quiz Name 1", "Quiz Name 2", "Quiz Name 3");
        // else, use the command below, so that it won't have a null pointer exception :D
        // quizNameCBox.getItems().addAll("");

        quizNameCBox.getSelectionModel().selectFirst();
    }

    public void delete() {

        if (quizNameCBox.getSelectionModel().getSelectedItem().isEmpty()) {
            Stage window = new Stage();

            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Error!");
            window.setMinWidth(250);

            Label label = new Label("No Quizzes to be Deleted!");
            Button closeButton = new Button("Close");

            VBox layout = new VBox(20);
            layout.getChildren().addAll(label, closeButton);
            layout.setAlignment(Pos.CENTER);
            closeButton.setOnAction(e -> window.close());

            window.setScene(new Scene(layout));
            window.showAndWait();
        }

        else {
            String quizToBeDeleted = quizNameCBox.getSelectionModel().getSelectedItem();
            quizNameCBox.getItems().remove(quizToBeDeleted);

            // then delete whatever is selected


            // An alert box will appear to inform user that selected quiz has been successfully deleted
            Stage window = new Stage();

            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Success!");
            window.setMinWidth(250);

            Button close = new Button("Close");
            Label label = new Label("Quiz has been deleted!");

            VBox layout = new VBox(20);
            layout.setAlignment(Pos.CENTER);
            layout.getChildren().addAll(label, close);
            close.setOnAction(e -> window.close());

            window.setScene(new Scene(layout));
            window.showAndWait();

//            Parent root = FXMLLoader.load(getClass().getResource("/View/QuizMasterAddDelete.fxml"));
//            ActivateRegistrationController.getStage().setScene(new Scene(root, 390, 350));
        }
    }

    public void cancel () throws IOException, RuntimeException{
        Parent root = FXMLLoader.load(getClass().getResource("/View/QuizMasterAddDelete.fxml"));
        QuizBeeApplication.getStage().setScene(new Scene(root, 390, 350));
    }

}
