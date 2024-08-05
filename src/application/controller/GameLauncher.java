package application.controller;

import javafx.stage.Stage;

/* Hauptverantwortlicher: Alex Mihel */
/* Mitwirkend: Alex Becker */

/**
 * Die Klasse GameLauncher ist verantwortlich für das Starten der Anwendung.
 * Sie initialisiert die Hauptbühne und den Koordinator der Anwendung.
 */
public class GameLauncher {

    private final Stage primaryStage;

    /**
     * Konstruktor für die Klasse GameLauncher. Initialisiert die Hauptbühne der Anwendung.
     *
     * @param primaryStage Die Hauptbühne der JavaFX-Anwendung.
     */
    public GameLauncher(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Startet die Anwendung, indem der Koordinator initialisiert und das Hauptmenü angezeigt wird.
     */
    public void run() {
        // Initialisiere den Koordinator mit der Hauptbühne
        Coordinator coordinator = new Coordinator(primaryStage);

        // Starte die Anwendung, indem das Hauptmenü angezeigt wird
        coordinator.start();
    }
}
