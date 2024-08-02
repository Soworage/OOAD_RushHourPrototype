package application.controller;

import application.model.Difficulty;
import application.model.GameSettings;
import application.model.MenuType;
import application.view.UserInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DifficultySelectController {
    @FXML
    private Button easyButton;
    @FXML
    private Button hardButton;
    @FXML
    private Button mediumButton;
    @FXML
    private Button mainMenuButton;
    private UserInterface userInterface;

    public void setUserInterface(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    @FXML
    void onClickEasy(ActionEvent event) {
        GameSettings.getInstance().setDifficulty(Difficulty.EASY);
        userInterface.showMenu(MenuType.COLORSCHEME_MENU);
    }

    @FXML
    void onClickHard(ActionEvent event) {
        GameSettings.getInstance().setDifficulty(Difficulty.HARD);
        userInterface.showMenu(MenuType.COLORSCHEME_MENU);
    }

    @FXML
    void onClickMedium(ActionEvent event) {
        GameSettings.getInstance().setDifficulty(Difficulty.MEDIUM);
        userInterface.showMenu(MenuType.COLORSCHEME_MENU);
    }

    @FXML
    void onMainMenuButtonClick(ActionEvent event) {
        userInterface.showMenu(MenuType.MAIN_MENU);
    }

}
