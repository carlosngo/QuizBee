package Model;

import java.util.*;

public class Quiz {
    private final int NUMBER_OF_QUESTIONS = 5;
    private final int MAX_PARTICIPANTS = 4;
    private int quizID;
    private String name;
    private String description;
    private ArrayList<Question> questions;
    private Observable observable;

    public Quiz() {
        quizID = -1;
        name = "";
        description = "";
        questions = new ArrayList<>(5);
        observable = new Observable();
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

    public void startGame() {

    }

    public void addObserver(ClientThread o) {
        observable.addObserver(o);
        if (observable.countObservers() == MAX_PARTICIPANTS) startGame();
    }

    public void removeObserver(ClientThread o) {
        observable.deleteObserver(o);
    }

    public void broadcast(Object message) {
        observable.notifyObservers(message);
    }

    public boolean isValid() {
        if (name.equals("") || description.equals("")) return false;
        for (int i = 0; i < NUMBER_OF_QUESTIONS; i++) if (!questions.get(i).isValid()) return false;
        return true;
    }

}
