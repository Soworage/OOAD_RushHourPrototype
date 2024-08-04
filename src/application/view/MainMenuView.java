package application.view;

import application.controller.MainMenuController;
import application.controller.InitializableController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenuView implements InitializableController {

    private MainMenuController mainMenuController;

    @FXML
    private Button settingsButton;
    @FXML
    private Button buttonStart;

    public void setMainMenuController(MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
    }

    @FXML
    void onButtonPress(ActionEvent event) {
        mainMenuController.handleStartButtonPress();
    }

    @FXML
    void onSettingsButtonPress(ActionEvent event) {
        mainMenuController.handleSettingsButtonPress();
    }

    @Override
    public void initializeWithSceneManager(SceneManager sceneManager) {
        // Initialization logic if needed
    }
}
