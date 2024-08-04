package application;

import application.view.GameLauncher;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            //create game instance
            GameLauncher gameLauncher = new GameLauncher(primaryStage);
            //launch game
            gameLauncher.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
