package application.controller;

import application.model.MenuType;
import application.view.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenuController implements InitializableController {

    private final Coordinator coordinator;
    @FXML
    private Button settingsButton;
    @FXML
    private Button buttonStart;

    public MainMenuController(Coordinator coordinator) {
        this.coordinator = coordinator;
    }

    @FXML
    void onButtonPress(ActionEvent event) {
        coordinator.showDifficultyMenu();
    }

    @FXML
    void onSettingsButtonPress(ActionEvent event) {
        coordinator.showResetPinMenu();
    }

    @Override
    public void initializeWithSceneManager(SceneManager sceneManager) {

    }
}
