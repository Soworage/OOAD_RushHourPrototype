package application.controller;

import application.model.MenuType;
import application.model.UserInterfaceAdapter;
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
    private UserInterfaceAdapter userInterfaceAdapter;

    @FXML
    private Label valueMoves;

    @FXML
    private Label valueTime;

    public void setUserInterface(UserInterface userInterface) {
        userInterfaceAdapter = new UserInterfaceAdapter(userInterface);
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
        userInterfaceAdapter.showMenu(MenuType.HIGHSCORE_MENU);
    }

    @FXML
    void onButtonPressYes(ActionEvent event) {
        userInterfaceAdapter.showMenu(MenuType.INPUTNAME_MENU);
    }

    @FXML
    void onButtonPressMainMenu(ActionEvent event) {
        userInterfaceAdapter.showMenu(MenuType.MAIN_MENU);
    }


}
