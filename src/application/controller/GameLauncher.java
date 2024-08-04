package application.controller;

import javafx.stage.Stage;

public class GameLauncher {

    private final Stage primaryStage;

    public GameLauncher(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void run() {
        // Initialize Coordinator with the primary stage
        Coordinator coordinator = new Coordinator(primaryStage);

        // Start the application by showing the main menu
        coordinator.start();
    }
}
