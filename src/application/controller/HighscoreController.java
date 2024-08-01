package application.controller;

import application.model.*;
import application.view.UserInterface;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import java.util.Comparator;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;


public class HighscoreController {
    private HighscoreTable highscoreTable;
    private UserInterface userInterface;

    public HighscoreTable getHighscoreTable() {
        return highscoreTable;
    }
    public void setHighscoreTable(HighscoreTable highscoreTable) {
        this.highscoreTable = highscoreTable;
    }

    public UserInterface getUserInterface() {
        return userInterface;
    }

    public void setUserInterface(UserInterface userInterface) {
        this.userInterface = userInterface;
    }


    @FXML
    private Label DifficultyLabel;

    @FXML
    private Label boardIdLabel;

    @FXML
    private Button buttonMainMenu;

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

    @FXML
    void backToMainMenuPress(ActionEvent event) {
        userInterface.showMenu(MenuType.MAIN_MENU);
    }


    public void postInit(){
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnMoves.setCellValueFactory(new PropertyValueFactory<>("moveCount"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnTime.setCellValueFactory(new PropertyValueFactory<>("elapsedTime"));
        // Populate TableView with data
        populateTable();
        this.boardIdLabel.setText(String.valueOf(UserStatistic.getInstance().getSelectedBoard().getBoardId()));
        this.DifficultyLabel.setText("(" + GameSettings.getInstance().getDifficulty() + ")");

        cleanUpSession();

    }


    private void cleanUpSession(){
        //clean up settings
        GameSettings.getInstance().resetToDefault();
        UserStatistic.getInstance().setToDefault();
    }

    private void populateTable() {
        ObservableList<HighscoreEntry> entries = FXCollections.observableArrayList(highscoreTable.getHighscoreList());
        // Sort entries
        entries.sort(Comparator.comparing(HighscoreEntry::getMoveCount)
                .thenComparing(HighscoreEntry::getElapsedTime));

        tableViewHighScore.setItems(entries);
    }
}