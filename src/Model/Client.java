package Model;

import java.io.*;
import java.net.*;
import java.util.*;

public abstract class Client {
    private Socket socket;
    private PrintWriter outToServer;
    private BufferedReader inFromServer;
    private BufferedReader inFromUser;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public PrintWriter getOutToServer() {
        return outToServer;
    }

    public void setOutToServer(PrintWriter outToServer) {
        this.outToServer = outToServer;
    }

    public BufferedReader getInFromServer() {
        return inFromServer;
    }

    public void setInFromServer(BufferedReader inFromServer) {
        this.inFromServer = inFromServer;
    }

    public void startConnection() {
        try {
            socket = new Socket(Server.IP_ADDRESS, Server.PORT_NUMBER);
            outToServer = new PrintWriter(socket.getOutputStream(), true);
            inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String messageFromServer;
//            while ((messageFromServer = inFromServer.readLine()) != null) {
//
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            while (!response.equals("END")) {
//                quizzes.add(Quiz.parseQuiz(response));
                System.out.println(response);
            }
        } catch (IOException e) {

        }
        return quizzes;
    }

    public void addQuiz(Quiz q) {
        outToServer.println("ADDQUIZ " + q);
    }

    public static void main(String[] args) {
        Participant client = new Participant();
        client.startConnection();
        Quiz q = new Quiz();
        q.setName("My Quiz");
        q.setDescription("A quiz made by the developers.");
        Question q1 = new Question();
        q1.setPrompt("HTML is the abbreviation for _________");
        q1.setAnswer(2);
        q1.setChoice(0, "Hyperlinks and Text Markup Language");
        q1.setChoice(1, "Home Tool Markup Language");
        q1.setChoice(2, "Hyper Text Markup Language");
        q1.setChoice(3, "Hyper Text Markdown Language");
        Question q2 = new Question();
        q2.setPrompt("Who is making the Web standards?");
        q2.setAnswer(3);
        q2.setChoice(0, "Microsoft");
        q2.setChoice(1, "Google");
        q2.setChoice(2, "Google");
        q2.setChoice(3, "The World Wide Web Consortium");
        Question q5 = new Question();
        q5.setPrompt("What is internet?");
        q5.setAnswer(1);
        q5.setChoice(0, "A single network");
        q5.setChoice(1, "A vast collection of different networks");
        q5.setChoice(2, "An interconnection of local area networks");
        q5.setChoice(3, "A magical place in our computers.");
        Question q3 = new Question();
        q3.setPrompt("Identify which of the services below use both TCP and UDP ports:");
        q3.setAnswer(1);
        q3.setChoice(0, "FTP");
        q3.setChoice(1, "DNS");
        q3.setChoice(2, "SSH");
        q3.setChoice(3, "TFTP");
        Question q4 = new Question();
        q4.setPrompt("Host A receives a frame and discards it after determining that it is corrupt. At which OSI layer are frames checked for errors?");
        q4.setAnswer(3);
        q4.setChoice(0, "Application");
        q4.setChoice(1, "Network");
        q4.setChoice(2, "Physical");
        q4.setChoice(3, "Data-link");
        q.setQuestion(0, q1);
        q.setQuestion(1, q2);
        q.setQuestion(2, q3);
        q.setQuestion(3, q4);
        q.setQuestion(4, q5);
        System.out.println("Adding quiz: " + q);
        client.addQuiz(q);
        System.out.println("Quiz successfully added.");
    }
}
