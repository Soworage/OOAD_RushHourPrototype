package application.controller;

import application.model.UserStatistic;
import application.view.StatisticsView;

/* Hauptverantwortlicher: Alex Becker */
/* Mitwirkend: Matthias Henzel, Alex Mihel */

/**
 * Die Klasse StatisticsController ist verantwortlich für die Verwaltung der Anzeige von Benutzerstatistiken.
 * Sie ermöglicht die Aktualisierung der Statistikansicht und die Navigation zwischen verschiedenen Menüs basierend auf Benutzereingaben.
 */
public class StatisticsController {

    private final UserStatistic userStatistic;
    private final Coordinator coordinator;
    private final StatisticsView statisticsView;

    /**
     * Konstruktor für die Klasse StatisticsController.
     * Initialisiert den Controller mit den gegebenen Benutzerstatistiken, dem Koordinator und der Statistikansicht.
     *
     * @param userStatistic  Die Benutzerstatistiken, die angezeigt werden sollen.
     * @param coordinator    Der Koordinator, der für die Steuerung der Anwendungsflüsse verantwortlich ist.
     * @param statisticsView Die Ansicht, die die Benutzerstatistiken darstellt.
     */
    public StatisticsController(UserStatistic userStatistic, Coordinator coordinator, StatisticsView statisticsView) {
        this.userStatistic = userStatistic;
        this.coordinator = coordinator;
        this.statisticsView = statisticsView;
        this.statisticsView.setStatisticsController(this);
    }

    /**
     * Initialisiert die Statistikansicht und zeigt die aktuellen Benutzerstatistiken an,
     * einschließlich der Anzahl der Züge und der verstrichenen Zeit in Minuten und Sekunden.
     */
    public void postInit() {
        // Anzeige der Benutzerstatistiken
        int moveCount = userStatistic.getMoveCount();
        int minutes = userStatistic.getSeconds() / 60;
        int seconds = userStatistic.getSeconds() % 60;
        statisticsView.updateStatistics(moveCount, minutes, seconds);
    }

    /**
     * Handhabt die Benutzeraktion, wenn der Benutzer die Statistiken speichern möchte,
     * und navigiert zum Menü zur Eingabe des Benutzernamens.
     */
    public void handleSaveYes() {
        coordinator.showInputNameMenu();
    }

    /**
     * Handhabt die Benutzeraktion, wenn der Benutzer die Statistiken nicht speichern möchte,
     * und navigiert direkt zum Highscore-Menü.
     */
    public void handleSaveNo() {
        coordinator.showHighScoreMenu();
    }

    /**
     * Kehrt zum Hauptmenü zurück.
     */
    public void goBackToMainMenu() {
        coordinator.showMainMenu();
    }
}
