package application.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * <p>Hauptverantwortlicher:</p>
 * <ul>
 *     <li>Matthias Henzel</li>
 * </ul>
 *
 * <p>Die Klasse InactivityNotifier überwacht die Inaktivität des Benutzers
 * und führt entsprechende Aktionen aus, wenn ein bestimmter Zeitraum ohne Interaktion vergeht.
 * Sie benachrichtigt den Benutzer nach einer bestimmten Zeit der Inaktivität
 * und kehrt nach einer längeren Zeit zum Hauptmenü zurück.</p>
 */

public class InactivityNotifier {
    private static final int NOTIFY_INACTIVITY_SECONDS = 180; // 3 Minuten
    private static final int RETURN_TO_MAIN_MENU_SECONDS = 300; // 5 Minuten

    private static InactivityNotifier instance;

    private final Coordinator coordinator;
    private Timeline inactivityTimer;
    private boolean notified;
    private int secondsInactive;

    /**
     * Privater Konstruktor für die Klasse InactivityNotifier. Initialisiert den Koordinator
     * und den Inaktivitäts-Timer.
     *
     * @param coordinator Der Koordinator, der für die Steuerung der Anwendungsflüsse verantwortlich ist.
     */
    private InactivityNotifier(Coordinator coordinator) {
        this.coordinator = coordinator;
        initializeTimer();
        System.out.println("InactivityManager-Instanz erstellt.");
    }

    /**
     * Gibt die Instanz des InactivityNotifier zurück und erstellt sie bei Bedarf.
     *
     * @param coordinator Der Koordinator der Anwendung.
     * @return Die Instanz des InactivityNotifier.
     */
    public static InactivityNotifier getInstance(Coordinator coordinator) {
        if (instance == null) {
            instance = new InactivityNotifier(coordinator);
            System.out.println("Neue InactivityManager-Instanz initialisiert.");
        } else {
            System.out.println("Vorhandene InactivityManager-Instanz abgerufen.");
        }
        return instance;
    }

    /**
     * Initialisiert den Inaktivitäts-Timer, der in regelmäßigen Abständen ein Ereignis auslöst.
     */
    private void initializeTimer() {
        inactivityTimer = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateInactivity()));
        inactivityTimer.setCycleCount(Timeline.INDEFINITE);
        System.out.println("Inactivity timer initialisiert.");
    }

    /**
     * Startet den Inaktivitäts-Timer, setzt die Inaktivitätszeit zurück
     * und beginnt mit der Überwachung.
     */
    public void startTimer() {
        inactivityTimer.stop();
        secondsInactive = 0;
        notified = false;
        inactivityTimer.playFromStart();
        System.out.println("Inactivity timer zurückgesetzt und gestartet.");
    }

    /**
     * Setzt den Inaktivitäts-Timer zurück und hält ihn an.
     */
    public void resetTimer() {
        inactivityTimer.stop();
        secondsInactive = 0;
        notified = false;
        System.out.println("Inactivity timer zurückgesetzt und angehalten.");
    }

    /**
     * Aktualisiert die Inaktivitätszeit und überprüft, ob Benachrichtigungen oder
     * eine Rückkehr zum Hauptmenü ausgelöst werden müssen.
     */
    private void updateInactivity() {
        secondsInactive++;
        System.out.println("Inactivity timer aktualisiert: " + secondsInactive + " Sekunden der Inaktivität.");

        if (secondsInactive == NOTIFY_INACTIVITY_SECONDS && !notified) {
            notifyInactivity();
            notified = true;
        }

        if (secondsInactive == RETURN_TO_MAIN_MENU_SECONDS) {
            returnToMainMenu();
        }
    }

    /**
     * Benachrichtigt den Benutzer über Inaktivität nach einer bestimmten Zeit.
     */
    private void notifyInactivity() {
        System.out.println("Inaktivitätsbenachrichtigung: Keine Interaktion seit 3 Minuten erkannt. Die Anwendung kehrt bald zum Hauptmenü zurück.");
        // TODO: Benachrichtige den Benutzer über die Inaktivität
    }

    /**
     * Kehrt zum Hauptmenü zurück, wenn die maximale Inaktivitätszeit erreicht ist.
     */
    private void returnToMainMenu() {
        inactivityTimer.stop();
        System.out.println("Inaktivität von 5 Minuten erreicht. Rückkehr zum Hauptmenü.");
        coordinator.showMainMenu();
    }
}
