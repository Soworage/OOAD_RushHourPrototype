package application.controller;

import application.model.Color;
import application.model.GameSettings;

/* Hauptverantwortlicher: Alex Becker */
/* Mitwirkend: Matthias Henzel */

public class ColorSchemeController {

    private final Coordinator coordinator;

    public ColorSchemeController(Coordinator coordinator) {
        this.coordinator = coordinator;
    }

    public void handleConfirm(Color selectedColor) {
        GameSettings.getInstance().setColorScheme(selectedColor);
        coordinator.showGame();
    }

    public void handleBackToMainMenu() {
        coordinator.showMainMenu();
    }
}
