package application.controller;

/**
 * <p>Hauptverantwortlicher:</p>
 * <ul>
 *     <li>Alex Becker</li>
 * </ul>
 * <p>Mitwirkende:</p>
 * <ul>
 *     <li>Matthias Henzel</li>
 * </ul>
 *
 * <p>Die Klasse MainMenuController ist verantwortlich für die Verwaltung der Interaktionen im Hauptmenü.
 * Sie behandelt Benutzereingaben und navigiert zu den entsprechenden Menüs.</p>
 */

public class MainMenuController {

    private final Coordinator coordinator;

    /**
     * Konstruktor für die Klasse MainMenuController.
     * Initialisiert den Controller mit dem gegebenen Koordinator.
     *
     * @param coordinator Der Koordinator, der für die Steuerung der Anwendungsflüsse verantwortlich ist.
     */
    public MainMenuController(Coordinator coordinator) {
        this.coordinator = coordinator;
    }

    /**
     * Handhabt das Drücken der Start-Taste im Hauptmenü und navigiert zum Menü zur Auswahl des Schwierigkeitsgrades.
     */
    public void handleStartButtonPress() {
        coordinator.showDifficultyMenu();
    }

    /**
     * Handhabt das Drücken der Einstellungen-Taste im Hauptmenü und navigiert zum Menü zum Zurücksetzen der PIN.
     */
    public void handleSettingsButtonPress() {
        coordinator.showResetPinMenu();
    }

}
