package Controller;

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

    public void initialize() {
        // Hi + username
        usernameLbl.setText("Hi Carlos");

        // List of quiz names
        quizNameCBox.getItems().addAll("Quiz Name 1", "Quiz Name 2", "Quiz Name 3");
        quizNameCBox.getSelectionModel().selectFirst();
    }

    public void delete() throws IOException {

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

            // then delete whatever is selected

            Parent root = FXMLLoader.load(getClass().getResource("/View/QuizMasterAddDelete.fxml"));
            ActivateRegistrationController.getStage().setScene(new Scene(root, 390, 350));
        }
    }

    public void cancel () throws IOException, RuntimeException{
        Parent root = FXMLLoader.load(getClass().getResource("/View/QuizMasterAddDelete.fxml"));
        ActivateRegistrationController.getStage().setScene(new Scene(root, 390, 350));
    }

}
