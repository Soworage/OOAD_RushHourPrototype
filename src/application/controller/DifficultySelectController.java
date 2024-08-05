package application.controller;

import application.model.Difficulty;
import application.model.GameSettings;

/**
  * <p>Hauptverantwortlicher:</p>
 *  * <ul>
   *     <li>Alex Becker</li>
  * </ul>
   * <p>Mitwirkende:</p>
   * <ul>
   *     <li>Alex Mihel</li>
   *     <li>Matthias Henzel</li>
   * </ul>
 * Die Klasse DifficultySelectController ist verantwortlich für die Verwaltung
 * der Auswahl des Schwierigkeitsgrades innerhalb der Anwendung. Sie ermöglicht
 * das Setzen des Schwierigkeitsgrades und die Navigation zu weiteren Menüs.
 */
public class DifficultySelectController {
    private final Coordinator coordinator;

    /**
     * Konstruktor für die Klasse DifficultySelectController. Initialisiert den
     * Controller mit einem Verweis auf den Koordinator der Anwendung.
     *
     * @param coordinator Der Koordinator, der für die Steuerung der Anwendungsflüsse
     *                    verantwortlich ist.
     */
    public DifficultySelectController(Coordinator coordinator) {
        this.coordinator = coordinator;
    }

    /**
     * Setzt den Schwierigkeitsgrad auf "Einfach" und zeigt das Menü zur
     * Farbschema-Auswahl an.
     */
    public void selectDifficultyEasy() {
        GameSettings.getInstance().setDifficulty(Difficulty.EASY);
        coordinator.showColorSchemeMenu();
    }

    /**
     * Setzt den Schwierigkeitsgrad auf "Schwer" und zeigt das Menü zur
     * Farbschema-Auswahl an.
     */
    public void selectDifficultyHard() {
        GameSettings.getInstance().setDifficulty(Difficulty.HARD);
        coordinator.showColorSchemeMenu();
    }

    /**
     * Setzt den Schwierigkeitsgrad auf "Mittel" und zeigt das Menü zur
     * Farbschema-Auswahl an.
     */
    public void selectDifficultyMedium() {
        GameSettings.getInstance().setDifficulty(Difficulty.MEDIUM);
        coordinator.showColorSchemeMenu();
    }

    /**
     * Kehrt zum Hauptmenü zurück.
     */
    public void goBackToMainMenu() {
        coordinator.showMainMenu();
    }
}
