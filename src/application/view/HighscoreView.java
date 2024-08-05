package application.view;

import application.controller.HighscoreController;
import application.controller.InitializableController;
import application.model.HighscoreEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

/* Hauptverantwortlicher: Matthias Henzel */
/* Mitwirkend: Alex Becker */
public class HighscoreView implements InitializableController {

    private HighscoreController highscoreController;

    @FXML
    private Button mainMenuButton;
    @FXML
    private Label difficultyLabel;
    @FXML
    private Label boardIdLabel;
    @FXML
    private TableColumn<HighscoreEntry, String> columnDate;
    @FXML
    private TableColumn<HighscoreEntry, Integer> columnMoves;
    @FXML
    private TableColumn<HighscoreEntry, String> columnName;
    @FXML
    private TableColumn<HighscoreEntry, String> columnTime;
    @FXML
    private TableView<HighscoreEntry> tableViewHighScore;

    public void setHighscoreController(HighscoreController highscoreController) {
        this.highscoreController = highscoreController;
    }

    @FXML
    void backToMainMenuPress(ActionEvent event) {
        highscoreController.handleBackToMainMenu();
    }

    @Override
    public void initializeWithSceneManager(SceneManager sceneManager) {
    }

    public void initializeTableColumns() {
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnMoves.setCellValueFactory(new PropertyValueFactory<>("moveCount"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnTime.setCellValueFactory(new PropertyValueFactory<>("elapsedTime"));
    }

    public void populateTable(List<HighscoreEntry> highscoreEntries) {
        ObservableList<HighscoreEntry> entries = FXCollections.observableArrayList(highscoreEntries);
        tableViewHighScore.setItems(entries);
    }

    public void updateLabels(String boardId, String difficulty) {
        boardIdLabel.setText(boardId);
        difficultyLabel.setText("(" + difficulty + ")");
    }
}
