package application.controller;

import application.model.Color;
import application.model.GameSettings;
import application.view.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class ColorSchemeController implements InitializableController {

    private final Coordinator coordinator;
    @FXML
    private Button mainMenuButton;
    @FXML
    private Button confirmButton;
    @FXML
    private ComboBox<Color> comboBox;

    public ColorSchemeController(Coordinator coordinator) {
        this.coordinator = coordinator;
    }

    @FXML
    void initialize() {
        comboBox.getItems().addAll(Color.values());
        if (!comboBox.getItems().isEmpty()) {
            comboBox.getSelectionModel().selectFirst();
        }
    }

    @FXML
    void onClickConfirm(ActionEvent event) {
        Color selectedColor = comboBox.getSelectionModel().getSelectedItem();
        GameSettings.getInstance().setColorScheme(selectedColor);
        coordinator.showGame();
    }

    @FXML
    void onClickMainMenuButton(ActionEvent event) {
        coordinator.showMainMenu();
    }

    @Override
    public void initializeWithSceneManager(SceneManager sceneManager) {
        // This method can be used if needed
    }
}
