package application.view;

import application.controller.ResetController;
import application.controller.InitializableController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/* Hauptverantwortlicher: Matthias Henzel */
public class ResetView implements InitializableController {

    private ResetController resetController;

    @FXML
    private Button buttonBack;
    @FXML
    private Button buttonAccept;
    @FXML
    private TextField codeInput;

    public void setResetController(ResetController resetController) {
        this.resetController = resetController;
    }

    @FXML
    void onAcceptButtonPress(ActionEvent event) {
        String pinCode = codeInput.getText();
        resetController.processPinCode(pinCode);
    }

    @FXML
    void onBackButtonPress(ActionEvent event) {
        resetController.goBackToMainMenu();
    }

    @Override
    public void initializeWithSceneManager(SceneManager sceneManager) {
        // Initialization logic if needed
    }
}
