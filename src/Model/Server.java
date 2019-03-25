package Model;

import DAO.*;

import java.util.concurrent.*;
import java.util.*;
import java.util.concurrent.locks.*;
import java.net.*;
import java.io.*;

public class Server {
    public static final int PORT_NUMBER = 5555;
    public static final String IP_ADDRESS = "127.0.0.1";

    private static final Server singleton = new Server();
    private final ExecutorService executor = Executors.newCachedThreadPool();
    private final Observable observable = new Observable();
    private final Lock lock = new ReentrantLock(true);
    private final QuizDAO quizDAO = new QuizDAO();
    private final HashMap<String, QuizThread> quizThreads = new HashMap<>();
    private final QuestionDAO questionDAO = new QuestionDAO();
    private boolean shutdown = false;

    private Server() { }

    public static Server getInstance() { return singleton; }

    public void shutDown() { shutdown = true; }

    public boolean isShutDown() { return shutdown; }

    public void startThread(Runnable thread) {
        executor.submit(thread);
    }

    public void registerClientThread(ClientThread thread) {
        lock.lock();
        try {
            observable.addObserver(thread);
        } finally {
            lock.unlock();
        }
    }

    public void unregisterClientThread(ClientThread thread) {
        lock.lock();
        try {
            observable.deleteObserver(thread);
        } finally {
            lock.unlock();
        }
    }

    public void broadcast(Object message) {
        lock.lock();
        try {
            executor.execute(new MessageBroadcaster(message, observable));
        } finally {
            lock.unlock();
        }
    }

    public void broadcastToParticipants(String quizName, Object message) {
        QuizThread quizThread = quizThreads.get(quizName);
        if (quizThread != null) quizThread.broadcast(message);
    }

    public void addParticipant(ClientThread clientThread, String quizName, String participantName) {
        QuizThread quizThread = quizThreads.get(quizName);
        if (quizThread == null) {
            quizThread = new QuizThread(quizDAO.find(quizName));
            quizThreads.put(quizName, quizThread);
        }
        quizThread.addParticipant(clientThread, participantName);
    }

    public void removeParticipant(ClientThread clientThread, String quizName, String participantName) {
        QuizThread quizThread = quizThreads.get(quizName);
        if (quizThread != null) quizThread.removeParticipant(clientThread, participantName);

    }

    public void createQuiz(Quiz q) {
        try {
            quizDAO.create(q);
        } catch (IllegalArgumentException e) {
            System.out.println("Quiz was not added.");
        }
    }

    public void startQuiz(String quizName) {
        startThread(quizThreads.get(quizName));
    }

    public void finishQuiz(String quizName, String participantName) {
        QuizThread quizThread = quizThreads.get(quizName);
        if (quizThread != null) quizThread.finishQuiz(participantName);
    }

    public void updateScore(String quizName, String participantName, int newScore) {
        QuizThread quizThread = quizThreads.get(quizName);
        if (quizThread != null) quizThread.setScore(participantName, newScore);
    }

    public ArrayList<Quiz> getQuizzes() {
        return quizDAO.listById();
    }


    public Quiz getQuiz(int quizID) {
        return quizDAO.find(quizID);
    }

    public ArrayList<Question> getQuestionsOfQuiz(int quizID) {
        return questionDAO.listByQuiz(quizID);
    }

    public static void main(String[] args) {
        Server server = Server.getInstance();
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT_NUMBER);
            while (!server.isShutDown()) {
                Socket client = serverSocket.accept();
                System.out.println("Client accepted.");
                ClientThread thread = new ClientThread(client);
                server.registerClientThread(thread);
                server.startThread(thread);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
