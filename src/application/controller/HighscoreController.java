package application.controller;

import application.model.HighscoreEntry;
import application.model.HighscoreTable;
import application.model.UserStatistic;
import application.model.GameSettings;
import application.model.BoardManager;
import application.model.Difficulty;
import application.view.HighscoreView;

import java.util.Comparator;
import java.util.List;

/* Hauptverantwortlicher: Alex Becker */
/* Mitwirkend: Matthias Henzel, Erik Witte */

/**
 * Die Klasse HighscoreController ist verantwortlich für die Verwaltung der Highscore-Ansicht.
 * Sie ermöglicht die Anzeige der Highscore-Tabelle und die Navigation zurück zum Hauptmenü.
 */
public class HighscoreController {
    private final UserStatistic userStatistic;
    private final Coordinator coordinator;
    private final BoardManager boardManager;
    private HighscoreView highscoreView;

    /**
     * Konstruktor für die Klasse HighscoreController. Initialisiert den Controller
     * mit den erforderlichen Managern und Statistiken.
     *
     * @param userStatistic Die Benutzerstatistiken.
     * @param coordinator   Der Koordinator, der für die Steuerung der Anwendungsflüsse verantwortlich ist.
     * @param boardManager  Der BoardManager, der das Spielbrett und die Highscores verwaltet.
     */
    public HighscoreController(UserStatistic userStatistic, Coordinator coordinator, BoardManager boardManager) {
        this.userStatistic = userStatistic;
        this.coordinator = coordinator;
        this.boardManager = boardManager;
    }

    /**
     * Setzt die Highscore-Ansicht für den Controller.
     *
     * @param highscoreView Die Highscore-Ansicht.
     */
    public void setHighscoreView(HighscoreView highscoreView) {
        this.highscoreView = highscoreView;
    }

    /**
     * Handhabt die Rückkehr zum Hauptmenü.
     */
    public void handleBackToMainMenu() {
        coordinator.showMainMenu();
    }

    /**
     * Initialisiert die Highscore-Ansicht, indem die Tabellenkolonnen
     * konfiguriert und die Tabelle gefüllt wird.
     */
    public void postInit() {
        highscoreView.initializeTableColumns();
        populateTable();
        highscoreView.updateLabels(
                String.valueOf(userStatistic.getSelectedBoard().getBoardId()),
                GameSettings.getInstance().getDifficulty().toString()
        );

        cleanUpSession();
    }

    /**
     * Füllt die Highscore-Tabelle mit Einträgen, die nach Anzahl der Züge
     * und benötigter Zeit sortiert sind.
     */
    private void populateTable() {
        HighscoreTable highscoreTable = getHighscoreTable();
        List<HighscoreEntry> entries = highscoreTable.getHighscoreList();
        entries.sort(Comparator.comparing(HighscoreEntry::getMoveCount)
                .thenComparing(HighscoreEntry::getElapsedTime));

        highscoreView.populateTable(entries);
    }

    /**
     * Ruft die Highscore-Tabelle basierend auf dem aktuellen Spielbrett
     * und dem Schwierigkeitsgrad ab.
     *
     * @return Die Highscore-Tabelle für das ausgewählte Brett und den Schwierigkeitsgrad.
     */
    private HighscoreTable getHighscoreTable() {
        int boardId = userStatistic.getSelectedBoard().getBoardId();
        Difficulty difficulty = GameSettings.getInstance().getDifficulty();
        return boardManager.getHighScoreTableForBoard(boardId, difficulty);
    }

    /**
     * Bereinigt die Sitzung, indem die Spiel- und Benutzerstatistiken
     * auf die Standardeinstellungen zurückgesetzt werden.
     */
    private void cleanUpSession() {
        GameSettings.getInstance().resetToDefault();
        userStatistic.setToDefault();
    }
}
