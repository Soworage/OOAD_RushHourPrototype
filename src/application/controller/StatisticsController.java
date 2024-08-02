package application.controller;

import application.model.MenuType;
import application.model.UserStatistic;
import application.view.UserInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class StatisticsController {
    @FXML
    private Button buttonSaveYes;
    @FXML
    private Button mainMenuButton;
    @FXML
    private Button buttonSaveNo;
    private UserStatistic userStatistic;
    private UserInterface userInterface;

    @FXML
    private Label valueMoves;

    @FXML
    private Label valueTime;

    public void setUserInterface(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    public void setUserStatistic(UserStatistic userStatistic) {
        this.userStatistic = userStatistic;
    }

    public void postInit() {
        valueMoves.setText(String.valueOf(userStatistic.getMoveCount()));
        int minutes = userStatistic.getSeconds() / 60;
        int secs = userStatistic.getSeconds() % 60;
        valueTime.setText(String.format("%02d:%02d", minutes, secs));
    }

    @FXML
    void onButtonPressNo(ActionEvent event) {
        userInterface.showMenu(MenuType.HIGHSCORE_MENU);
    }

    @FXML
    void onButtonPressYes(ActionEvent event) {
        userInterface.showMenu(MenuType.INPUTNAME_MENU);
    }

    @FXML
    void onButtonPressMainMenu(ActionEvent event) {
        userInterface.showMenu(MenuType.MAIN_MENU);
    }


}
