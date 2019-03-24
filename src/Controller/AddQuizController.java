package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import Model.TempQuiz;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AddQuizController {

    @FXML TextField quizNameTxtFld, descriptionTxtFld;
    @FXML TextField questionNameTxtFld1, choiceATxtFld1, choiceBTxtFld1, choiceCTxtFld1, choiceDTxtFld1;
    @FXML TextField questionNameTxtFld2, choiceATxtFld2, choiceBTxtFld2, choiceCTxtFld2, choiceDTxtFld2;
    @FXML TextField questionNameTxtFld3, choiceATxtFld3, choiceBTxtFld3, choiceCTxtFld3, choiceDTxtFld3;
    @FXML TextField questionNameTxtFld4, choiceATxtFld4, choiceBTxtFld4, choiceCTxtFld4, choiceDTxtFld4;
    @FXML TextField questionNameTxtFld5, choiceATxtFld5, choiceBTxtFld5, choiceCTxtFld5, choiceDTxtFld5;
    @FXML Tab question1, question2, question3, question4, question5;
    @FXML ComboBox<String> answerCBox1, answerCBox2, answerCBox3, answerCBox4, answerCBox5;
    @FXML Button saveBtn, discardAllBtn;
    TempQuiz quiz;

    public void initialize () {
        // temporary quiz
        quiz = new TempQuiz();

        //initialized to question 1
        answerCBox1.getItems().addAll("A", "B", "C", "D");
        answerCBox1.getSelectionModel().selectFirst();
        answerCBox2.getItems().addAll("A", "B", "C", "D");
        answerCBox2.getSelectionModel().selectFirst();
        answerCBox3.getItems().addAll("A", "B", "C", "D");
        answerCBox3.getSelectionModel().selectFirst();
        answerCBox4.getItems().addAll("A", "B", "C", "D");
        answerCBox4.getSelectionModel().selectFirst();
        answerCBox5.getItems().addAll("A", "B", "C", "D");
        answerCBox5.getSelectionModel().selectFirst();
        discardAll();
    }

    public void discardAll() {
        quizNameTxtFld.clear();
        descriptionTxtFld.clear();
        questionNameTxtFld1.clear();
        questionNameTxtFld2.clear();
        questionNameTxtFld3.clear();
        questionNameTxtFld4.clear();
        questionNameTxtFld5.clear();
        choiceATxtFld1.clear();
        choiceATxtFld2.clear();
        choiceATxtFld3.clear();
        choiceATxtFld4.clear();
        choiceATxtFld5.clear();
        choiceBTxtFld1.clear();
        choiceBTxtFld2.clear();
        choiceBTxtFld3.clear();
        choiceBTxtFld4.clear();
        choiceBTxtFld5.clear();
        choiceCTxtFld1.clear();
        choiceCTxtFld2.clear();
        choiceCTxtFld3.clear();
        choiceCTxtFld4.clear();
        choiceCTxtFld5.clear();
        choiceDTxtFld1.clear();
        choiceDTxtFld2.clear();
        choiceDTxtFld3.clear();
        choiceDTxtFld4.clear();
        choiceDTxtFld5.clear();

        quiz.setQuizName("");
        quiz.setDescription("");
        quiz.setQuestion1("");
        quiz.setQuestion2("");
        quiz.setQuestion3("");
        quiz.setQuestion4("");
        quiz.setQuestion5("");
        quiz.setChoiceA1("");
        quiz.setChoiceA2("");
        quiz.setChoiceA3("");
        quiz.setChoiceA4("");
        quiz.setChoiceA5("");
        quiz.setChoiceB1("");
        quiz.setChoiceB2("");
        quiz.setChoiceB3("");
        quiz.setChoiceB4("");
        quiz.setChoiceB5("");
        quiz.setChoiceC1("");
        quiz.setChoiceC2("");
        quiz.setChoiceC3("");
        quiz.setChoiceC4("");
        quiz.setChoiceC5("");
        quiz.setChoiceD1("");
        quiz.setChoiceD2("");
        quiz.setChoiceD3("");
        quiz.setChoiceD4("");
        quiz.setChoiceD5("");
        quiz.setAnswer1("");
        quiz.setAnswer2("");
        quiz.setAnswer3("");
        quiz.setAnswer4("");
        quiz.setAnswer5("");

    }

    public void save() {

        if (quizNameTxtFld.getText() == null || quizNameTxtFld.getText().trim().isEmpty() ||
                descriptionTxtFld.getText()==null || descriptionTxtFld.getText().trim().isEmpty() ||
                questionNameTxtFld1.getText()==null  || questionNameTxtFld1.getText().trim().isEmpty() ||
                questionNameTxtFld2.getText()==null  || questionNameTxtFld2.getText().trim().isEmpty() ||
                questionNameTxtFld3.getText()==null  || questionNameTxtFld3.getText().trim().isEmpty() ||
                questionNameTxtFld4.getText()==null  || questionNameTxtFld4.getText().trim().isEmpty() ||
                questionNameTxtFld5.getText()==null  || questionNameTxtFld5.getText().trim().isEmpty() ||
                choiceATxtFld1.getText()==null || choiceATxtFld1.getText().trim().isEmpty() ||
                choiceATxtFld2.getText()==null || choiceATxtFld2.getText().trim().isEmpty() ||
                choiceATxtFld3.getText()==null || choiceATxtFld3.getText().trim().isEmpty() ||
                choiceATxtFld4.getText()==null || choiceATxtFld4.getText().trim().isEmpty() ||
                choiceATxtFld5.getText()==null || choiceATxtFld5.getText().trim().isEmpty() ||
                choiceBTxtFld1.getText()==null || choiceBTxtFld1.getText().trim().isEmpty() ||
                choiceBTxtFld2.getText()==null || choiceBTxtFld2.getText().trim().isEmpty() ||
                choiceBTxtFld3.getText()==null || choiceBTxtFld3.getText().trim().isEmpty() ||
                choiceBTxtFld4.getText()==null || choiceBTxtFld4.getText().trim().isEmpty() ||
                choiceBTxtFld5.getText()==null || choiceBTxtFld5.getText().trim().isEmpty() ||
                choiceCTxtFld1.getText()==null || choiceCTxtFld1.getText().trim().isEmpty() ||
                choiceCTxtFld2.getText()==null || choiceCTxtFld2.getText().trim().isEmpty() ||
                choiceCTxtFld3.getText()==null || choiceCTxtFld3.getText().trim().isEmpty() ||
                choiceCTxtFld4.getText()==null || choiceCTxtFld4.getText().trim().isEmpty() ||
                choiceCTxtFld5.getText()==null || choiceCTxtFld5.getText().trim().isEmpty() ||
                choiceDTxtFld1.getText()==null || choiceDTxtFld1.getText().trim().isEmpty() ||
                choiceDTxtFld2.getText()==null || choiceDTxtFld2.getText().trim().isEmpty() ||
                choiceDTxtFld3.getText()==null || choiceDTxtFld3.getText().trim().isEmpty() ||
                choiceDTxtFld4.getText()==null || choiceDTxtFld4.getText().trim().isEmpty() ||
                choiceDTxtFld5.getText()==null || choiceDTxtFld5.getText().trim().isEmpty() ) {

            Stage window = new Stage();

            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Error!");
            window.setMinWidth(300);
            window.setMinHeight(250);

            Label label = new Label("One or more text fields are left blank.");

            VBox layout = new VBox(20);
            layout.getChildren().addAll(label);
            layout.setAlignment(Pos.CENTER);

            window.setScene(new Scene(layout));
            window.showAndWait();
        }
        else {
                quiz.setQuizName(quizNameTxtFld.getText());
                quiz.setDescription(descriptionTxtFld.getText());

                quiz.setQuestion1(questionNameTxtFld1.getText());
                quiz.setChoiceA1(choiceATxtFld1.getText());
                quiz.setChoiceB1(choiceBTxtFld1.getText());
                quiz.setChoiceC1(choiceCTxtFld1.getText());
                quiz.setChoiceD1(choiceDTxtFld1.getText());
                quiz.setAnswer1(answerCBox1.getSelectionModel().getSelectedItem());

                quiz.setQuestion2(questionNameTxtFld2.getText());
                quiz.setChoiceA2(choiceATxtFld2.getText());
                quiz.setChoiceB2(choiceBTxtFld2.getText());
                quiz.setChoiceC2(choiceCTxtFld2.getText());
                quiz.setChoiceD2(choiceDTxtFld2.getText());
                quiz.setAnswer2(answerCBox2.getSelectionModel().getSelectedItem());

                quiz.setQuestion3(questionNameTxtFld3.getText());
                quiz.setChoiceA3(choiceATxtFld3.getText());
                quiz.setChoiceB3(choiceBTxtFld3.getText());
                quiz.setChoiceC3(choiceCTxtFld3.getText());
                quiz.setChoiceD3(choiceDTxtFld3.getText());
                quiz.setAnswer3(answerCBox3.getSelectionModel().getSelectedItem());

                quiz.setQuestion4(questionNameTxtFld4.getText());
                quiz.setChoiceA4(choiceATxtFld4.getText());
                quiz.setChoiceB4(choiceBTxtFld4.getText());
                quiz.setChoiceC4(choiceCTxtFld4.getText());
                quiz.setChoiceD4(choiceDTxtFld4.getText());
                quiz.setAnswer4(answerCBox4.getSelectionModel().getSelectedItem());

                quiz.setQuestion5(questionNameTxtFld5.getText());
                quiz.setChoiceA5(choiceATxtFld5.getText());
                quiz.setChoiceB5(choiceBTxtFld5.getText());
                quiz.setChoiceC5(choiceCTxtFld5.getText());
                quiz.setChoiceD5(choiceDTxtFld5.getText());
                quiz.setAnswer5(answerCBox5.getSelectionModel().getSelectedItem());

                Stage window = new Stage();

                window.initModality(Modality.APPLICATION_MODAL);
                window.setTitle("Success!");
                window.setMinWidth(250);
                window.setMinHeight(250);

                Label label = new Label("Saved successfully!");

                VBox layout = new VBox(10);
                layout.getChildren().addAll(label);
                layout.setAlignment(Pos.CENTER);

                window.setScene(new Scene(layout));
                window.showAndWait();
        }
    }

    public void back () {
        System.out.println("Quiz Name TExtField: " + quizNameTxtFld.getText());
        System.out.println("Quiz Name: " + quiz.getQuizName());

        // this means hindi nakapag save yung quiz master, kasi he wont be able to save din if any text field is left blank
        if (quiz.getQuizName().length()==0 ) {
            Stage window = new Stage();

            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Warning!");
            window.setMinWidth(250);
            window.setMinHeight(250);

            Label label = new Label("Quiz incomplete. If you go back now, everything will be discarded. Are you sure you want to exit?");
            Button confirm = new Button("Confirm");
            Button cancel = new Button("Cancel");

            VBox layout = new VBox(25);
            layout.getChildren().addAll(label, confirm, cancel);
            layout.setAlignment(Pos.CENTER);

            confirm.setOnAction(e -> {
                try {
                    discardAll();
                    Parent root = FXMLLoader.load(getClass().getResource("/View/QuizMasterAddDelete.fxml"));
                    ActivateRegistrationController.getStage().setScene(new Scene(root, 390, 350));
                    window.close();
                } catch (IOException exception) {
                    throw new RuntimeException();
                }
            });

            cancel.setOnAction(e -> { window.close(); } );

            window.setScene(new Scene(layout));
            window.showAndWait();
        }

        else {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/View/QuizMasterAddDelete.fxml"));
                ActivateRegistrationController.getStage().setScene(new Scene(root, 390, 350));
                discardAll();
            } catch (IOException exception) {
                throw new RuntimeException();
            }
        }

    }

}
