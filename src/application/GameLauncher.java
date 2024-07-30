package application;

import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


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
