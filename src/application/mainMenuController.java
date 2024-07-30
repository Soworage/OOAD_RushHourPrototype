package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class mainMenuController {

    private UserInterface userInterface;

    @FXML
    private Button buttonStart;

    @FXML
    void onButtonPress(ActionEvent event) {
        userInterface.showMenu(MenuType.DIFFICULTY_MENU);
    }

    public UserInterface getUserInterface() {
        return userInterface;
    }

    public void setUserInterface(UserInterface userInterface) {
        this.userInterface = userInterface;
    }
}
