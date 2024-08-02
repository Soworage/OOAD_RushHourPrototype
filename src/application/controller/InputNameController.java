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

public class InputNameController {

    private UserStatistic userStatistic;
    private UserInterface userInterface;
    private HighscoreTable highscoreTable;
    @FXML
    private Button buttonSave;
    @FXML
    private TextField nameInputField;

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

    public HighscoreTable getHighscoreTable() {
        return highscoreTable;
    }

    public void setHighscoreTable(HighscoreTable highscoreTable) {
        this.highscoreTable = highscoreTable;
    }

    @FXML
    void onSaveButtonPressed(ActionEvent event) {
        userStatistic.setName(nameInputField.getText());
        LocalDate now = LocalDate.now();
        HighscoreEntry entry = new HighscoreEntry(
                userStatistic.getName(),
                userStatistic.getMoveCount(),
                now,
                String.valueOf(userStatistic.getSeconds())
        );
        highscoreTable.addEntry(entry);
        userInterface.showMenu(MenuType.HIGHSCORE_MENU);
    }

    @FXML
    void onPressBackToMainMenuButton(ActionEvent event) {
        userInterface.showMenu(MenuType.MAIN_MENU);
    }


}
