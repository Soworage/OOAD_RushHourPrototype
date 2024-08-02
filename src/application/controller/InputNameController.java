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

    @FXML
    private Button mainMenuButton;
    @FXML
    private Button buttonSave;
    private UserStatistic userStatistic;
    private UserInterface userInterface;
    private HighscoreTable highscoreTable;
    @FXML
    private TextField nameInputField;

    public void setUserStatistic(UserStatistic userStatistic) {
        this.userStatistic = userStatistic;
    }

    public void setUserInterface(UserInterface userInterface) {
        this.userInterface = userInterface;
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
