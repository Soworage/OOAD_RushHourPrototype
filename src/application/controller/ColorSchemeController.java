package application.controller;

import application.model.Color;
import application.model.GameSettings;

/* Hauptverantwortlicher: Alex Becker */
/* Mitwirkend: Matthias Henzel */

/**
 * Die Klasse ColorSchemeController ist verantwortlich für die Verwaltung
 * der Farbschema-Auswahl innerhalb der Anwendung. Sie ermöglicht das
 * Setzen des ausgewählten Farbschemas und die Navigation zwischen den Menüs.
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
