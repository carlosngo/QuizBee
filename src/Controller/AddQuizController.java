package Controller;

import Driver.QuizBeeApplication;
import Model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javax.swing.*;
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

    private final Client client = Client.getInstance();
    private Quiz quiz;
    private Question question;
//    TempQuiz quiz;
    public void initialize () {
        // temporary quiz
        quiz = new Quiz();
        question = new Question();
//        quiz = new TempQuiz();
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

            JOptionPane.showMessageDialog(null, "One or more text fields are left blank.",
                    "Error!", JOptionPane.ERROR_MESSAGE);
//            Stage window = new Stage();
//
//            window.initModality(Modality.APPLICATION_MODAL);
//            window.setTitle("Error!");
//            window.setMinWidth(300);
//            window.setMinHeight(250);
//
//            Label label = new Label("One or more text fields are left blank.");
//
//            VBox layout = new VBox(20);
//            layout.getChildren().addAll(label);
//            layout.setAlignment(Pos.CENTER);
//
//            window.setScene(new Scene(layout));
//            window.showAndWait();
        }
        else {
            quiz.setName(quizNameTxtFld.getText());
            quiz.setDescription(descriptionTxtFld.getText());

            question = new Question();
            question.setPrompt(questionNameTxtFld1.getText());
            question.setChoice(0, choiceATxtFld1.getText());
            question.setChoice(1, choiceBTxtFld1.getText());
            question.setChoice(2, choiceCTxtFld1.getText());
            question.setChoice(3, choiceDTxtFld1.getText());
            question.setAnswer(answerCBox1.getSelectionModel().getSelectedIndex());
            quiz.setQuestion(0, question);

            question = new Question();
            question.setPrompt(questionNameTxtFld2.getText());
            question.setChoice(0, choiceATxtFld2.getText());
            question.setChoice(1, choiceBTxtFld2.getText());
            question.setChoice(2, choiceCTxtFld2.getText());
            question.setChoice(3, choiceDTxtFld2.getText());
            question.setAnswer(answerCBox2.getSelectionModel().getSelectedIndex());
            quiz.setQuestion(1, question);

            question = new Question();
            question.setPrompt(questionNameTxtFld3.getText());
            question.setChoice(0, choiceATxtFld3.getText());
            question.setChoice(1, choiceBTxtFld3.getText());
            question.setChoice(2, choiceCTxtFld3.getText());
            question.setChoice(3, choiceDTxtFld3.getText());
            question.setAnswer(answerCBox3.getSelectionModel().getSelectedIndex());
            quiz.setQuestion(2, question);

            question = new Question();
            question.setPrompt(questionNameTxtFld4.getText());
            question.setChoice(0, choiceATxtFld4.getText());
            question.setChoice(1, choiceBTxtFld4.getText());
            question.setChoice(2, choiceCTxtFld4.getText());
            question.setChoice(3, choiceDTxtFld4.getText());
            question.setAnswer(answerCBox4.getSelectionModel().getSelectedIndex());
            quiz.setQuestion(3, question);

            question = new Question();
            question.setPrompt(questionNameTxtFld5.getText());
            question.setChoice(0, choiceATxtFld5.getText());
            question.setChoice(1, choiceBTxtFld5.getText());
            question.setChoice(2, choiceCTxtFld5.getText());
            question.setChoice(3, choiceDTxtFld5.getText());
            question.setAnswer(answerCBox5.getSelectionModel().getSelectedIndex());
            quiz.setQuestion(4, question);

            client.addQuiz(quiz);

            JOptionPane.showMessageDialog(null, "Saved successfully!");
            showAddDeleteFXML();
        }
    }

    public void back () {
        int choice = JOptionPane.showConfirmDialog(null,
                "Unsaved data might be lost. Do you want to continue?",
                "Confirm", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) showAddDeleteFXML();

    }

    public void showAddDeleteFXML() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/QuizMasterAddDelete.fxml"));
            QuizBeeApplication.getStage().setScene(new Scene(root, 390, 350));
            discardAll();
        } catch (IOException exception) {
            throw new RuntimeException();
        }
    }

}
