package application.controller;

import javafx.stage.Stage;

public class GameLauncher {

    private final Stage primaryStage;
    private Coordinator coordinator;

    public GameLauncher(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void run() {
        // Initialize Coordinator with the primary stage
        coordinator = new Coordinator(primaryStage);

        // Start the application by showing the main menu
        coordinator.start();
    }
}
