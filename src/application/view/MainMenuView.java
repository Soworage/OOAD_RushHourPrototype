package application.view;

import application.controller.MainMenuController;
import application.controller.InitializableController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Ansichtsklasse für das Hauptmenü der Anwendung.
 * <p>Hauptverantwortlicher:</p>
 * <ul>
 *     <li>Matthias Henzel</li>
 * </ul>
 */
public class MainMenuView implements InitializableController {

    private MainMenuController mainMenuController;

    @FXML
    private Button settingsButton;

    @FXML
    private Button buttonStart;

    /**
     * Setzt den MainMenu-Controller für diese Ansicht.
     *
     * @param mainMenuController Der Controller, der die Logik für das Hauptmenü verarbeitet.
     */
    public void setMainMenuController(MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
    }

    /**
     * Behandelt das Klicken auf den "Start"-Button.
     * Ruft die Methode im Controller auf, um den Start des Spiels zu verarbeiten.
     *
     * @param event Das Ereignis, das beim Klicken auf den "Start"-Button ausgelöst wird.
     */
    @FXML
    void onButtonPress(ActionEvent event) {
        mainMenuController.handleStartButtonPress();
    }

    /**
     * Behandelt das Klicken auf den "Einstellungen"-Button.
     * Ruft die Methode im Controller auf, um die Einstellungen zu öffnen.
     *
     * @param event Das Ereignis, das beim Klicken auf den "Einstellungen"-Button ausgelöst wird.
     */
    @FXML
    void onSettingsButtonPress(ActionEvent event) {
        mainMenuController.handleSettingsButtonPress();
    }

    @Override
    public void initializeWithSceneManager(SceneManager sceneManager) {
        // Initialisierungslogik falls erforderlich
    }
}
