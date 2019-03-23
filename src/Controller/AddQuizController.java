package Controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import Model.TempQuiz;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddQuizController {

    @FXML TextField quizNameTxtFld, descriptionTxtFld, questionNameTxtFld, choiceATxtFld, choiceBTxtFld, choiceCTxtFld, choiceDTxtFld;
    @FXML ToggleButton question1, question2, question3, question4, question5;
    @FXML Button saveBtn, discardAllBtn;
    @FXML ComboBox<String> answerCBox;
    TempQuiz quiz;

    public void initialize () {
        // temporary quiz
        quiz = new TempQuiz();

        //initialized to question 1
        question1.setSelected(true);
        answerCBox.getItems().addAll("A", "B", "C", "D");
        answerCBox.getSelectionModel().selectFirst();
    }

    public void question1() {

        question1.setSelected(true);
        question2.setSelected(false);
        question3.setSelected(false);
        question4.setSelected(false);
        question5.setSelected(false);

        questionNameTxtFld.setText(quiz.getQuestion1());
        choiceATxtFld.setText(quiz.getChoiceA1());
        choiceBTxtFld.setText(quiz.getChoiceB1());
        choiceCTxtFld.setText(quiz.getChoiceC1());
        choiceDTxtFld.setText(quiz.getChoiceD1());

    }
    public void question2 () {
        question1.setSelected(false);
        question2.setSelected(true);
        question3.setSelected(false);
        question4.setSelected(false);
        question5.setSelected(false);

        questionNameTxtFld.setText(quiz.getQuestion2());
        choiceATxtFld.setText(quiz.getChoiceA2());
        choiceBTxtFld.setText(quiz.getChoiceB2());
        choiceCTxtFld.setText(quiz.getChoiceC2());
        choiceDTxtFld.setText(quiz.getChoiceD2());
    }

    public void question3 () {
        question1.setSelected(false);
        question2.setSelected(false);
        question3.setSelected(true);
        question4.setSelected(false);
        question5.setSelected(false);

        questionNameTxtFld.setText(quiz.getQuestion3());
        choiceATxtFld.setText(quiz.getChoiceA3());
        choiceBTxtFld.setText(quiz.getChoiceB3());
        choiceCTxtFld.setText(quiz.getChoiceC3());
        choiceDTxtFld.setText(quiz.getChoiceD3());
    }

    public void question4 () {
        question1.setSelected(false);
        question2.setSelected(false);
        question3.setSelected(false);
        question4.setSelected(true);
        question5.setSelected(false);

        questionNameTxtFld.setText(quiz.getQuestion4());
        choiceATxtFld.setText(quiz.getChoiceA4());
        choiceBTxtFld.setText(quiz.getChoiceB4());
        choiceCTxtFld.setText(quiz.getChoiceC4());
        choiceDTxtFld.setText(quiz.getChoiceD4());
    }

    public void question5() {
        question1.setSelected(false);
        question2.setSelected(false);
        question3.setSelected(false);
        question4.setSelected(false);
        question5.setSelected(true);

        questionNameTxtFld.setText(quiz.getQuestion5());
        choiceATxtFld.setText(quiz.getChoiceA5());
        choiceBTxtFld.setText(quiz.getChoiceB5());
        choiceCTxtFld.setText(quiz.getChoiceC5());
        choiceDTxtFld.setText(quiz.getChoiceD5());
    }

    public void discardAll() {
        quizNameTxtFld.clear();
        descriptionTxtFld.clear();
        questionNameTxtFld.clear();
        choiceATxtFld.clear();
        choiceBTxtFld.clear();
        choiceCTxtFld.clear();
        choiceDTxtFld.clear();

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

    public ToggleButton getCurrentToggledButton() {
        if (question1.isSelected())
            return question1;
        else if (question2.isSelected())
            return question2;
        else if (question3.isSelected())
            return question3;
        else if (question4.isSelected())
            return question4;

        return question5;
    }

    public void save() {

        if (quizNameTxtFld.getText() == null || quizNameTxtFld.getText().trim().isEmpty() ||
                descriptionTxtFld.getText()==null || descriptionTxtFld.getText().trim().isEmpty() ||
                questionNameTxtFld.getText()==null  || questionNameTxtFld.getText().trim().isEmpty() ||
                choiceATxtFld.getText()==null || choiceATxtFld.getText().trim().isEmpty() ||
                choiceBTxtFld.getText()==null || choiceBTxtFld.getText().trim().isEmpty() ||
                choiceCTxtFld.getText()==null || choiceCTxtFld.getText().trim().isEmpty() ||
                choiceDTxtFld.getText()==null || choiceDTxtFld.getText().trim().isEmpty() ) {
            Stage window = new Stage();

            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Error!");
            window.setMinWidth(300);

            Label label = new Label("One or more text fields are left blank.");

            VBox layout = new VBox(20);
            layout.getChildren().addAll(label);
            layout.setAlignment(Pos.CENTER);

            window.setScene(new Scene(layout));
            window.showAndWait();
        }
        else {
            if (getCurrentToggledButton().equals(question1)) {
                quiz.setQuestion1(questionNameTxtFld.getText());
                quiz.setChoiceA1(choiceATxtFld.getText());
                quiz.setChoiceB1(choiceBTxtFld.getText());
                quiz.setChoiceC1(choiceCTxtFld.getText());
                quiz.setChoiceD1(choiceDTxtFld.getText());
                quiz.setAnswer1(answerCBox.getSelectionModel().getSelectedItem());
            } else if (getCurrentToggledButton().equals(question2)) {
                quiz.setQuestion2(questionNameTxtFld.getText());
                quiz.setChoiceA2(choiceATxtFld.getText());
                quiz.setChoiceB2(choiceBTxtFld.getText());
                quiz.setChoiceC2(choiceCTxtFld.getText());
                quiz.setChoiceD2(choiceDTxtFld.getText());
                quiz.setAnswer2(answerCBox.getSelectionModel().getSelectedItem());
            } else if (getCurrentToggledButton().equals(question3)) {
                quiz.setQuestion3(questionNameTxtFld.getText());
                quiz.setChoiceA3(choiceATxtFld.getText());
                quiz.setChoiceB3(choiceBTxtFld.getText());
                quiz.setChoiceC3(choiceCTxtFld.getText());
                quiz.setChoiceD3(choiceDTxtFld.getText());
                quiz.setAnswer3(answerCBox.getSelectionModel().getSelectedItem());
            } else if (getCurrentToggledButton().equals(question4)) {
                quiz.setQuestion4(questionNameTxtFld.getText());
                quiz.setChoiceA4(choiceATxtFld.getText());
                quiz.setChoiceB4(choiceBTxtFld.getText());
                quiz.setChoiceC4(choiceCTxtFld.getText());
                quiz.setChoiceD4(choiceDTxtFld.getText());
                quiz.setAnswer4(answerCBox.getSelectionModel().getSelectedItem());
            } else if (getCurrentToggledButton().equals(question5)) {
                quiz.setQuestion5(questionNameTxtFld.getText());
                quiz.setChoiceA5(choiceATxtFld.getText());
                quiz.setChoiceB5(choiceBTxtFld.getText());
                quiz.setChoiceC5(choiceCTxtFld.getText());
                quiz.setChoiceD5(choiceDTxtFld.getText());
                quiz.setAnswer5(answerCBox.getSelectionModel().getSelectedItem());
            }
        }
    }
}
