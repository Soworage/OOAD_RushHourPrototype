package application.controller;

/* Hauptverantwortlicher: Alex Becker */
/* Mitwirkend: Matthias Henzel */
public class MainMenuController {

    private final Coordinator coordinator;

    public MainMenuController(Coordinator coordinator) {
        this.coordinator = coordinator;
    }


    public void handleStartButtonPress() {
        coordinator.showDifficultyMenu();
    }

    public void handleSettingsButtonPress() {
        coordinator.showResetPinMenu();
    }

}