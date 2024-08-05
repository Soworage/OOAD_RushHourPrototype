package application.view;

import application.controller.ColorSchemeController;
import application.controller.InitializableController;
import application.model.Color;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

/* Hauptverantwortlicher: Matthias Henzel */
/* Mitwirkend: Alex Becker, Erik Witte */
public class ColorSchemeView implements InitializableController {

    private ColorSchemeController colorSchemeController;

    @FXML
    private Button mainMenuButton;
    @FXML
    private Button confirmButton;
    @FXML
    private ComboBox<Color> comboBox;

    public void setColorSchemeController(ColorSchemeController colorSchemeController) {
        this.colorSchemeController = colorSchemeController;
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
        colorSchemeController.handleConfirm(selectedColor);
    }

    @FXML
    void onClickMainMenuButton(ActionEvent event) {
        colorSchemeController.handleBackToMainMenu();
    }

    @Override
    public void initializeWithSceneManager(SceneManager sceneManager) {

    }
}