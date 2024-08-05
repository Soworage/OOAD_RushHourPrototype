package application.controller;

import application.model.PINManager;

/* Hauptverantwortlicher: Alex Becker */

public class ResetController {

    private final PINManager pinManager;
    private final Coordinator coordinator;

    public ResetController(PINManager pinManager, Coordinator coordinator) {
        this.pinManager = pinManager;
        this.coordinator = coordinator;
    }


    public void processPinCode(String pinCode) {
        if (pinManager.userEnterPIN(pinCode)) {
            System.out.println("Service member initiated a game reset");
            coordinator.showMainMenu();
        } else {
            System.out.println("Invalid PIN entered");
            coordinator.showMainMenu();
        }
    }

    public void goBackToMainMenu() {
        coordinator.showMainMenu();
    }
}
