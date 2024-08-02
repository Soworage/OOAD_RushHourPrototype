package application.controller;

import application.model.MenuType;
import application.model.PINManager;
import application.view.UserInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ResetController {

    @FXML
    private Button buttonBack;
    @FXML
    private Button buttonAccept;
    private UserInterface userInterface;
    private PINManager pinManager;
    @FXML
    private TextField codeInput;

    public void setPinManager(PINManager pinManager) {
        this.pinManager = pinManager;
    }

    public void setUserInterface(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    @FXML
    void onAcceptButtonPress(ActionEvent event) {
        if (pinManager.userEnterPIN(codeInput.getText())) {
            System.out.println("Service member initiated a game reset");
            //... reset files here ----- prototype ??
            userInterface.showMenu(MenuType.MAIN_MENU);
        }
    }

    @FXML
    void onBackButtonPress(ActionEvent event) {
        userInterface.showMenu(MenuType.MAIN_MENU);
    }

}
