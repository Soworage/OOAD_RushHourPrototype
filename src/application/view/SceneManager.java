package application.view;

import application.controller.InitializableController;
import application.model.MenuType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/* Hauptverantwortlicher: Matthias Henzel */
public class SceneManager {

    private final Stage primaryStage;
    private InitializableController currentController;

    public SceneManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setController(InitializableController controller) {
        this.currentController = controller;
    }

    public void showMenu(MenuType menuType) {
        String fxmlFile = switch (menuType) {
            case MAIN_MENU -> "mainMenu.fxml";
            case GAME_MENU -> "gameMenu.fxml";
            case DIFFICULTY_MENU -> "difficultyMenu.fxml";
            case COLORSCHEME_MENU -> "colorSchemeMenu.fxml";
            case STATISTICS_MENU -> "statisticsMenu.fxml";
            case HIGHSCORE_MENU -> "highScoreTable.fxml";
            case INPUTNAME_MENU -> "inputNameField.fxml";
            case RESETPIN_MENU -> "pinMenu.fxml";
        };

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            loader.setController(currentController); // Correctly set the controller here
            Parent root = loader.load(); // Load the FXML after setting the controller
            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
