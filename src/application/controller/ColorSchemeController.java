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

    public UserInterface getUserInterface() {
        return userInterface;
    }

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
    private ComboBox<Color> comboBox;

    @FXML
    private Button confirmButton;

    @FXML
    void onClickConfirm(ActionEvent event) {
        userInterface.showMenu(MenuType.GAME_MENU);
    }
}
