package application.controller;

import application.model.HighscoreEntry;
import application.model.HighscoreTable;
import application.model.UserStatistic;
import application.model.GameSettings;
import application.model.BoardManager;
import application.model.Difficulty;
import application.view.HighscoreView;

import java.util.Comparator;
import java.util.List;

public class HighscoreController {
    private final UserStatistic userStatistic;
    private final Coordinator coordinator;
    private final BoardManager boardManager;
    private HighscoreView highscoreView;

    public HighscoreController(UserStatistic userStatistic, Coordinator coordinator, BoardManager boardManager) {
        this.userStatistic = userStatistic;
        this.coordinator = coordinator;
        this.boardManager = boardManager;
    }

    public void setHighscoreView(HighscoreView highscoreView) {
        this.highscoreView = highscoreView;
    }

    public void handleBackToMainMenu() {
        coordinator.showMainMenu();
    }


    public void postInit() {
        highscoreView.initializeTableColumns();
        populateTable();
        highscoreView.updateLabels(
                String.valueOf(userStatistic.getSelectedBoard().getBoardId()),
                GameSettings.getInstance().getDifficulty().toString()
        );

        cleanUpSession();
    }

    private void populateTable() {
        HighscoreTable highscoreTable = getHighscoreTable();
        List<HighscoreEntry> entries = highscoreTable.getHighscoreList();
        entries.sort(Comparator.comparing(HighscoreEntry::getMoveCount)
                .thenComparing(HighscoreEntry::getElapsedTime));

        highscoreView.populateTable(entries);
    }

    private HighscoreTable getHighscoreTable() {
        int boardId = userStatistic.getSelectedBoard().getBoardId();
        Difficulty difficulty = GameSettings.getInstance().getDifficulty();
        return boardManager.getHighScoreTableForBoard(boardId, difficulty);
    }

    private void cleanUpSession() {
        GameSettings.getInstance().resetToDefault();
        userStatistic.setToDefault();
    }
}
