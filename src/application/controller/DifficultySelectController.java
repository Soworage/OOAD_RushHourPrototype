package application.controller;

import application.model.Difficulty;
import application.model.GameSettings;
import application.view.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DifficultySelectController implements InitializableController {

    private final Coordinator coordinator;
    @FXML
    private Button easyButton;
    @FXML
    private Button hardButton;
    @FXML
    private Button mediumButton;
    @FXML
    private Button mainMenuButton;

    public DifficultySelectController(Coordinator coordinator) {
        this.coordinator = coordinator;
    }

    @FXML
    void onClickEasy(ActionEvent event) {
        GameSettings.getInstance().setDifficulty(Difficulty.EASY);
        coordinator.showColorSchemeMenu();
    }

    @FXML
    void onClickHard(ActionEvent event) {
        GameSettings.getInstance().setDifficulty(Difficulty.HARD);
        coordinator.showColorSchemeMenu();
    }

    @FXML
    void onClickMedium(ActionEvent event) {
        GameSettings.getInstance().setDifficulty(Difficulty.MEDIUM);
        coordinator.showColorSchemeMenu();
    }

    @FXML
    void onMainMenuButtonClick(ActionEvent event) {
        coordinator.showMainMenu();
    }

    @Override
    public void initializeWithSceneManager(SceneManager sceneManager) {
        // This method can be used if needed, or left empty if not used
    }
}
