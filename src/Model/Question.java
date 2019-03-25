package Model;

import java.util.*;

public class Question {
    public static final int NUMBER_OF_CHOICES = 4;
    private int questionID;
    private int quizID;
    private String prompt;
    private ArrayList<String> choices;
    private int answer;
    private long startTime;

    public Question() {
        questionID = -1;
        choices = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_CHOICES; i++) choices.add("");
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
        int weight = 600 - (int)(endTime - startTime) / 1000 * 10;
        return weight > 50 ? weight : 50;
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
        Question question = new Question();
        String[] parts = s.split("\\|");
        question.setQuestionID(Integer.parseInt(parts[0]));
        question.setQuizID(Integer.parseInt(parts[1]));
        question.setPrompt(parts[2]);
        question.setAnswer(Integer.parseInt(parts[3]));
        int index = 0;
        for(int i=4; i<parts.length; i++){
//            System.out.println(parts[i]);
            question.setChoice(index, parts[i]);
            index++;
        }
        return question;
    }

    // Include all fields EXCEPT startTime. Use pipe (|) delimited format to separate fields.
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getQuestionID());
        sb.append("|");
        sb.append(getQuizID());
        sb.append("|");
        sb.append(getPrompt());
        sb.append("|");
        sb.append(getAnswer());
        sb.append("|");
        for (int i = 0; i < NUMBER_OF_CHOICES; i++) {
            if (i > 0) sb.append("|");
            sb.append(choices.get(i));
        }
        return sb.toString();
    }
}
