package application.controller;

import application.model.MenuType;
import application.view.UserInterface;
import javafx.stage.Stage;

public class GameLauncher {

    private final Stage primaryStage;
    private UserInterface userInterface;

    public GameLauncher(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void run() {
        userInterface = new UserInterface(primaryStage);
        userInterface.showMenu(MenuType.MAIN_MENU);
    }
}
