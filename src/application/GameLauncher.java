package application;

import javafx.stage.Stage;

public class GameLauncher {
    private Stage primaryStage;

    public GameLauncher(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void run(){
        //Create user interface instance
        UserInterface userInterface = new UserInterface(primaryStage);


    }

}
