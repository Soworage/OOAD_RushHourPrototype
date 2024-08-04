package application.view;

import application.model.MenuType;
import javafx.stage.Stage;

public class GameLauncher {

    private final Stage primaryStage;

    public GameLauncher(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void run() {
        UserInterface userInterface = new UserInterface(primaryStage);
        userInterface.showMenu(MenuType.MAIN_MENU);
    }
}
