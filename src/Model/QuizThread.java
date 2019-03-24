package Model;

import java.util.*;

/*
    Thread that handles the game logic.
 */
public class QuizThread extends Observable implements Runnable {

    private final int MAX_PARTICIPANTS = 4;
    private final Quiz quiz = new Quiz();

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

    @Override
    public boolean hasChanged() {
        return true;
    }

    public void broadcast(Object message) {
        System.out.println("QuizThread: Broadcasting the following to " + countObservers() + " quiz participants: " + message);
        setChanged();
        notifyObservers(message);
    }

}
