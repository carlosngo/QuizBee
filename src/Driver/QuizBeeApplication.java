package Driver;

import Model.Client;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;

public class QuizBeeApplication extends Application {

    private final Client client = Client.getInstance();

    public static void main(final String[] args) {
        launch(args);
    }
    private static Stage primaryStage;
    @Override
    public void start(Stage theStage) throws Exception {
        try {
            client.startConnection();
            Parent root = FXMLLoader.load(getClass().getResource("/View/GUIwelcomePage.fxml"));
            primaryStage = theStage;
            primaryStage.setTitle("Welcome to Network Quiz Bee!");
            primaryStage.setScene(new Scene(root, 677, 454));
            primaryStage.setResizable(false);
            primaryStage.setOnHiding(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
//                                client.leaveQuiz();
                                client.closeConnection();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            System.exit(0);
                        }
                    });
                }
            });
            primaryStage.show();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Could not connect to the server. Closing the program.",
                    "Connection Failed", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    public static Stage getStage() {
        return primaryStage;
    }


}