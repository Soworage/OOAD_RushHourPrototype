package application.view;

import application.controller.ColorSchemeController;
import application.controller.DifficultySelectController;
import application.controller.GameController;
import application.controller.HighscoreController;
import application.controller.InputNameController;
import application.controller.MainMenuController;
import application.controller.StatisticsController;
import application.model.BoardManager;
import application.model.HighscoreTable;
import application.model.MenuType;
import application.model.UserStatistic;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import java.io.IOException;

public class UserInterface {

    private Stage primaryStage;
    private BoardManager boardManager;
    private HighscoreTable highscoreTable;
    private static final int GRID_SIZE = 6;
    private static final int RECT_SIZE = 40;

    public UserInterface(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.boardManager = new BoardManager();
        this.highscoreTable = new HighscoreTable();
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
            default -> throw new IllegalArgumentException("Unbekannter Menütyp: " + menuType);
        };

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            if (loader.getController() instanceof MainMenuController) {
                ((MainMenuController) loader.getController()).setUserInterface(this);
            }

            if (loader.getController() instanceof DifficultySelectController) {
                ((DifficultySelectController) loader.getController()).setUserInterface(this);
            }

            if (loader.getController() instanceof ColorSchemeController) {
                ((ColorSchemeController) loader.getController()).setUserInterface(this);
            }

            if (loader.getController() instanceof GameController) {
                ((GameController) loader.getController()).setBoardManager(boardManager);
                ((GameController) loader.getController()).setUserInterface(this);
            }

            if (loader.getController() instanceof StatisticsController) {
                ((StatisticsController) loader.getController()).setUserStatistic(UserStatistic.getInstance());
                ((StatisticsController) loader.getController()).setUserInterface(this);
                ((StatisticsController) loader.getController()).postInit();
            }

            if (loader.getController() instanceof InputNameController) {
                ((InputNameController) loader.getController()).setUserStatistic(UserStatistic.getInstance());
                ((InputNameController) loader.getController()).setUserInterface(this);
                ((InputNameController) loader.getController()).setHighscoreTable(highscoreTable);
            }

            if (loader.getController() instanceof HighscoreController) {
                ((HighscoreController) loader.getController()).setHighscoreTable(highscoreTable);
                ((HighscoreController) loader.getController()).setUserInterface(this);
                ((HighscoreController) loader.getController()).postInit();
            }

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
