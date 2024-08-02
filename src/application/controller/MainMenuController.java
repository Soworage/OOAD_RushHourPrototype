package application.controller;

import application.model.MenuType;
import application.view.UserInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenuController {

    private UserInterface userInterface;

    @FXML
    private Button buttonStart;

    @FXML
    void onButtonPress(ActionEvent event) {
        userInterface.showMenu(MenuType.DIFFICULTY_MENU);
    }

    public void setUserInterface(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    @FXML
    void onSettingsButtonPress(ActionEvent event) {
        userInterface.showMenu(MenuType.RESETPIN_MENU);
    }
}
