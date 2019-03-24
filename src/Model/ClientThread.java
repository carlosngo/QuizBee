package Model;

import java.util.*;
import java.io.*;
import java.net.*;

public class ClientThread implements Runnable, Observer {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ClientThread(Socket socket) {
        this.socket = socket;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("Started thread for client.");
        Server server = Server.getInstance();
        String messageFromClient;
        try {
            while (!server.isShutDown() && (messageFromClient = in.readLine()) != null) {
                System.out.println("Received the message: " + messageFromClient + " from client.");
                StringBuilder reply = new StringBuilder();
                if (messageFromClient.equals("GETQUIZZES")) {
                    ArrayList<Quiz> quizzes = server.getQuizzes();
                    for (int i = 0; i < quizzes.size(); i++) {
                        reply.append(quizzes.get(i).toString());
                        reply.append("\n");
                    }
                } else if (messageFromClient.startsWith("ADDQUIZ")) {
                    Quiz quiz = Quiz.parseQuiz(messageFromClient.substring(7).trim());
                    server.createQuiz(quiz);
                } else if (messageFromClient.startsWith("GETQUESTIONS")) {
                    int quizID = Integer.parseInt(messageFromClient.substring(12).trim());
                    ArrayList<Question> questions = server.getQuestionsOfQuiz(quizID);
                    for (int i = 0; i < questions.size(); i++) {
                        reply.append(questions.get(i).toString());
                        reply.append("\n");
                    }
                } else if (messageFromClient.startsWith("JOINQUIZ")) {
                    server.addParticipant(this, Integer.parseInt(messageFromClient.substring(8).trim()));
                } else if (messageFromClient.startsWith("LEAVEQUIZ")) {
                    server.removeParticipant(this, Integer.parseInt(messageFromClient.substring(9).trim()));
                } else if (messageFromClient.startsWith("STARTQUIZ")) {
                    server.startQuiz(Integer.parseInt(messageFromClient.substring(9).trim()));
                }
                reply.append("END");
                out.println(reply.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            server.unregisterClientThread(this);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        String message = (String) arg;
        if (message.equals("STARTQUIZ")) {

        } else if (message.equals("ENDQUIZ")) {

        }
    }
}
