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

/* Hauptverantwortlicher: Matthias Henzel */

/**
 * Die Klasse Coordinator ist verantwortlich für die Koordination der verschiedenen
 * Teile der Anwendung. Sie verwaltet die Navigation zwischen den verschiedenen
 * Menüs und Spielansichten und stellt sicher, dass die richtigen Controller
 * und Views initialisiert und angezeigt werden.
 */
public class Coordinator {
    private final SceneManager sceneManager;
    private final BoardManager boardManager;
    private final UserStatistic userStatistic;
    private final PINManager pinManager;
    private final InactivityNotifier inactivityNotifier;

    /**
     * Konstruktor für die Klasse Coordinator. Initialisiert die verschiedenen
     * Manager und das Hauptfenster der Anwendung.
     *
     * @param primaryStage Das Hauptfenster der JavaFX-Anwendung.
     */
    public Coordinator(Stage primaryStage) {
        this.sceneManager = new SceneManager(primaryStage);
        this.boardManager = new BoardManager();
        this.userStatistic = UserStatistic.getInstance();
        this.pinManager = new PINManager();
        this.inactivityNotifier = InactivityNotifier.getInstance(this);
    }

    /**
     * Startet die Anwendung, indem das Hauptmenü angezeigt wird.
     */
    public void start() {
        showMainMenu();
    }

    /**
     * Zeigt das Hauptmenü an.
     */
    public void showMainMenu() {
        MainMenuView mainMenuView = new MainMenuView();
        MainMenuController mainMenuController = new MainMenuController(this);

        mainMenuView.setMainMenuController(mainMenuController);

        sceneManager.setController(mainMenuView);
        sceneManager.showMenu(MenuType.MAIN_MENU);

        inactivityNotifier.resetTimer();
    }

    /**
     * Zeigt das Menü zur Auswahl des Schwierigkeitsgrades an.
     */
    public void showDifficultyMenu() {
        DifficultySelectView difficultySelectView = new DifficultySelectView();
        DifficultySelectController difficultySelectController = new DifficultySelectController(this);

        difficultySelectView.setDifficultySelectController(difficultySelectController);

        sceneManager.setController(difficultySelectView);
        sceneManager.showMenu(MenuType.DIFFICULTY_MENU);

        inactivityNotifier.startTimer();
    }

    /**
     * Zeigt das Menü zur Auswahl des Farbschemas an.
     */
    public void showColorSchemeMenu() {
        ColorSchemeView colorSchemeView = new ColorSchemeView();
        ColorSchemeController colorSchemeController = new ColorSchemeController(this);

        colorSchemeView.setColorSchemeController(colorSchemeController);

        sceneManager.setController(colorSchemeView);
        sceneManager.showMenu(MenuType.COLORSCHEME_MENU);

        inactivityNotifier.startTimer();
    }

    /**
     * Zeigt das Menü zum Zurücksetzen der PIN an.
     */
    public void showResetPinMenu() {
        ResetView resetView = new ResetView();
        ResetController resetController = new ResetController(pinManager, this);

        resetView.setResetController(resetController);

        sceneManager.setController(resetView);
        sceneManager.showMenu(MenuType.RESETPIN_MENU);

        inactivityNotifier.startTimer();
    }

    /**
     * Zeigt das Spiel an.
     */
    public void showGame() {
        GameView gameView = new GameView();
        GameController gameController = new GameController(boardManager, userStatistic, this, gameView);

        gameView.setGameController(gameController);

        sceneManager.setController(gameView);
        sceneManager.showMenu(MenuType.GAME_MENU);
        gameController.postInit();

        inactivityNotifier.startTimer();
    }

    /**
     * Zeigt die Statistikansicht an.
     */
    public void showStatistics() {
        StatisticsView statisticsView = new StatisticsView();
        StatisticsController statisticsController = new StatisticsController(userStatistic, this, statisticsView);

        sceneManager.setController(statisticsView);
        sceneManager.showMenu(MenuType.STATISTICS_MENU);
        statisticsController.postInit();

        inactivityNotifier.startTimer();
    }

    /**
     * Zeigt das Highscore-Menü an.
     */
    public void showHighScoreMenu() {
        HighscoreView highscoreView = new HighscoreView();
        HighscoreController highscoreController = new HighscoreController(userStatistic, this, boardManager);

        highscoreView.setHighscoreController(highscoreController);
        highscoreController.setHighscoreView(highscoreView);

        sceneManager.setController(highscoreView);
        sceneManager.showMenu(MenuType.HIGHSCORE_MENU);
        highscoreController.postInit();

        inactivityNotifier.startTimer();
    }

    /**
     * Zeigt das Menü zur Eingabe des Namens an.
     */
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

        inactivityNotifier.startTimer();
    }

    /**
     * Gibt den Inaktivitätsbenachrichtiger zurück.
     *
     * @return Der Inaktivitätsbenachrichtiger.
     */
    public InactivityNotifier getInactivityNotifier() {
        return inactivityNotifier;
    }
}
