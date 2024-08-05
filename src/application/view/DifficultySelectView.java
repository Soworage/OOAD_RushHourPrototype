package application.view;

import application.controller.DifficultySelectController;
import application.controller.InitializableController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Ansichtsklasse für das Menü zur Auswahl der Schwierigkeitsstufe.
 * <p>Hauptverantwortlicher:</p>
 * <ul>
 *     <li>Matthias Henzel</li>
 * </ul>
 * <p>Mitwirkend:</p>
 * <ul>
 *     <li>Alex Becker</li>
 *     <li>Alex Mihel</li>
 * </ul>
 */
public class DifficultySelectView implements InitializableController {

    private DifficultySelectController difficultySelectController;

    @FXML
    private Button easyButton;

    @FXML
    private Button hardButton;

    @FXML
    private Button mediumButton;

    @FXML
    private Button mainMenuButton;

    /**
     * Setzt den Controller für die Schwierigkeitsstufen-Auswahl.
     *
     * @param difficultySelectController Der Controller für die Schwierigkeitsstufen-Auswahl.
     */
    public void setDifficultySelectController(DifficultySelectController difficultySelectController) {
        this.difficultySelectController = difficultySelectController;
    }

    /**
     * Behandelt das Klicken auf den Button für die einfache Schwierigkeitsstufe.
     * Der Controller wird benachrichtigt, um die einfache Schwierigkeitsstufe auszuwählen.
     *
     * @param event Das Ereignis, das beim Klicken auf den Button für einfache Schwierigkeitsstufe ausgelöst wird.
     */
    @FXML
    void onClickEasy(ActionEvent event) {
        difficultySelectController.selectDifficultyEasy();
    }

    /**
     * Behandelt das Klicken auf den Button für die schwierige Schwierigkeitsstufe.
     * Der Controller wird benachrichtigt, um die schwierige Schwierigkeitsstufe auszuwählen.
     *
     * @param event Das Ereignis, das beim Klicken auf den Button für schwierige Schwierigkeitsstufe ausgelöst wird.
     */
    @FXML
    void onClickHard(ActionEvent event) {
        difficultySelectController.selectDifficultyHard();
    }

    /**
     * Behandelt das Klicken auf den Button für die mittlere Schwierigkeitsstufe.
     * Der Controller wird benachrichtigt, um die mittlere Schwierigkeitsstufe auszuwählen.
     *
     * @param event Das Ereignis, das beim Klicken auf den Button für mittlere Schwierigkeitsstufe ausgelöst wird.
     */
    @FXML
    void onClickMedium(ActionEvent event) {
        difficultySelectController.selectDifficultyMedium();
    }

    /**
     * Behandelt das Klicken auf den Button für das Hauptmenü.
     * Der Controller wird benachrichtigt, um zum Hauptmenü zurückzukehren.
     *
     * @param event Das Ereignis, das beim Klicken auf den Hauptmenü-Button ausgelöst wird.
     */
    @FXML
    void onMainMenuButtonClick(ActionEvent event) {
        difficultySelectController.goBackToMainMenu();
    }

    @Override
    public void initializeWithSceneManager(SceneManager sceneManager) {
        // Noch nicht verwendet, aber vom Interface erforderlich
    }
}

