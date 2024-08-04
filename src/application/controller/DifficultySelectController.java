package application.controller;

import application.model.Difficulty;
import application.model.GameSettings;
import application.model.MenuType;
import application.model.UserInterfaceAdapter;
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
    private UserInterfaceAdapter userInterfaceAdapter;

    public void setUserInterface(UserInterface userInterface) {
        userInterfaceAdapter = new UserInterfaceAdapter(userInterface);
    }

    @FXML
    void onClickEasy(ActionEvent event) {
        GameSettings.getInstance().setDifficulty(Difficulty.EASY);
        userInterfaceAdapter.showMenu(MenuType.COLORSCHEME_MENU);
    }

    @FXML
    void onClickHard(ActionEvent event) {
        GameSettings.getInstance().setDifficulty(Difficulty.HARD);
        userInterfaceAdapter.showMenu(MenuType.COLORSCHEME_MENU);
    }

    @FXML
    void onClickMedium(ActionEvent event) {
        GameSettings.getInstance().setDifficulty(Difficulty.MEDIUM);
        userInterfaceAdapter.showMenu(MenuType.COLORSCHEME_MENU);
    }

    @FXML
    void onMainMenuButtonClick(ActionEvent event) {
        userInterfaceAdapter.showMenu(MenuType.MAIN_MENU);
    }

}
