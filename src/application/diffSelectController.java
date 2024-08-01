package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;

public class diffSelectController {
    private UserInterface userInterface;

    public UserInterface getUserInterface() {
        return userInterface;
    }

    public void setUserInterface(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    @FXML
    private Button easyButton;

    @FXML
    private Button hardButton;

    @FXML
    private Button mediumButton;

    @FXML
    void onClickEasy(ActionEvent event) {

        GameSettings.getInstance().setDifficulty(Difficulty.EASY);
        getUserInterface().showMenu(MenuType.COLORSCHEME_MENU);
    }

    @FXML
    void onClickHard(ActionEvent event) {
        GameSettings.getInstance().setDifficulty(Difficulty.HARD);
        getUserInterface().showMenu(MenuType.COLORSCHEME_MENU);

    }

    @FXML
    void onClickMedium(ActionEvent event) {
        GameSettings.getInstance().setDifficulty(Difficulty.MEDIUM);
        getUserInterface().showMenu(MenuType.COLORSCHEME_MENU);
    }



}
