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
        Server server = Server.getInstance();
        String messageFromClient;
        try {
            while (!server.isShutDown() && (messageFromClient = in.readLine()) != null) {
                Object reply;
                if (messageFromClient.equals("GETQUIZZES")) {

                } else if (messageFromClient.startsWith("GETQUESTIONS")) {

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            server.unregisterClientThread(this);
        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
