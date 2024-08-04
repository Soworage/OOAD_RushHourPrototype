package application.controller;

import application.model.*;
import application.view.SceneManager;
import javafx.stage.Stage;

public class Coordinator {

    private final SceneManager sceneManager;
    private final BoardManager boardManager;
    private final UserStatistic userStatistic;
    private final GameSettings gameSettings;
    private final PINManager pinManager;

    public Coordinator(Stage primaryStage) {
        this.sceneManager = new SceneManager(primaryStage);
        this.boardManager = new BoardManager();
        this.userStatistic = UserStatistic.getInstance();
        this.gameSettings = GameSettings.getInstance();
        this.pinManager = new PINManager();
    }

    public void start() {
        showMainMenu();
    }

    public void showMainMenu() {
        MainMenuController mainMenuController = new MainMenuController(this);
        sceneManager.setController(mainMenuController);
        sceneManager.showMenu(MenuType.MAIN_MENU);
    }

    public void showDifficultyMenu() {
        DifficultySelectController difficultySelectController = new DifficultySelectController(this);
        sceneManager.setController(difficultySelectController);
        sceneManager.showMenu(MenuType.DIFFICULTY_MENU);
    }

    public void showColorSchemeMenu() {
        ColorSchemeController colorSchemeController = new ColorSchemeController(this);
        sceneManager.setController(colorSchemeController);
        sceneManager.showMenu(MenuType.COLORSCHEME_MENU);
    }

    public void showResetPinMenu() {
        ResetController resetController = new ResetController(pinManager, this);
        sceneManager.setController(resetController);
        sceneManager.showMenu(MenuType.RESETPIN_MENU);
    }

    public void showGame() {
        GameController gameController = new GameController(boardManager, userStatistic, this);
        sceneManager.setController(gameController);
        sceneManager.showMenu(MenuType.GAME_MENU);
        gameController.postInit();
    }

    public void showStatistics() {
        StatisticsController statisticsController = new StatisticsController(userStatistic, this);
        sceneManager.setController(statisticsController);
        sceneManager.showMenu(MenuType.STATISTICS_MENU);
        statisticsController.postInit();
    }

    public void showHighScoreMenu() {
        HighscoreController highscoreController = new HighscoreController(userStatistic, this, boardManager);
        sceneManager.setController(highscoreController);
        sceneManager.showMenu(MenuType.HIGHSCORE_MENU);
        highscoreController.postInit();
    }

    public void showInputNameMenu() {
        HighscoreTable highscoreTable = boardManager.getHighScoreTableForBoard(
                userStatistic.getSelectedBoard().getBoardId(),
                GameSettings.getInstance().getDifficulty()
        );
        InputNameController inputNameController = new InputNameController(userStatistic, this, highscoreTable);
        sceneManager.setController(inputNameController);
        sceneManager.showMenu(MenuType.INPUTNAME_MENU);
    }
}
