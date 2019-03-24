package Model;

import java.util.*;
import java.io.*;
import java.net.*;

public class ClientThread implements Runnable, Observer {
    // Network variables to communicate to client
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    // Information about client being handled
    private String name;
    private int points;

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
//                    reply.append("GETQUIZZES ");
                    ArrayList<Quiz> quizzes = server.getQuizzes();
                    reply.append(quizzes.size());
                    reply.append("\n");
                    for (int i = 0; i < quizzes.size(); i++) {
                        if (i > 0) reply.append("\n");
                        reply.append(quizzes.get(i).toString());
                    }
                } else if (messageFromClient.startsWith("ADDQUIZ")) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(messageFromClient.substring(7).trim());
                    sb.append("\n");
                    messageFromClient = in.readLine();
                    while (!messageFromClient.equals("END")) {
                        sb.append(messageFromClient);
                        sb.append("\n");
                        messageFromClient = in.readLine();
                    }
                    Quiz quiz = Quiz.parseQuiz(sb.toString());
                    server.createQuiz(quiz);
                } else if (messageFromClient.startsWith("GETQUESTIONS")) {
                    int quizID = Integer.parseInt(messageFromClient.substring(12).trim());
                    ArrayList<Question> questions = server.getQuestionsOfQuiz(quizID);
                    for (int i = 0; i < questions.size(); i++) {
                        reply.append(questions.get(i).toString());
                        reply.append("\n");
                    }
                } else if (messageFromClient.startsWith("JOINQUIZ")) {
                    String[] data = messageFromClient.substring(9).split("\\|");
                    server.addParticipant(this, data[0].trim(), data[1].trim());
                } else if (messageFromClient.startsWith("LEAVEQUIZ")) {
                    server.removeParticipant(this, Integer.parseInt(messageFromClient.substring(9).trim()));
                } else if (messageFromClient.startsWith("STARTQUIZ")) {
                    server.startQuiz((messageFromClient.substring(9).trim()));
                }
                reply.append("END");
                System.out.println("Sending the following message to the client: " + reply.toString());
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
        System.out.println("ClientThread: Received the broadcast " + message);
        out.println(message);
        if (message.equals("STARTQUIZ")) {

        } else if (message.equals("ENDQUIZ")) {

        }
    }
}
