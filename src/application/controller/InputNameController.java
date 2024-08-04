package application.controller;

import application.model.HighscoreEntry;
import application.model.HighscoreTable;
import application.model.UserStatistic;
import application.view.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class InputNameController implements InitializableController {

    private final UserStatistic userStatistic;
    private final Coordinator coordinator;
    private final HighscoreTable highscoreTable;
    @FXML
    private Button mainMenuButton;
    @FXML
    private Button buttonSave;
    @FXML
    private TextField nameInputField;

    public InputNameController(UserStatistic userStatistic, Coordinator coordinator, HighscoreTable highscoreTable) {
        this.userStatistic = userStatistic;
        this.coordinator = coordinator;
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
        coordinator.showHighScoreMenu();
    }

    @FXML
    void onPressBackToMainMenuButton(ActionEvent event) {
        coordinator.showMainMenu();
    }

    @Override
    public void initializeWithSceneManager(SceneManager sceneManager) {
        // This method can be used if needed
    }
}
