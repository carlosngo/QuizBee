package Model;

import java.util.*;

public class Quiz {
    public static final int NUMBER_OF_QUESTIONS = 5;
    private int quizID;
    private String name;
    private String description;
    private ArrayList<Question> questions;

    public Quiz() {
        quizID = -1;
        name = "";
        description = "";
        questions = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_QUESTIONS; i++) questions.add(new Question());
    }

    public int getQuizID() {
        return quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuestion(int index, Question q) {
        questions.set(index, q);
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public boolean isValid() {
        if (name.equals("") || description.equals("")) return false;
        for (int i = 0; i < NUMBER_OF_QUESTIONS; i++) if (!questions.get(i).isValid()) return false;
        return true;
    }

    // Reverse the toString() function. Given a String representation of a Quiz, build the Quiz object.
    public static Quiz parseQuiz(String s) {
        Quiz quiz = null;
        String[] parts = s.split("|");
        quiz.setQuizID(Integer.parseInt(parts[0]));
        quiz.setName(parts[1]);
        quiz.setDescription(parts[2]);
        return quiz;
    }

    // Include quiz ID, name, and description. Use pipe (|) delimited format to separate fields.
    @Override
    public String toString() {
        String s = "";
        s = s + getQuizID() + "|" + getName()+ "|" + getDescription();
        return s;
    }
}
