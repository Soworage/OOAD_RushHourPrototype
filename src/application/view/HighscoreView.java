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

/**
 * Ansichtsklasse für die Highscore-Tabelle, die die besten Ergebnisse anzeigt.
 * <p>Hauptverantwortlicher:</p>
 * <ul>
 *     <li>Matthias Henzel</li>
 * </ul>
 * <p>Mitwirkend:</p>
 * <ul>
 *     <li>Alex Becker</li>
 * </ul>
 */
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

    /**
     * Setzt den Highscore-Controller für diese Ansicht.
     *
     * @param highscoreController Der Controller, der die Logik für die Highscore-Tabelle verarbeitet.
     */
    public void setHighscoreController(HighscoreController highscoreController) {
        this.highscoreController = highscoreController;
    }

    /**
     * Behandelt das Klicken auf den Button, um zum Hauptmenü zurückzukehren.
     * Der Controller wird benachrichtigt, um die Rückkehr zum Hauptmenü zu handhaben.
     *
     * @param event Das Ereignis, das beim Klicken auf den Hauptmenü-Button ausgelöst wird.
     */
    @FXML
    void backToMainMenuPress(ActionEvent event) {
        highscoreController.handleBackToMainMenu();
    }

    @Override
    public void initializeWithSceneManager(SceneManager sceneManager) {
        // Methode aus dem Interface, wird hier aber nicht verwendet.
    }

    /**
     * Initialisiert die Spalten der Tabelle mit den entsprechenden Eigenschaften der Highscore-Einträge.
     */
    public void initializeTableColumns() {
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnMoves.setCellValueFactory(new PropertyValueFactory<>("moveCount"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnTime.setCellValueFactory(new PropertyValueFactory<>("elapsedTime"));
    }

    /**
     * Füllt die Highscore-Tabelle mit den übergebenen Highscore-Einträgen.
     *
     * @param highscoreEntries Die Liste der Highscore-Einträge, die in der Tabelle angezeigt werden sollen.
     */
    public void populateTable(List<HighscoreEntry> highscoreEntries) {
        ObservableList<HighscoreEntry> entries = FXCollections.observableArrayList(highscoreEntries);
        tableViewHighScore.setItems(entries);
    }

    /**
     * Aktualisiert die Labels für die Board-ID und die Schwierigkeit.
     *
     * @param boardId    Die ID des Boards, die im Label angezeigt werden soll.
     * @param difficulty Die Schwierigkeit, die im Label angezeigt werden soll.
     */
    public void updateLabels(String boardId, String difficulty) {
        boardIdLabel.setText(boardId);
        difficultyLabel.setText("(" + difficulty + ")");
    }
}
