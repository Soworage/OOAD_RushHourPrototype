package application.view;

import application.controller.InputNameController;
import application.controller.InitializableController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Ansichtsklasse für die Eingabe des Benutzernamens.
 * <p>Hauptverantwortlicher:</p>
 * <ul>
 *     <li>Matthias Henzel</li>
 * </ul>
 * <p>Mitwirkend:</p>
 * <ul>
 *     <li>Alex Becker</li>
 *     <li>Alex Mihel</li>
 * </ul>
 */
public class InputNameView implements InitializableController {

    private InputNameController inputNameController;

    @FXML
    private Button mainMenuButton;

    @FXML
    private Button buttonSave;

    @FXML
    private TextField nameInputField;

    /**
     * Setzt den InputName-Controller für diese Ansicht.
     *
     * @param inputNameController Der Controller, der die Logik für die Eingabe des Benutzernamens verarbeitet.
     */
    public void setInputNameController(InputNameController inputNameController) {
        this.inputNameController = inputNameController;
    }

    /**
     * Behandelt das Klicken auf den "Speichern"-Button.
     * Ruft die Methode zum Speichern des Namens im Controller auf.
     *
     * @param event Das Ereignis, das beim Klicken auf den "Speichern"-Button ausgelöst wird.
     */
    @FXML
    void onSaveButtonPressed(ActionEvent event) {
        String name = nameInputField.getText();
        inputNameController.saveName(name);
    }

    /**
     * Behandelt das Klicken auf den Button, um zum Hauptmenü zurückzukehren.
     * Der Controller wird benachrichtigt, um die Rückkehr zum Hauptmenü zu handhaben.
     *
     * @param event Das Ereignis, das beim Klicken auf den Hauptmenü-Button ausgelöst wird.
     */
    @FXML
    void onPressBackToMainMenuButton(ActionEvent event) {
        inputNameController.goBackToMainMenu();
    }

    @Override
    public void initializeWithSceneManager(SceneManager sceneManager) {
        // Initialisierungslogik falls erforderlich
    }
}
