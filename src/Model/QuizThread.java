package Model;

import java.util.*;

/*
    Thread that handles the game logic.
 */
public class QuizThread implements Runnable {

    private final int MAX_PARTICIPANTS = 4;
    private final Quiz quiz = new Quiz();
    private final Observable observable = new Observable();

    public QuizThread(Quiz quiz) {
        this.quiz.setQuizID(quiz.getQuizID());
        this.quiz.setName(quiz.getName());
        this.quiz.setDescription(quiz.getDescription());
        this.quiz.setQuestions(quiz.getQuestions());
    }

    @Override
    public void run() {
        broadcast("STARTQUIZ");


        broadcast("ENDQUIZ");
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

}
