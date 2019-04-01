package Model;

import Controller.*;
import Driver.QuizBeeApplication;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.*;


public class Client {
    private static final Client singleton = new Client();
    // Network variables to communicate with the server
    private Socket socket;
    private PrintWriter outToServer;
    private BufferedReader inFromServer;
    private final ExecutorService executor = Executors.newCachedThreadPool();

    // User variables
    private String name;
    private int points;
    private final Answer currentAnswer = new Answer();
    private Quiz currentQuiz;
    private boolean inGame;
    private HashMap<String, Quiz> map;

    // Controllers
    private LobbyController lc;
    private QuizBController qbc;

    private Client() { }

    public static Client getInstance() { return singleton; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Quiz getCurrentQuiz() {
        return currentQuiz;
    }

    public void setCurrentQuiz(Quiz quiz) {
        currentQuiz = quiz;
    }

    public void setLobbyController(LobbyController lc) {
        this.lc = lc;
    }

    public void setQuizBController(QuizBController qbc) {
        this.qbc = qbc;
    }

    public void startConnection() throws IOException {
        socket = new Socket(Server.IP_ADDRESS, Server.PORT_NUMBER);
        outToServer = new PrintWriter(socket.getOutputStream(), true);
        inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        name = "";
        points = 0;
        inGame = false;
        map = new HashMap<>();
    }

    public void closeConnection() throws IOException {
        try {
            outToServer.close();
            inFromServer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Quiz> getQuizzes() {
        ArrayList<Quiz> quizzes = new ArrayList<>();
        outToServer.println("GETQUIZZES");
        try {
            String response = inFromServer.readLine();
            System.out.println("Received the following response: " + response);
            int numOfQuizzes = Integer.parseInt(response);
            for (int i = 0; i < numOfQuizzes; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < 6; j++) {
                    response = inFromServer.readLine();
                    sb.append(response);
                    sb.append("\n");
                }
                System.out.println(sb.toString());
                Quiz quiz = Quiz.parseQuiz(sb.toString());
                map.put(quiz.getName(), quiz);
                quizzes.add(quiz);
            }
            response = inFromServer.readLine();
            if (response.equals("END")) System.out.println("Information was passed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return quizzes;
    }

    public TreeMap<String, Integer> getParticipants() {
        TreeMap<String, Integer> participants = new TreeMap<>();
        outToServer.println("GETPARTICIPANTS " + currentQuiz.getName());
        try {
            String reply = inFromServer.readLine();
            if (!reply.equals("")) {
                String[] data = reply.split("\\|");
                for (int i = 0; i < data.length; i += 2) {
                    participants.put(data[i], Integer.parseInt(data[i + 1]));
                }
            }
            reply = inFromServer.readLine();
            if (reply.equals("END")) System.out.println("Information was passed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return participants;
    }

    public void addQuiz(Quiz q) {
        StringBuilder sb = new StringBuilder();
        sb.append("ADDQUIZ ");
        sb.append(q);
        sb.append("\nEND");
        outToServer.println(sb.toString());

        try {
            String reply = inFromServer.readLine();
            if (reply.equals("END")) System.out.println("Information was passed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean joinQuiz(String quizName) {
        currentQuiz = map.get(quizName);
        currentQuiz.setParticipants(getParticipants());
        outToServer.println("JOINQUIZ " + quizName + "|" + name);
        String reply;
        try {
            reply = inFromServer.readLine();
            if (reply.equals("NO")) {
                reply = inFromServer.readLine();
                JOptionPane.showMessageDialog(null, "The selected quiz is ongoing or full.");
                if (reply.equals("END")) System.out.println("Information was passed successfully.");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        inGame = true;
        currentQuiz.addParticipant(name);
        executor.execute(() -> {
            try {
                while (inGame) {
                    String messageFromServer = inFromServer.readLine();
                    System.out.println("Received the following broadcast: " + messageFromServer);
                    if (messageFromServer.startsWith("STARTQUIZ")) {
                        System.out.println("Starting quiz...");
                        JOptionPane.showMessageDialog(null, "Quiz is starting...");
                        lc.switchNextScene();
                    } else if (messageFromServer.startsWith("ENDQUIZ")) {
                        System.out.println("The quiz has finished. Here are the results:");
                        qbc.switchNextScene();
                        String[] info = messageFromServer.substring(8).split("\\|");
                        for (int i = 0; i < info.length; i += 2) {
                            currentQuiz.getTopPlayers().add("#" + (i / 2 + 1) + " " + info[i]);
                            currentQuiz.getTopScores().add(info[i + 1]);
                        }
                        messageFromServer = inFromServer.readLine();
                        if (messageFromServer.equals("END")) System.out.println("Quiz has successfully terminated.");
                        inGame = false;
                    } else if (messageFromServer.startsWith("JOINQUIZ")) {
                        String newParticipant = messageFromServer.substring(9).trim();
                        currentQuiz.addParticipant(newParticipant);
                        System.out.println(newParticipant + " has joined the quiz. There are now " +
                                currentQuiz.getParticipants().size() + " participants.");
                        lc.update();
                    } else if (messageFromServer.startsWith("LEAVEQUIZ")) {
                        String coward = messageFromServer.substring(10).trim();
                        currentQuiz.removeParticipant(coward);
                        System.out.println(coward + " has left the quiz. There are now " +
                                currentQuiz.getParticipants().size() + " participants.");
                        lc.update();
                    } else if (messageFromServer.startsWith("SCORE")) {
                        String[] info = messageFromServer.substring(6).split("\\|");
                        System.out.println("The score of contestant " + info[0] + " is now " + Integer.parseInt(info[1]));
                        currentQuiz.setScore(info[0], Integer.parseInt(info[1]));
                        qbc.update(currentQuiz.getParticipants());
                    } else {
                        System.out.println("Ignored the message: " + messageFromServer);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        try {
            reply = inFromServer.readLine();
            if (reply.equals("END")) System.out.println("Information was passed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void leaveQuiz() {
        inGame = false;
        if (currentQuiz != null) {
            outToServer.println("LEAVEQUIZ " + currentQuiz.getName() + "|" + name);
            currentQuiz.removeParticipant(name);
        }
        currentQuiz = null;
    }

    public void startQuiz() {
        outToServer.println("STARTQUIZ " + currentQuiz.getName());
    }

    public void administerQuiz() {
        currentAnswer.index = -1;
        setPoints(0);
        qbc.update(currentQuiz.getParticipants());
        executor.submit(() -> {
            currentQuiz.setStartTime(System.currentTimeMillis());
            for (int i = 0; i < currentQuiz.getQuestions().size(); i++) {

                Question question = currentQuiz.getQuestions().get(i);
                System.out.println("Updating View " + qbc);
                qbc.update(question);
                question.beginTimer();
                while (true) {
                    synchronized (currentAnswer) {
                        if (currentAnswer.index != -1) break;
                    }
                    if (System.currentTimeMillis() - currentQuiz.getStartTime() == 300000) { // if 5 minutes has passed, gg
                        finishQuiz();
                    }
                }
                if (question.isCorrect(currentAnswer.index)) addPoints(currentQuiz.getName(), question.getPoints());
                currentAnswer.index = -1;
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            finishQuiz();
        });
    }

    public void finishQuiz() {
        qbc.disableControls();
        JOptionPane.showMessageDialog(null,
                "You have finished the quiz. Please standby until all participants finish the quiz...");
        outToServer.println("FINISHQUIZ " + currentQuiz.getName() + "|" + name);
    }

    public void addPoints(String quizName, int increment) {
        setPoints(getPoints() + increment);
        currentQuiz.setScore(name, getPoints());
        outToServer.println("SCORE " + quizName + "|" + name + "|" + getPoints());
    }

    public void setChoice(int choice) {
        synchronized (currentAnswer) {
            currentAnswer.index = choice;
        }
        System.out.println("Current answer is now: " + choice);
    }

    private class Answer {
        int index;
    }
}
