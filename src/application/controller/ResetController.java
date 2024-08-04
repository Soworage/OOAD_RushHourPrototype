package application.controller;

import application.model.PINManager;
import application.view.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ResetController implements InitializableController {

    private final PINManager pinManager;
    private final Coordinator coordinator;
    @FXML
    private Button buttonBack;
    @FXML
    private Button buttonAccept;
    @FXML
    private TextField codeInput;

    public ResetController(PINManager pinManager, Coordinator coordinator) {
        this.pinManager = pinManager;
        this.coordinator = coordinator;
    }

    @FXML
    void onAcceptButtonPress(ActionEvent event) {
        if (pinManager.userEnterPIN(codeInput.getText())) {
            System.out.println("Service member initiated a game reset");
            coordinator.showMainMenu();
        } else {
            System.out.println("Invalid PIN entered");
            // Optionally, provide feedback to the user
        }
    }

    @FXML
    void onBackButtonPress(ActionEvent event) {
        coordinator.showMainMenu();
    }

    @Override
    public void initializeWithSceneManager(SceneManager sceneManager) {
        // This method can be used if needed
    }
}
