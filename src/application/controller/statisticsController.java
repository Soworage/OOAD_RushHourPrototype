package application.controller;

import application.model.MenuType;
import application.model.UserStatistic;
import application.view.UserInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

public class statisticsController {

    private UserStatistic userStatistic;
    private UserInterface userInterface;

    public UserInterface getUserInterface() {
        return userInterface;
    }


    public void postInit(){
        this.valueMoves.setText(String.valueOf(userStatistic.getMoveCount()));
        int minutes = userStatistic.getSeconds() / 60;
        int secs = userStatistic.getSeconds() % 60;
        this.valueTime.setText(String.format("%02d:%02d", minutes, secs));
    }

    @FXML
    void onButtonPressNo(ActionEvent event) {
        userInterface.showMenu(MenuType.HIGHSCORE_MENU);
    }

    @FXML
    void onButtonPressYes(ActionEvent event) {
        userInterface.showMenu(MenuType.INPUTNAME_MENU);
    }


    public void setUserInterface(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    public UserStatistic getUserStatistic() {
        return userStatistic;
    }

    public void setUserStatistic(UserStatistic userStatistic) {
        this.userStatistic = userStatistic;
    }

    @FXML
    private Button buttonSaveNo;

    @FXML
    private Button buttonSaveYes;

    @FXML
    private Label valueMoves;

    @FXML
    private Label valueTime;

}
