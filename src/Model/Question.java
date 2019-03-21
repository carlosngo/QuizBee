package Model;

import java.util.*;

public class Question {
    private final int NUMBER_OF_CHOICES = 4;
    private int questionID;
    private int quizID;
    private String prompt;
    private ArrayList<String> choices;
    private int answer;
    private long startTime;

    public Question() {
        questionID = -1;
        choices = new ArrayList<>(4);
        answer = -1;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public int getQuizID() {
        return quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public ArrayList<String> getChoices() {
        return choices;
    }

    public void setChoice(int index, String choice) {
        choices.set(index, choice);
    }

    public void beginTimer() {
        startTime = Calendar.getInstance().getTimeInMillis();
    }

    public int getPoints() {
        long endTime = Calendar.getInstance().getTimeInMillis();
        return 600 - (int)(endTime - startTime) / 1000 * 10;
    }

    public boolean isCorrect(int answer) {
        return this.answer == answer;
    }

    public boolean isValid() {
        if (prompt.equals("") || answer == -1) return false;
        for (int i = 0; i < NUMBER_OF_CHOICES; i++) if (choices.get(i).equals("")) return false;
        return true;
    }

    // Reverse the toString() function. Given a String representation of a Question, build the Question object.
    public static Question parseQuestion(String s) {
        Question question = null;

        return question;
    }

    // Include all fields EXCEPT startTime. Use pipe (|) delimited format to separate fields.
    @Override
    public String toString() {
        String s = "";
        s = s + "|" + getQuestionID() + "|" + getQuizID() + "|" + getPrompt() + "|" + getAnswer() + "|";
        for(String choice : getChoices())
            s = s + choice + "|";
        return s;
    }
}
