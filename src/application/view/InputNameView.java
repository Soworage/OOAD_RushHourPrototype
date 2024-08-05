package application.view;

import application.controller.InputNameController;
import application.controller.InitializableController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/* Hauptverantwortlicher: Matthias Henzel */
/* Mitwirkend: Alex Becker, Alex Mihel */
public class InputNameView implements InitializableController {

    private InputNameController inputNameController;

    @FXML
    private Button mainMenuButton;
    @FXML
    private Button buttonSave;
    @FXML
    private TextField nameInputField;

    public void setInputNameController(InputNameController inputNameController) {
        this.inputNameController = inputNameController;
    }

    @FXML
    void onSaveButtonPressed(ActionEvent event) {
        String name = nameInputField.getText();
        inputNameController.saveName(name);
    }

    @FXML
    void onPressBackToMainMenuButton(ActionEvent event) {
        inputNameController.goBackToMainMenu();
    }

    @Override
    public void initializeWithSceneManager(SceneManager sceneManager) {
        // Initialization logic if needed
    }
}
