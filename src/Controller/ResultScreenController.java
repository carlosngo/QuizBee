package Controller;

import java.net.URISyntaxException;
import java.util.*;
import java.io.*;

import Driver.QuizBeeApplication;
import Model.Client;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ResultScreenController {
    @FXML
    private ImageView background;
    @FXML
    private TableView<TableRow> rankingTable;
    @FXML
    private TableColumn rankCol;
    @FXML
    private TableColumn nameCol;
    @FXML
    private TableColumn scoreCol;
    @FXML
    private Button backBtn;

    private Client client = Client.getInstance();
    
    @FXML
    public void back() {
        client.leaveQuiz();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/Lobby.fxml"));
            QuizBeeApplication.getStage().setScene(new Scene(root, 650, 450));
            QuizBeeApplication.getStage().setTitle("Lobby");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void initialize() {
        try {
            background.setImage(new Image(getClass().getClassLoader().getResource(
                    "images/green_and_white.png").toURI().toString()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        rankCol.setCellValueFactory(new PropertyValueFactory<TableRow, String>("rank"));
        nameCol.setCellValueFactory(new PropertyValueFactory<TableRow, String>("name"));
        scoreCol.setCellValueFactory(new PropertyValueFactory<TableRow, String>("score"));
        ObservableList<TableRow> ranking = FXCollections.observableArrayList();
        ArrayList<String> topPlayers = client.getCurrentQuiz().getTopPlayers();
        ArrayList<String> topScores = client.getCurrentQuiz().getTopScores();
        for (int i = 0; i < topPlayers.size(); i++) {
            ranking.add(new TableRow(Integer.toString(i + 1), topPlayers.get(i), topScores.get(i)));
        }
        rankingTable.setItems(ranking);
    }

	public static class TableRow {
        private final SimpleStringProperty rank;
        private final SimpleStringProperty name;
        private final SimpleStringProperty score;

        public TableRow(String rank, String name, String score) {
            this.rank = new SimpleStringProperty(rank);
            this.name = new SimpleStringProperty(name);
            this.score = new SimpleStringProperty(score);
        }

        public String getRank() {
            return rank.get();
        }

        public void setRank(String rank) {
            this.rank.set(rank);
        }

        public String getName() {
            return name.get();
        }

        public void setName(String name) {
            this.name.set(name);
        }

        public String getScore() {
            return score.get();
        }

        public void setScore(String score) {
            this.score.set(score);
        }
    }
}
