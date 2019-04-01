package Controller;

import java.util.*;
import java.io.*;

import Driver.QuizBeeApplication;
import Model.Client;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class ResultScreenController {

    @FXML
    private ListView<String> topPlayers;

    @FXML
    private ListView<String> topScores;
    
    @FXML
    private Button exitGame;

    @FXML
    private Button backBtn;

    private Client client = Client.getInstance();
    
    @FXML
    public void back() {
        client.leaveQuiz();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/GUILobby.fxml"));
            QuizBeeApplication.getStage().setScene(new Scene(root, 553, 357));
            QuizBeeApplication.getStage().setTitle("Lobby");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    @FXML
    void initialize() {
        topPlayers.setItems(FXCollections.observableArrayList(client.getCurrentQuiz().getTopPlayers()));
        topScores.setItems(FXCollections.observableArrayList(client.getCurrentQuiz().getTopScores()));
        topPlayers.setStyle("-fx-font-style: Britannic Bold; -fx-font-size: 15;");
        topScores.setStyle("-fx-font-style: Britannic Bold; -fx-font-size: 15;");

    }


    
	public ListView<String> getTopPlayers() {
		return topPlayers;
	}

	public void setTopPlayers(ListView<String> topPlayers) {
		this.topPlayers = topPlayers;
	}

	public ListView<String> getTopScores() {
		return topScores;
	}

	public void setTopScores(ListView<String> topScores) {
		this.topScores = topScores;
	}

}
