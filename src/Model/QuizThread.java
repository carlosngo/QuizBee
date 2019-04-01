package Model;

import java.util.*;

/*
    Thread that handles the game logic.
 */
public class QuizThread extends Observable implements Runnable {


    private final Quiz quiz = new Quiz();
    private TreeMap<String, Integer> scores = new TreeMap<>();
    private TreeMap<String, Boolean> clientStates = new TreeMap<>();
    private boolean hasStarted = false;

    public QuizThread(Quiz quiz) {
        this.quiz.setQuizID(quiz.getQuizID());
        this.quiz.setName(quiz.getName());
        this.quiz.setDescription(quiz.getDescription());
        this.quiz.setQuestions(quiz.getQuestions());
    }

    @Override
    public void run() {
        broadcast("STARTQUIZ " + quiz.getName());
        hasStarted = true;
        while (true) {
            boolean isFinished = true;
            for (String name : clientStates.keySet()) if (!clientStates.get(name)) isFinished = false;
            if (isFinished) break;
        }
        ArrayList<Standing> ranking = new ArrayList<>();
        for (String participant : scores.keySet()) {
            Standing s = new Standing();
            s.name = participant;
            s.score = scores.get(participant);
            ranking.add(s);
        }
        Collections.sort(ranking);
        StringBuilder sb = new StringBuilder();
        sb.append("ENDQUIZ ");
        int ctr = 0;
        for (int i = 0; i < ranking.size(); i++) {
            if (ctr > 0) sb.append("|");
            sb.append(ranking.get(i).name);
            sb.append("|");
            sb.append(ranking.get(i).score);
            ctr++;
        }
        broadcast(sb.toString());
        hasStarted = false;
        deleteObservers();
        // append results
    }

    public boolean hasStarted() { return hasStarted; }

    public boolean isFull() { return Quiz.MAX_NUMBER_OF_PARTICIPANTS == countObservers(); }

    public void finishQuiz(String clientName) {
        clientStates.put(clientName, true);
    }

    public TreeMap<String, Integer> getParticipants() {
        return scores;
    }

    public void addParticipant(ClientThread clientThread, String participantName) {
        scores.put(participantName, 0);
        clientStates.put(participantName, false);
        broadcast("JOINQUIZ " + participantName);
        addObserver(clientThread);
    }

    public void removeParticipant(ClientThread clientThread, String participantName) {
        scores.remove(participantName);
        clientStates.remove(participantName);
        deleteObserver(clientThread);
        broadcast("LEAVEQUIZ " + participantName);
    }

    public void setScore(String participantName, int newScore) {
        scores.put(participantName, newScore);
        broadcast("SCORE " + participantName + "|" + newScore);
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

    private static class Standing implements Comparable<Standing> {
        String name;
        int score;

        @Override
        public int compareTo(Standing o) {
            int result = Integer.compare(o.score, score);
            if (result == 0) return name.compareTo(o.name);
            return result;
        }
    }
}
