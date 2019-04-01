/**
 * Sample Skeleton for 'Lobby.fxml' Controller Class
 */

package Controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

import Driver.QuizBeeApplication;
import Model.*;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javax.swing.*;

public class LobbyController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    @FXML
    private ImageView background;
    @FXML
    private TableView<TableRow> quizTableView;
    @FXML
    private TableColumn nameCol;
    @FXML
    private TableColumn descriptionCol;
    @FXML
    private TableColumn statusCol;
    @FXML
    private TableColumn numPlayersCol;

    @FXML
    private Button refreshButton;

    @FXML
    private Button backButton;

    @FXML
    private Button joinQuizButton;

    private Client client = Client.getInstance();
    
    public String getSelectedQuiz() {
    	return quizTableView.getSelectionModel().getSelectedItem().getName();
    }

    @FXML
    void readQuizName(MouseEvent e) {  //when the ListView is clicked
    	if (quizTableView.getSelectionModel().getSelectedItem() != null) joinQuizButton.setDisable(false);
    }

    @FXML
    void refresh() {  //refresh button
        quizTableView.setItems(FXCollections.observableArrayList(getQuizzes()));
    }

    @FXML
    void joinQuiz() throws IOException, RuntimeException { //edit
    	if (!client.joinQuiz(getSelectedQuiz())) return;
    	Parent root = FXMLLoader.load(getClass().getResource("/View/Room.fxml"));
        QuizBeeApplication.getStage().setScene(new Scene(root, 553, 357));
        QuizBeeApplication.getStage().setTitle("Lobby");
    }
    
    public ArrayList<TableRow> getQuizzes() {
        ArrayList<TableRow> quizNames = new ArrayList<>();

        for (Quiz quiz : client.getQuizzes()) {
            quizNames.add(new TableRow(quiz.getName(), quiz.getDescription(), client.getStatus(quiz.getName()),
                    client.getNumberOfPlayers(quiz.getName()) + "/" + Quiz.MAX_NUMBER_OF_PARTICIPANTS));
        }
        return quizNames;
    }

    @FXML
    void exit() {
        int choice = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to exit?",
                "Confirm", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            try {
                client.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            QuizBeeApplication.getStage().close();
        };

    }

    // This method is called by the FXMLLoader when initialization is complete
    public void initialize() {
        try {
            background.setImage(new Image(getClass().getClassLoader().getResource(
                    "images/green_and_white.png").toURI().toString()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        nameCol.setCellValueFactory(new PropertyValueFactory<TableRow, String>("name"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<TableRow, String>("description"));
        statusCol.setCellValueFactory(new PropertyValueFactory<TableRow, String>("status"));
        numPlayersCol.setCellValueFactory(new PropertyValueFactory<TableRow, String>("players"));
        quizTableView.setItems(FXCollections.observableArrayList(getQuizzes()));
        joinQuizButton.setDisable(true);
    }

    public static class TableRow {
        private final SimpleStringProperty name;
        private final SimpleStringProperty description;
        private final SimpleStringProperty status;
        private final SimpleStringProperty players;

        public TableRow(String name, String description, String status, String players) {
            this.name = new SimpleStringProperty(name);
            this.description = new SimpleStringProperty(description);
            this.status = new SimpleStringProperty(status);
            this.players = new SimpleStringProperty(players);
        }

        public String getName() {
            return name.get();
        }

        public void setName(String name) {
            this.name.set(name);
        }

        public String getDescription() {
            return description.get();
        }

        public void setDescription(String description) {
            this.description.set(description);
        }

        public String getStatus() {
            return status.get();
        }

        public void setStatus(String status) {
            this.status.set(status);
        }

        public String getPlayers() {
            return players.get();
        }

        public void setPlayers(String players) {
            this.players.set(players);
        }
    }
}
