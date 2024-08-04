package application.model;

import application.view.UserInterface;

public class UserInterfaceAdapter {
    private final UserInterface userInterface;

    public UserInterfaceAdapter(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    public void showMenu(MenuType menuType) {
        userInterface.showMenu(menuType);
    }
}
