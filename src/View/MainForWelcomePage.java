package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainForWelcomePage extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("GUIwelcomePage.fxml"));
        primaryStage.setTitle("Welcome!!");
        primaryStage.setScene(new Scene(root, 677, 454));
        primaryStage.show();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

}
