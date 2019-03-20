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
    private final QuestionDAO questionDAO = new QuestionDAO();
    private boolean shutdown = false;

    private Server() { }

    public static Server getInstance() { return singleton; }

    public void shutDown() { shutdown = true; }

    public boolean isShutDown() { return shutdown; }

    public void startClientThread(ClientThread thread) {
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
            observable.notifyObservers(message);
        } finally {
            lock.unlock();
        }
    }

    public ArrayList<Quiz> getQuizzes() {
        return quizDAO.listById();
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
                ClientThread thread = new ClientThread(client);
                server.registerClientThread(thread);
                server.startClientThread(thread);
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
