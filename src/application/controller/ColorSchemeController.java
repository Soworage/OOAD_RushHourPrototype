package application.controller;

import application.model.Color;
import application.model.MenuType;
import application.view.UserInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class ColorSchemeController {

    private UserInterface userInterface;
    @FXML
    private ComboBox<Color> comboBox;
    @FXML
    private Button confirmButton;

    public void setUserInterface(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    @FXML
    void initialize() {
        comboBox.getItems().addAll(Color.values());
        if (!comboBox.getItems().isEmpty()) {
            comboBox.getSelectionModel().selectFirst();
        }
    }

    @FXML
    void onClickConfirm(ActionEvent event) {
        userInterface.showMenu(MenuType.GAME_MENU);
    }

    @FXML
    void onClickMainMenuButton(ActionEvent event) {
        userInterface.showMenu(MenuType.MAIN_MENU);
    }

}
