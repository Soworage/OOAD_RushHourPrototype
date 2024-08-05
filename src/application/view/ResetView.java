package application.view;

import application.controller.ResetController;
import application.controller.InitializableController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Ansichtsklasse für das Zurücksetzen der Anwendungseinstellungen.
 * <p>Hauptverantwortlicher:</p>
 * <ul>
 *     <li>Matthias Henzel</li>
 * </ul>
 * <p>Mitwirkend:</p>
 * <ul>
 *     <li>Alex Becker</li>
 *     <li>Erik Witte</li>
 * </ul>
 */
public class ResetView implements InitializableController {

    private ResetController resetController;

    @FXML
    private Button buttonBack;

    @FXML
    private Button buttonAccept;

    @FXML
    private TextField codeInput;

    /**
     * Setzt den Reset-Controller für diese Ansicht.
     *
     * @param resetController Der Controller, der die Logik für das Zurücksetzen verarbeitet.
     */
    public void setResetController(ResetController resetController) {
        this.resetController = resetController;
    }

    /**
     * Behandelt das Klicken auf den "Akzeptieren"-Button.
     * Ruft die Methode im Controller auf, um den eingegebenen PIN-Code zu verarbeiten.
     *
     * @param event Das Ereignis, das beim Klicken auf den "Akzeptieren"-Button ausgelöst wird.
     */
    @FXML
    void onAcceptButtonPress(ActionEvent event) {
        String pinCode = codeInput.getText();
        resetController.processPinCode(pinCode);
    }

    /**
     * Behandelt das Klicken auf den "Zurück"-Button.
     * Ruft die Methode im Controller auf, um zur Hauptmenü-Ansicht zurückzukehren.
     *
     * @param event Das Ereignis, das beim Klicken auf den "Zurück"-Button ausgelöst wird.
     */
    @FXML
    void onBackButtonPress(ActionEvent event) {
        resetController.goBackToMainMenu();
    }

    @Override
    public void initializeWithSceneManager(SceneManager sceneManager) {
        // Initialisierungslogik falls erforderlich
    }
}
