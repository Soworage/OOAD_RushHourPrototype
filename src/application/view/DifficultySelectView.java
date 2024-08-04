package application.view;

import application.controller.DifficultySelectController;
import application.controller.InitializableController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DifficultySelectView implements InitializableController {

    private DifficultySelectController difficultySelectController;

    @FXML
    private Button easyButton;
    @FXML
    private Button hardButton;
    @FXML
    private Button mediumButton;
    @FXML
    private Button mainMenuButton;

    public void setDifficultySelectController(DifficultySelectController difficultySelectController) {
        this.difficultySelectController = difficultySelectController;
    }

    @FXML
    void onClickEasy(ActionEvent event) {
        difficultySelectController.selectDifficultyEasy();
    }

    @FXML
    void onClickHard(ActionEvent event) {
        difficultySelectController.selectDifficultyHard();
    }

    @FXML
    void onClickMedium(ActionEvent event) {
        difficultySelectController.selectDifficultyMedium();
    }

    @FXML
    void onMainMenuButtonClick(ActionEvent event) {
        difficultySelectController.goBackToMainMenu();
    }

    @Override
    public void initializeWithSceneManager(SceneManager sceneManager) {
        // Not used but required by the interface
    }
}
