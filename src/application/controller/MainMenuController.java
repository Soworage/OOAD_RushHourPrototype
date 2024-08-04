package application.controller;

import application.model.MenuType;
import application.model.UserInterfaceAdapter;
import application.view.UserInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenuController {
    @FXML
    private Button settingsButton;
    @FXML
    private Button buttonStart;
    private UserInterfaceAdapter userInterfaceAdapter;

    @FXML
    void onButtonPress(ActionEvent event) {
        userInterfaceAdapter.showMenu(MenuType.DIFFICULTY_MENU);
    }

    public void setUserInterface(UserInterface userInterface) {
        userInterfaceAdapter = new UserInterfaceAdapter(userInterface);
    }

    @FXML
    void onSettingsButtonPress(ActionEvent event) {
        userInterfaceAdapter.showMenu(MenuType.RESETPIN_MENU);
    }
}
