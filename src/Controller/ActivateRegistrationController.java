package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ActivateRegistrationController extends Application{

    public static void main(String[] args) {   /*  */
        launch(args);
    }

    @Override
    public void start(Stage theStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/RegistrationGUI.fxml"));
        theStage.setTitle("Song Adder");
        theStage.setScene(new Scene(root, 471, 357));
        theStage.show();

    }

}