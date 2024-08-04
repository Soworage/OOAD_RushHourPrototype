package application.controller;

import application.model.UserStatistic;
import application.view.StatisticsView;

public class StatisticsController {

    private final UserStatistic userStatistic;
    private final Coordinator coordinator;
    private final StatisticsView statisticsView;

    public StatisticsController(UserStatistic userStatistic, Coordinator coordinator, StatisticsView statisticsView) {
        this.userStatistic = userStatistic;
        this.coordinator = coordinator;
        this.statisticsView = statisticsView;
        this.statisticsView.setStatisticsController(this);
    }


    public void postInit() {
        // Display user statistics
        int moveCount = userStatistic.getMoveCount();
        int minutes = userStatistic.getSeconds() / 60;
        int seconds = userStatistic.getSeconds() % 60;
        statisticsView.updateStatistics(moveCount, minutes, seconds);
    }

    public void handleSaveYes() {
        coordinator.showInputNameMenu();
    }

    public void handleSaveNo() {
        coordinator.showHighScoreMenu();
    }

    public void goBackToMainMenu() {
        coordinator.showMainMenu();
    }
}
