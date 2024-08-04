package application.controller;

import application.model.UserStatistic;
import application.view.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class StatisticsController implements InitializableController {

    private final UserStatistic userStatistic;
    private final Coordinator coordinator;
    @FXML
    private Button buttonSaveYes;
    @FXML
    private Button mainMenuButton;
    @FXML
    private Button buttonSaveNo;
    @FXML
    private Label valueMoves;
    @FXML
    private Label valueTime;

    public StatisticsController(UserStatistic userStatistic, Coordinator coordinator) {
        this.userStatistic = userStatistic;
        this.coordinator = coordinator;
    }

    @Override
    public void initializeWithSceneManager(SceneManager sceneManager) {
        // Not used, handled by coordinator
    }

    public void postInit() {
        // Display user statistics
        valueMoves.setText(String.valueOf(userStatistic.getMoveCount()));
        int minutes = userStatistic.getSeconds() / 60;
        int secs = userStatistic.getSeconds() % 60;
        valueTime.setText(String.format("%02d:%02d", minutes, secs));
    }

    @FXML
    void onButtonPressNo(ActionEvent event) {
        coordinator.showHighScoreMenu();
    }

    @FXML
    void onButtonPressYes(ActionEvent event) {
        coordinator.showInputNameMenu();
    }

    @FXML
    void onButtonPressMainMenu(ActionEvent event) {
        coordinator.showMainMenu();
    }
}
