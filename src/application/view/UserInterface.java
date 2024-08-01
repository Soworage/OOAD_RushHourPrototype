package application.view;

import application.controller.*;
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


    /**
     * Lädt und zeigt die entsprechende Szene basierend auf dem MenuType.
     */
    public void showMenu(MenuType menuType) {
        String fxmlFile = switch (menuType) {
            case MenuType.MAIN_MENU -> "mainMenu.fxml";
            case MenuType.GAME_MENU -> "gameMenu.fxml";
            case MenuType.DIFFICULTY_MENU -> "difficultyMenu.fxml";
            case MenuType.COLORSCHEME_MENU -> "colorSchemeMenu.fxml";
            case MenuType.STATISTICS_MENU -> "statisticsMenu.fxml";
            case MenuType.HIGHSCORE_MENU -> "highScoreTable.fxml";
            case MenuType.INPUTNAME_MENU -> "inputNameField.fxml";
            default -> throw new IllegalArgumentException("Unbekannter Menütyp: " + menuType);
        };

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            //setze ui in controller, damit der controller das menü wechseln kann
            if(loader.getController() instanceof mainMenuController){
                ((mainMenuController) loader.getController()).setUserInterface(this);
            }

            if(loader.getController() instanceof  diffSelectController){
                ((diffSelectController) loader.getController()).setUserInterface(this);
            }

            if(loader.getController() instanceof colorSchemeController){
                ((colorSchemeController) loader.getController()).setUserInterface(this);
            }

            if(loader.getController() instanceof GameController){
                ((GameController) loader.getController()).setBoardManager(this.boardManager);
                ((GameController) loader.getController()).setUserInterface(this);
            }

            if(loader.getController() instanceof statisticsController){
                ((statisticsController) loader.getController()).setUserStatistic(UserStatistic.getInstance());
                ((statisticsController) loader.getController()).setUserInterface(this);
                ((statisticsController) loader.getController()).postInit();
            }

            if(loader.getController() instanceof inputNameController){
                ((inputNameController) loader.getController()).setUserStatistic(UserStatistic.getInstance());
                ((inputNameController) loader.getController()).setUserInterface(this);
                ((inputNameController) loader.getController()).setHighscoreTable(this.highscoreTable);
            }

            if(loader.getController() instanceof highscoreController){
                ((highscoreController) loader.getController()).setHighscoreTable(this.highscoreTable);
                ((highscoreController) loader.getController()).setUserInterface(this);
                ((highscoreController) loader.getController()).postInit();
            }


            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
