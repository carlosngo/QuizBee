package Model;

import Controller.*;

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
        sb.append("END");
        outToServer.println(sb.toString());
        try {
            String reply = inFromServer.readLine();
            if (reply.equals("END")) System.out.println("Information was passed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void joinQuiz(String quizName) {
        inGame = true;
        currentQuiz = map.get(quizName);
        currentQuiz.setParticipants(getParticipants());
        currentQuiz.addParticipant(name);
        outToServer.println("JOINQUIZ " + quizName + "|" + name);
        executor.execute(() -> {
            try {
                while (inGame) {
                    String messageFromServer = inFromServer.readLine();
                    System.out.println("Received the following broadcast: " + messageFromServer);
                    if (messageFromServer.startsWith("STARTQUIZ")) {
                        System.out.println("Starting quiz...");
                        administerQuiz(messageFromServer.substring(10));
                    } else if (messageFromServer.startsWith("ENDQUIZ")) {
                        System.out.println("The quiz has finished. Here are the results:");
                        String[] info = messageFromServer.substring(8).split("\\|");
                        for (int i = 0; i < info.length; i += 2) System.out.println(info[i] + ": " + info[i + 1]);
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
                    } else {
                        System.out.println("Ignored the message: " + messageFromServer);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public void leaveQuiz() {
        inGame = false;
        if (currentQuiz != null) outToServer.println("LEAVEQUIZ " + currentQuiz.getName() + "|" + name);
        currentQuiz = null;
    }

    public void startQuiz() {
        outToServer.println("STARTQUIZ " + currentQuiz.getName());
    }

    public void administerQuiz(String quizName) {
        Quiz quiz = map.get(quizName);
        currentAnswer.index = -1;
        executor.submit(() -> {
            for (int i = 0; i < quiz.getQuestions().size(); i++) {

                Question question = quiz.getQuestions().get(i);
                System.out.println(question.getPrompt());
                System.out.println("A. " + question.getChoices().get(0));
                System.out.println("B. " + question.getChoices().get(1));
                System.out.println("C. " + question.getChoices().get(2));
                System.out.println("D. " + question.getChoices().get(3));
                question.beginTimer();
                while (true) {
                    synchronized (currentAnswer) {
                        if (currentAnswer.index != -1) break;
                    }
                }
                if (question.isCorrect(currentAnswer.index)) addPoints(quizName, question.getPoints());
                currentAnswer.index = -1;
            }
            finishQuiz(quizName);
        });
    }

    public void finishQuiz(String quizName) {
        outToServer.println("FINISHQUIZ " + quizName + "|" + name);
    }

    public void addPoints(String quizName, int increment) {
        setPoints(getPoints() + increment);
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

//    public static void main(String[] args) {
//        Client client = new Client();
//        client.setName(JOptionPane.showInputDialog("What is your name?"));
//        JFrame frm = new JFrame("Test");
//        JPanel content = new JPanel();
//        JButton getQuiz = new JButton("Get Quizzes");
//        getQuiz.addActionListener(e -> client.getQuizzes());
//        content.add(getQuiz);
//        JButton joinQuiz = new JButton("Join Quiz");
//        joinQuiz.addActionListener(e -> client.joinQuiz("My Quiz"));
//        content.add(joinQuiz);
//        JButton leaveQuiz = new JButton("Leave Quiz");
//        leaveQuiz.addActionListener(e -> client.leaveQuiz("My Quiz"));
//        content.add(leaveQuiz);
//        JButton startQuiz = new JButton("Start Quiz");
//        startQuiz.addActionListener(e -> client.startQuiz("My Quiz"));
//        content.add(startQuiz);
//        JButton addPoints = new JButton("Add Points");
//        addPoints.addActionListener(e -> client.addPoints("My Quiz", 50));
//        content.add(addPoints);
//        JButton finishQuiz = new JButton("Finish Quiz");
//        finishQuiz.addActionListener(e -> client.finishQuiz("My Quiz"));
//        content.add(finishQuiz);
//        JButton choiceA = new JButton("A");
//        choiceA.addActionListener(e -> client.setChoice(0));
//        content.add(choiceA);
//        JButton choiceB = new JButton("B");
//        choiceB.addActionListener(e -> client.setChoice(1));
//        content.add(choiceB);
//        JButton choiceC = new JButton("C");
//        choiceC.addActionListener(e -> client.setChoice(2));
//        content.add(choiceC);
//        JButton choiceD = new JButton("D");
//        choiceD.addActionListener(e -> client.setChoice(3));
//        content.add(choiceD);
//        frm.setContentPane(content);
//        frm.setVisible(true);
//        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frm.pack();

//        client.startConnection();
//
////        ADDQUIZ Test
//        Quiz q = new Quiz();
//        q.setName("My Quiz");
//        q.setDescription("A quiz made by the developers.");
//        Question q1 = new Question();
//        q1.setPrompt("HTML is the abbreviation for _________");
//        q1.setAnswer(2);
//        q1.setChoice(0, "Hyperlinks and Text Markup Language");
//        q1.setChoice(1, "Home Tool Markup Language");
//        q1.setChoice(2, "Hyper Text Markup Language");
//        q1.setChoice(3, "Hyper Text Markdown Language");
//        Question q2 = new Question();
//        q2.setPrompt("Who is making the Web standards?");
//        q2.setAnswer(3);
//        q2.setChoice(0, "Microsoft");
//        q2.setChoice(1, "Google");
//        q2.setChoice(2, "Google");
//        q2.setChoice(3, "The World Wide Web Consortium");
//        Question q5 = new Question();
//        q5.setPrompt("What is internet?");
//        q5.setAnswer(1);
//        q5.setChoice(0, "A single network");
//        q5.setChoice(1, "A vast collection of different networks");
//        q5.setChoice(2, "An interconnection of local area networks");
//        q5.setChoice(3, "A magical place in our computers.");
//        Question q3 = new Question();
//        q3.setPrompt("Identify which of the services below use both TCP and UDP ports:");
//        q3.setAnswer(1);
//        q3.setChoice(0, "FTP");
//        q3.setChoice(1, "DNS");
//        q3.setChoice(2, "SSH");
//        q3.setChoice(3, "TFTP");
//        Question q4 = new Question();
//        q4.setPrompt("Host A receives a frame and discards it after determining that it is corrupt. At which OSI layer are frames checked for errors?");
//        q4.setAnswer(3);
//        q4.setChoice(0, "Application");
//        q4.setChoice(1, "Network");
//        q4.setChoice(2, "Physical");
//        q4.setChoice(3, "Data-link");
//        q.setQuestion(0, q1);
//        q.setQuestion(1, q2);
//        q.setQuestion(2, q3);
//        q.setQuestion(3, q4);
//        q.setQuestion(4, q5);
//        client.addQuiz(q);
//
//        System.out.println("Quiz successfully added.");
////                GETQUIZ Test
//        ArrayList<Quiz> quizzes = client.getQuizzes();
//        System.out.println("Number of quizzes = " + quizzes.size());
//        client.closeConnection();
//    }
}
