package application.view;

import application.controller.ColorSchemeController;
import application.controller.InitializableController;
import application.model.Color;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

/**
 * Ansichtsklasse für das Farbschema-Menü.
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
public class ColorSchemeView implements InitializableController {

    private ColorSchemeController colorSchemeController;

    @FXML
    private Button mainMenuButton;

    @FXML
    private Button confirmButton;

    @FXML
    private ComboBox<Color> comboBox;

    /**
     * Setzt den Controller für das Farbschema-Menü.
     *
     * @param colorSchemeController Der Controller für das Farbschema-Menü.
     */
    public void setColorSchemeController(ColorSchemeController colorSchemeController) {
        this.colorSchemeController = colorSchemeController;
    }

    /**
     * Initialisiert die Ansicht, indem die ComboBox mit den verfügbaren Farben gefüllt wird
     * und die erste Farbe ausgewählt wird.
     */
    @FXML
    void initialize() {
        comboBox.getItems().addAll(Color.values());
        if (!comboBox.getItems().isEmpty()) {
            comboBox.getSelectionModel().selectFirst();
        }
    }

    /**
     * Behandelt das Bestätigen des Farbschemas durch den Benutzer.
     * Die ausgewählte Farbe wird an den Controller übergeben.
     *
     * @param event Das Ereignis, das beim Klicken auf den Bestätigungs-Button ausgelöst wird.
     */
    @FXML
    void onClickConfirm(ActionEvent event) {
        Color selectedColor = comboBox.getSelectionModel().getSelectedItem();
        colorSchemeController.handleConfirm(selectedColor);
    }

    /**
     * Behandelt das Zurückkehren zum Hauptmenü durch den Benutzer.
     * Der Controller wird benachrichtigt, um zum Hauptmenü zurückzukehren.
     *
     * @param event Das Ereignis, das beim Klicken auf den Hauptmenü-Button ausgelöst wird.
     */
    @FXML
    void onClickMainMenuButton(ActionEvent event) {
        colorSchemeController.handleBackToMainMenu();
    }

    @Override
    public void initializeWithSceneManager(SceneManager sceneManager) {
        // Noch nicht implementiert
    }
}
