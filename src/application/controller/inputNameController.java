package application.controller;

import application.model.HighscoreEntry;
import application.model.HighscoreTable;
import application.model.MenuType;
import application.model.UserStatistic;
import application.view.UserInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class inputNameController {

    private UserStatistic userStatistic;
    private UserInterface userInterface;
    private HighscoreTable highscoreTable;

    public UserStatistic getUserStatistic() {
        return userStatistic;
    }

    public void setUserStatistic(UserStatistic userStatistic) {
        this.userStatistic = userStatistic;
    }

    public UserInterface getUserInterface() {
        return userInterface;
    }

    public void setUserInterface(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    @FXML
    void onSaveButtonPressed(ActionEvent event) {
        this.getUserStatistic().setName(nameInputField.getText());

        //create own entry
        LocalDate now = LocalDate.now();
        HighscoreEntry entry = new HighscoreEntry(userStatistic.getName(), userStatistic.getMoveCount(),now,String.valueOf(userStatistic.getSeconds()));
        highscoreTable.addEntry(entry);

        //switch to highscore table
        userInterface.showMenu(MenuType.HIGHSCORE_MENU);


    }

    public HighscoreTable getHighscoreTable() {
        return highscoreTable;
    }

    public void setHighscoreTable(HighscoreTable highscoreTable) {
        this.highscoreTable = highscoreTable;
    }

    @FXML
    private Button buttonSave;

    @FXML
    private TextField nameInputField;



}
