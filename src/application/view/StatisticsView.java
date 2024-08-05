package application.view;

import application.controller.StatisticsController;
import application.controller.InitializableController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Ansicht für die Anzeige von Statistiken am Ende des Spiels.
 * Diese Klasse ermöglicht es dem Benutzer, Statistiken zu speichern oder zurück zum Hauptmenü zu navigieren.
 * <p>Hauptverantwortlicher:</p>
 * <ul>
 *     <li>Matthias Henzel</li>
 * </ul>
 * <p>Mitwirkend:</p>
 * <ul>
 *     <li>Alex Becker</li>
 * </ul>
 */
public class StatisticsView implements InitializableController {

    private StatisticsController statisticsController;

    @FXML
    private Button buttonSaveYes;
    @FXML
    private Button mainMenuButton;
    @FXML
    private Button buttonSaveNo;
    @FXML
    private Label valueMoves;
    @FXML
    private Label valueTime;

    /**
     * Setzt den Controller, der die Logik für die Statistiken verwaltet.
     *
     * @param statisticsController Der Controller, der für die Statistiken zuständig ist.
     */
    public void setStatisticsController(StatisticsController statisticsController) {
        this.statisticsController = statisticsController;
    }

    /**
     * Verarbeitet den Klick auf den "Ja"-Button, um die Statistiken zu speichern.
     *
     * @param event Das ActionEvent, das den Klick auf den Button beschreibt.
     */
    @FXML
    void onButtonPressYes(ActionEvent event) {
        statisticsController.handleSaveYes();
    }

    /**
     * Verarbeitet den Klick auf den "Nein"-Button, um das Speichern der Statistiken abzulehnen.
     *
     * @param event Das ActionEvent, das den Klick auf den Button beschreibt.
     */
    @FXML
    void onButtonPressNo(ActionEvent event) {
        statisticsController.handleSaveNo();
    }

    /**
     * Verarbeitet den Klick auf den "Hauptmenü"-Button, um zum Hauptmenü zurückzukehren.
     *
     * @param event Das ActionEvent, das den Klick auf den Button beschreibt.
     */
    @FXML
    void onButtonPressMainMenu(ActionEvent event) {
        statisticsController.goBackToMainMenu();
    }

    /**
     * Aktualisiert die Anzeige der Statistiken mit den angegebenen Werten.
     *
     * @param moveCount Die Anzahl der Züge.
     * @param minutes   Die Anzahl der Minuten.
     * @param seconds   Die Anzahl der Sekunden.
     */
    public void updateStatistics(int moveCount, int minutes, int seconds) {
        valueMoves.setText(String.valueOf(moveCount));
        valueTime.setText(String.format("%02d:%02d", minutes, seconds));
    }

    @Override
    public void initializeWithSceneManager(SceneManager sceneManager) {
        // Initialisierungslogik, falls erforderlich
    }
}
