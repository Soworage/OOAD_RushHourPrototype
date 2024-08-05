package application.controller;

import application.model.HighscoreEntry;
import application.model.HighscoreTable;
import application.model.UserStatistic;

import java.time.LocalDate;
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
 * <p>Die Klasse InputNameController ist verantwortlich für die Handhabung der Eingabe von Benutzernamen
 * und das Speichern der Benutzerstatistiken in der Highscore-Tabelle.
 * Sie ermöglicht auch die Navigation zurück zum Hauptmenü.</p>
 */

public class InputNameController {

    private final UserStatistic userStatistic;
    private final Coordinator coordinator;
    private final HighscoreTable highscoreTable;

    /**
     * Konstruktor für die Klasse InputNameController.
     * Initialisiert den Controller mit den erforderlichen Benutzerstatistiken und der Highscore-Tabelle.
     *
     * @param userStatistic Die Benutzerstatistiken.
     * @param coordinator   Der Koordinator, der für die Steuerung der Anwendungsflüsse verantwortlich ist.
     * @param highscoreTable Die Highscore-Tabelle, in der die Einträge gespeichert werden.
     */
    public InputNameController(UserStatistic userStatistic, Coordinator coordinator, HighscoreTable highscoreTable) {
        this.userStatistic = userStatistic;
        this.coordinator = coordinator;
        this.highscoreTable = highscoreTable;
    }

    /**
     * Speichert den eingegebenen Namen des Benutzers und erstellt einen neuen Highscore-Eintrag
     * basierend auf den aktuellen Benutzerstatistiken. Zeigt anschließend das Highscore-Menü an.
     *
     * @param name Der eingegebene Name des Benutzers.
     */
    public void saveName(String name) {
        userStatistic.setName(name);
        LocalDate now = LocalDate.now();
        HighscoreEntry entry = new HighscoreEntry(
                userStatistic.getName(),
                userStatistic.getMoveCount(),
                now,
                String.valueOf(userStatistic.getSeconds())
        );
        highscoreTable.addEntry(entry);
        coordinator.showHighScoreMenu();
    }

    /**
     * Kehrt zum Hauptmenü zurück.
     */
    public void goBackToMainMenu() {
        coordinator.showMainMenu();
    }
}
