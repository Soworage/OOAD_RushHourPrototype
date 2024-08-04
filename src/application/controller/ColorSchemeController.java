package application.controller;

import application.model.Color;
import application.model.MenuType;
import application.model.UserInterfaceAdapter;
import application.view.UserInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class ColorSchemeController {

    @FXML
    private Button mainMenuButton;
    @FXML
    private Button confirmButton;
    private UserInterfaceAdapter userInterfaceAdapter;
    @FXML
    private ComboBox<Color> comboBox;

    public void setUserInterface(UserInterface userInterface) {
        userInterfaceAdapter = new UserInterfaceAdapter(userInterface);
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
        userInterfaceAdapter.showMenu(MenuType.GAME_MENU);
    }

    @FXML
    void onClickMainMenuButton(ActionEvent event) {
        userInterfaceAdapter.showMenu(MenuType.MAIN_MENU);
    }

}
