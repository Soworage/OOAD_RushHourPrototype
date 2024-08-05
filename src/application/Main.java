package application;

import application.controller.GameLauncher;
import javafx.application.Application;
import javafx.stage.Stage;

/* Hauptverantwortlicher: Alex Mihel */

/**
 * Die Klasse Main ist der Einstiegspunkt der Anwendung.
 * Sie startet die JavaFX-Anwendung und initialisiert das Spiel über den GameLauncher.
 */
public class Main extends Application {

    /**
     * Der Haupteinstiegspunkt der Anwendung.
     * Ruft die JavaFX-Startmethode auf, um die Anwendung zu starten.
     *
     * @param args Die Befehlszeilenargumente.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Startet die JavaFX-Anwendung und initialisiert das Hauptfenster.
     * Erstellt eine Instanz des GameLaunchers und startet das Spiel.
     *
     * @param primaryStage Die Hauptbühne der JavaFX-Anwendung.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            // Instanziiere das Spiel
            GameLauncher gameLauncher = new GameLauncher(primaryStage);
            // Starte das Spiel
            gameLauncher.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
