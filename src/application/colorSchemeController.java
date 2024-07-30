package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class colorSchemeController {

    private UserInterface userInterface;

    public UserInterface getUserInterface() {
        return userInterface;
    }

    public void setUserInterface(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    @FXML
    void initialize() {
        // Füge die Enum-Werte zur ComboBox hinzu
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
        getUserInterface().showMenu(MenuType.GAME_MENU);
    }

}
