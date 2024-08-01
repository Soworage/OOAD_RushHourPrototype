package application.controller;

import application.model.HighscoreEntry;
import application.model.HighscoreTable;
import application.view.UserInterface;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class highscoreController {
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
    public void initialize() {

    }

    public void postInit(){
        // Set up columns with property names corresponding to `HighscoreEntry` fields
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnMoves.setCellValueFactory(new PropertyValueFactory<>("anzahlZuege"));
        columnDate.setCellValueFactory(cellData -> {
            LocalDate date = cellData.getValue().getDatum();
            return new SimpleStringProperty(date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        });
        columnTime.setCellValueFactory(new PropertyValueFactory<>("elapsedTime"));

        // Populate TableView with data
        populateTable();
    }

    private void populateTable() {
        ObservableList<HighscoreEntry> entries = FXCollections.observableArrayList(highscoreTable.getHighscoreList());
        tableViewHighScore.setItems(entries);
    }
}