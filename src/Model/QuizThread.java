package Model;

import java.util.*;

/*
    Thread that handles the game logic.
 */
public class QuizThread implements Runnable {

    private final int MAX_PARTICIPANTS = 4;
    private final Observable observable = new Observable();

    @Override
    public void run() {
        broadcast("STARTQUIZ");


        broadcast("ENDQUIZ");
    }

    public void addObserver(ClientThread o) {
        observable.addObserver(o);
        if (observable.countObservers() == MAX_PARTICIPANTS) Server.getInstance().startThread(this);
    }

    public void removeObserver(ClientThread o) {
        observable.deleteObserver(o);
    }

    public void broadcast(Object message) {
        observable.notifyObservers(message);
    }

}
