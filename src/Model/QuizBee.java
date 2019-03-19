package Model;

import java.util.*;

public class QuizBee {
    private final int NUMBER_OF_QUESTIONS = 5;
    private String name;
    private String description;
    private ArrayList<Question> questions;
    private Observable observable;

    public QuizBee() {
        name = "";
        description = "";
        questions = new ArrayList<>(5);
        for (int i = 0; i < NUMBER_OF_QUESTIONS; i++) questions.set(i, new MultipleChoiceQuestion());
        observable = new Observable();
    }

    public void startGame() {

    }

    public void addObserver(ClientThread o) {
        observable.addObserver(o);
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
