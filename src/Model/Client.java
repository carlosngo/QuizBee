package Model;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.*;


public class Client {
    // Network variables to communicate with the server
    private Socket socket;
    private PrintWriter outToServer;
    private BufferedReader inFromServer;
    private final ExecutorService executor = Executors.newCachedThreadPool();

    // User variables
    private String name;
    private int points;
    private boolean inGame;

    public Client() {
        try {
            socket = new Socket(Server.IP_ADDRESS, Server.PORT_NUMBER);
            outToServer = new PrintWriter(socket.getOutputStream(), true);
            inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            name = "";
            points = 0;
            inGame = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public void closeConnection() {
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
                quizzes.add(Quiz.parseQuiz(sb.toString()));
            }
            response = inFromServer.readLine();
            if (response.equals("END")) System.out.println("Information was passed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return quizzes;
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
        outToServer.println("JOINQUIZ " + quizName + "|" + name);
        executor.execute(() -> {
            try {
                while (inGame) {
                    String messageFromServer = inFromServer.readLine();
                    System.out.println("Received the following broadcast: " + messageFromServer);
                    if (messageFromServer.equals("STARTQUIZ")) {
                        System.out.println("Starting quiz...");
                    } else if (messageFromServer.equals("ENDQUIZ")) {
                        System.out.println("Finishing quiz...");
                        inGame = false;
                    } else if (messageFromServer.startsWith("JOINQUIZ")) {
                        String newParticipant = messageFromServer.substring(9).trim();
                        System.out.println(newParticipant + " has joined the fray.");
                    } else if (messageFromServer.startsWith("LEAVEQUIZ")) {
                        String coward = messageFromServer.substring(10).trim();
                        System.out.println(coward + " has left the game.");
                    } else if (messageFromServer.equals("ENDQUIZ")) {
                        System.out.println("Finishing quiz...");
                        inGame = false;
                    } else {
                        System.out.println("Ignored the message: " + messageFromServer);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public void leaveQuiz(String quizName) {
        inGame = false;
    }

    public void startQuiz(String quizName) {
        outToServer.println("STARTQUIZ " + quizName);
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.setName("Carlos");
        JFrame frm = new JFrame("Test");
        JPanel content = new JPanel();
        JButton getQuiz = new JButton("Get Quizzes");
        getQuiz.addActionListener(e -> {
            client.getQuizzes();
        });
        content.add(getQuiz);
        JButton joinQuiz = new JButton("Join Quiz");
        joinQuiz.addActionListener(e -> {
          client.joinQuiz("My Quiz");
        });
        content.add(joinQuiz);
        JButton startQuiz = new JButton("Start Quiz");
        startQuiz.addActionListener(e -> {
            client.startQuiz("My Quiz");
        });
        content.add(startQuiz);
        frm.setContentPane(content);
        frm.setVisible(true);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.pack();

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
    }
}
