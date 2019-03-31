package Controller;

import javafx.fxml.FXML;
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
    private Button tryAgain;
    
    
    @FXML
    void leaveGameExit(MouseEvent event) {

    }

    @FXML
    void tryAgainAndReset(MouseEvent event) {

    }

    @FXML
    void initialize() {
        
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
