package application.controller;

import application.model.Difficulty;
import application.model.GameSettings;

/* Hauptverantwortlicher: Alex Becker */
/* Mitwirkend: Alex Mihel, Matthias Henzel */
public class DifficultySelectController {
    private final Coordinator coordinator;

    public DifficultySelectController(Coordinator coordinator) {
        this.coordinator = coordinator;
    }


    public void selectDifficultyEasy() {
        GameSettings.getInstance().setDifficulty(Difficulty.EASY);
        coordinator.showColorSchemeMenu();
    }

    public void selectDifficultyHard() {
        GameSettings.getInstance().setDifficulty(Difficulty.HARD);
        coordinator.showColorSchemeMenu();
    }

    public void selectDifficultyMedium() {
        GameSettings.getInstance().setDifficulty(Difficulty.MEDIUM);
        coordinator.showColorSchemeMenu();
    }

    public void goBackToMainMenu() {
        coordinator.showMainMenu();
    }
}
