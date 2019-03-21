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
        Parent root = FXMLLoader.load(getClass().getResource("/View/RegistrationGUI.fxml"));
        primaryStage = theStage;
        primaryStage.setTitle("Song Adder");
        primaryStage.setScene(new Scene(root, 471, 357));
        primaryStage.show();

    }

    public static Stage getStage() {
        return primaryStage;
    }


}