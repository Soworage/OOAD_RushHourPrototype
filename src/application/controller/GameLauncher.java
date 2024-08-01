package application.controller;

import application.model.MenuType;
import application.view.UserInterface;
import javafx.stage.Stage;


public class GameLauncher {
    private Stage primaryStage;
    private UserInterface userInterface;


    public GameLauncher(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void run(){
        //Create user interface instance
        userInterface = new UserInterface(primaryStage);
        //open main menu
        userInterface.showMenu(MenuType.MAIN_MENU);
    }

}
