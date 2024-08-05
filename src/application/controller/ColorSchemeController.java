package application.controller;

import application.model.Color;
import application.model.GameSettings;

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
 * <p>Die Klasse ColorSchemeController ist verantwortlich für die Verwaltung
 * der Farbschema-Auswahl innerhalb der Anwendung. Sie ermöglicht das
 * Setzen des ausgewählten Farbschemas und die Navigation zwischen den Menüs.</p>
 */
public class ColorSchemeController {

    private final Coordinator coordinator;

    /**
     * Konstruktor für die Klasse ColorSchemeController. Initialisiert den Controller
     * mit einem Verweis auf den Koordinator der Anwendung.
     *
     * @param coordinator Der Koordinator, der für die Steuerung der Anwendungsflüsse
     *                    verantwortlich ist.
     */
    public ColorSchemeController(Coordinator coordinator) {
        this.coordinator = coordinator;
    }

    /**
     * Behandelt die Bestätigung der Farbauswahl durch den Benutzer.
     * Setzt das ausgewählte Farbschema in den Spiel-Einstellungen
     * und zeigt das Spiel an.
     *
     * @param selectedColor Die vom Benutzer ausgewählte Farbe.
     */
    public void handleConfirm(Color selectedColor) {
        GameSettings.getInstance().setColorScheme(selectedColor);
        coordinator.showGame();
    }

    /**
     * Kehrt zum Hauptmenü zurück.
     */
    public void handleBackToMainMenu() {
        coordinator.showMainMenu();
    }
}
