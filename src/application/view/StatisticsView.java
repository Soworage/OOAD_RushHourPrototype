package application.view;

import application.controller.StatisticsController;
import application.controller.InitializableController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/* Hauptverantwortlicher: Matthias Henzel */
/* Mitwirkend: Alex Becker */
public class StatisticsView implements InitializableController {

    private StatisticsController statisticsController;

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

    public void setStatisticsController(StatisticsController statisticsController) {
        this.statisticsController = statisticsController;
    }

    @FXML
    void onButtonPressYes(ActionEvent event) {
        statisticsController.handleSaveYes();
    }

    @FXML
    void onButtonPressNo(ActionEvent event) {
        statisticsController.handleSaveNo();
    }

    @FXML
    void onButtonPressMainMenu(ActionEvent event) {
        statisticsController.goBackToMainMenu();
    }

    public void updateStatistics(int moveCount, int minutes, int seconds) {
        valueMoves.setText(String.valueOf(moveCount));
        valueTime.setText(String.format("%02d:%02d", minutes, seconds));
    }

    @Override
    public void initializeWithSceneManager(SceneManager sceneManager) {
    }
}