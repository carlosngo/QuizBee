package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class ActivateRegistrationController extends Application{

    public static void main(final String[] args) {   /*  */
        launch(args);
    }
    private static Stage primaryStage;
    @Override
    public void start(Stage theStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/GUIwelcomePage.fxml"));
        primaryStage = theStage;
        primaryStage.setTitle("Welcome to Network Quiz Bee!");
        primaryStage.setScene(new Scene(root, 677, 454));
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static Stage getStage() {
        return primaryStage;
    }


}