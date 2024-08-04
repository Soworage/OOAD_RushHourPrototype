package application.controller;

import application.model.GameSettings;
import application.model.HighscoreTable;
import application.model.UserStatistic;
import application.model.PINManager;
import application.model.BoardManager;
import application.model.MenuType;
import application.view.GameView;
import application.view.HighscoreView;
import application.view.InputNameView;
import application.view.ResetView;
import application.view.ColorSchemeView;
import application.view.DifficultySelectView;
import application.view.StatisticsView;
import application.view.MainMenuView;
import application.view.SceneManager;
import javafx.stage.Stage;

public class Coordinator {

    private final SceneManager sceneManager;
    private final BoardManager boardManager;
    private final UserStatistic userStatistic;
    private final PINManager pinManager;

    public Coordinator(Stage primaryStage) {
        this.sceneManager = new SceneManager(primaryStage);
        this.boardManager = new BoardManager();
        this.userStatistic = UserStatistic.getInstance();
        this.pinManager = new PINManager();
    }

    public void start() {
        showMainMenu();
    }

    public void showMainMenu() {
        MainMenuView mainMenuView = new MainMenuView();
        MainMenuController mainMenuController = new MainMenuController(this);

        mainMenuView.setMainMenuController(mainMenuController);

        sceneManager.setController(mainMenuView);
        sceneManager.showMenu(MenuType.MAIN_MENU);
    }

    public void showDifficultyMenu() {
        DifficultySelectView difficultySelectView = new DifficultySelectView();
        DifficultySelectController difficultySelectController = new DifficultySelectController(this);

        difficultySelectView.setDifficultySelectController(difficultySelectController);

        sceneManager.setController(difficultySelectView);
        sceneManager.showMenu(MenuType.DIFFICULTY_MENU);
    }

    public void showColorSchemeMenu() {
        ColorSchemeView colorSchemeView = new ColorSchemeView();
        ColorSchemeController colorSchemeController = new ColorSchemeController(this);

        colorSchemeView.setColorSchemeController(colorSchemeController);

        sceneManager.setController(colorSchemeView);
        sceneManager.showMenu(MenuType.COLORSCHEME_MENU);
    }

    public void showResetPinMenu() {
        ResetView resetView = new ResetView();
        ResetController resetController = new ResetController(pinManager, this);

        resetView.setResetController(resetController);

        sceneManager.setController(resetView);
        sceneManager.showMenu(MenuType.RESETPIN_MENU);
    }

    public void showGame() {
        GameView gameView = new GameView();
        GameController gameController = new GameController(boardManager, userStatistic, this, gameView);

        gameView.setGameController(gameController);

        sceneManager.setController(gameView);
        sceneManager.showMenu(MenuType.GAME_MENU);
        gameController.postInit();
    }

    public void showStatistics() {
        StatisticsView statisticsView = new StatisticsView();
        StatisticsController statisticsController = new StatisticsController(userStatistic, this, statisticsView);
        sceneManager.setController(statisticsView);
        sceneManager.showMenu(MenuType.STATISTICS_MENU);
        statisticsController.postInit();
    }


    public void showHighScoreMenu() {
        HighscoreView highscoreView = new HighscoreView();
        HighscoreController highscoreController = new HighscoreController(userStatistic, this, boardManager);

        highscoreView.setHighscoreController(highscoreController);
        highscoreController.setHighscoreView(highscoreView);

        sceneManager.setController(highscoreView);
        sceneManager.showMenu(MenuType.HIGHSCORE_MENU);
        highscoreController.postInit();
    }

    public void showInputNameMenu() {
        HighscoreTable highscoreTable = boardManager.getHighScoreTableForBoard(
                userStatistic.getSelectedBoard().getBoardId(),
                GameSettings.getInstance().getDifficulty()
        );

        InputNameView inputNameView = new InputNameView();
        InputNameController inputNameController = new InputNameController(userStatistic, this, highscoreTable);

        inputNameView.setInputNameController(inputNameController);

        sceneManager.setController(inputNameView);
        sceneManager.showMenu(MenuType.INPUTNAME_MENU);
    }
}
