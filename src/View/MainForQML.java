package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainForQML extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("GUIquizMasterLogin.fxml"));
        primaryStage.setTitle("Log-in");
        primaryStage.setScene(new Scene(root, 491, 332));
        primaryStage.show();
	}
	
	public static void main(String args[]) {
		launch(args);
	}
}
