package application.controller;

import application.model.HighscoreEntry;
import application.model.HighscoreTable;
import application.model.UserStatistic;
import application.model.GameSettings;
import application.model.BoardManager;
import application.model.Difficulty;
import application.view.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Comparator;

public class HighscoreController implements InitializableController {

    private final UserStatistic userStatistic;
    private final Coordinator coordinator;
    private final BoardManager boardManager;
    @FXML
    private Button mainMenuButton;
    @FXML
    private Label DifficultyLabel;
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

    public HighscoreController(UserStatistic userStatistic, Coordinator coordinator, BoardManager boardManager) {
        this.userStatistic = userStatistic;
        this.coordinator = coordinator;
        this.boardManager = boardManager;
    }

    @FXML
    void backToMainMenuPress(ActionEvent event) {
        coordinator.showMainMenu();
    }

    @Override
    public void initializeWithSceneManager(SceneManager sceneManager) {
        // Not used in this context
    }

    public void postInit() {
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnMoves.setCellValueFactory(new PropertyValueFactory<>("moveCount"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnTime.setCellValueFactory(new PropertyValueFactory<>("elapsedTime"));

        populateTable();
        boardIdLabel.setText(String.valueOf(userStatistic.getSelectedBoard().getBoardId()));
        DifficultyLabel.setText("(" + GameSettings.getInstance().getDifficulty() + ")");

        cleanUpSession();
    }

    private void populateTable() {
        HighscoreTable highscoreTable = getHighscoreTable();
        ObservableList<HighscoreEntry> entries = FXCollections.observableArrayList(highscoreTable.getHighscoreList());
        entries.sort(Comparator.comparing(HighscoreEntry::getMoveCount)
                .thenComparing(HighscoreEntry::getElapsedTime));

        tableViewHighScore.setItems(entries);
    }

    private HighscoreTable getHighscoreTable() {
        int boardId = userStatistic.getSelectedBoard().getBoardId();
        Difficulty difficulty = GameSettings.getInstance().getDifficulty();
        return boardManager.getHighScoreTableForBoard(boardId, difficulty);
    }

    private void cleanUpSession() {
        GameSettings.getInstance().resetToDefault();
        userStatistic.setToDefault();
    }
}
