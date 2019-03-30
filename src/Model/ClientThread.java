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
                        reply.append(quizzes.get(i).toString());
                        reply.append("\n");
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
                } else if (messageFromClient.startsWith("JOINQUIZ")) {
                    String[] data = messageFromClient.substring(9).split("\\|");
                    server.addParticipant(this, data[0].trim(), data[1].trim());
                } else if (messageFromClient.startsWith("LEAVEQUIZ")) {
                    String[] data = messageFromClient.substring(10).split("\\|");
                    server.removeParticipant(this, data[0].trim(), data[1].trim());
                } else if (messageFromClient.startsWith("STARTQUIZ")) {
                    server.startQuiz((messageFromClient.substring(9).trim()));
                } else if (messageFromClient.startsWith("FINISHQUIZ")) {
                    String[] data = messageFromClient.substring(11).split("\\|");
                    server.finishQuiz(data[0], data[1]);
                } else if (messageFromClient.startsWith("SCORE")) {
                    String[] data = messageFromClient.substring(6).split("\\|");
                    server.updateScore(data[0], data[1], Integer.parseInt(data[2]));
                } else if (messageFromClient.startsWith("GETPARTICIPANTS")) {
                    TreeMap<String, Integer> participants = server.getParticipants(messageFromClient.substring(16));
                    int ctr = 0;
                    for (String participant : participants.keySet()) {
                        if (ctr > 0) reply.append("|");
                        reply.append(participant);
                        reply.append("|");
                        reply.append(participants.get(participant));
                        ctr++;
                    }
                    reply.append("\n");
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
    }
}
