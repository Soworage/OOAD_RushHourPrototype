package application.controller;

import application.model.PINManager;

/* Hauptverantwortlicher: Alex Becker */

/**
 * Die Klasse ResetController ist verantwortlich für die Verwaltung des PIN-Reset-Vorgangs.
 * Sie verarbeitet die Eingabe des PIN-Codes und führt entsprechende Aktionen basierend auf der Validität des Codes durch.
 */
public class ResetController {

    private final PINManager pinManager;
    private final Coordinator coordinator;

    /**
     * Konstruktor für die Klasse ResetController.
     * Initialisiert den Controller mit dem gegebenen PIN-Manager und Koordinator.
     *
     * @param pinManager  Der PINManager, der für die Verwaltung und Überprüfung der PIN-Codes verantwortlich ist.
     * @param coordinator Der Koordinator, der für die Steuerung der Anwendungsflüsse verantwortlich ist.
     */
    public ResetController(PINManager pinManager, Coordinator coordinator) {
        this.pinManager = pinManager;
        this.coordinator = coordinator;
    }

    /**
     * Verarbeitet den eingegebenen PIN-Code und überprüft dessen Gültigkeit.
     * Wenn der PIN gültig ist, wird ein Zurücksetzen des Spiels initiiert.
     * Unabhängig von der Validität wird zum Hauptmenü navigiert.
     *
     * @param pinCode Der eingegebene PIN-Code.
     */
    public void processPinCode(String pinCode) {
        if (pinManager.userEnterPIN(pinCode)) {
            System.out.println("Servicemitglied hat einen Spiel-Reset initiiert");
            coordinator.showMainMenu();
        } else {
            System.out.println("Ungültiger PIN eingegeben");
            coordinator.showMainMenu();
        }
    }

    /**
     * Kehrt zum Hauptmenü zurück.
     */
    public void goBackToMainMenu() {
        coordinator.showMainMenu();
    }
}
