package application.view;

import application.controller.GameController;
import application.controller.InitializableController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

/* Hauptverantwortlicher: Matthias Henzel */
public class GameView implements InitializableController {

    @FXML
    private Button mainMenuButton;
    @FXML
    private Label moveCountLabel;
    @FXML
    private Label timeCountLabel;
    @FXML
    private GridPane carGrid = new GridPane();
    private GameController gameController;

    public GameView() {
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    @FXML
    void backToMenuButton(ActionEvent event) {
        // Notify GameController to handle menu transition
        gameController.handleBackToMenu();
    }

    @FXML
    void onDragExited(DragEvent event) {
        // Pass the event to GameController for handling the logic
        gameController.handleDragExited(event);
    }

    @Override
    public void initializeWithSceneManager(SceneManager sceneManager) {

    }

    public void addRectangleToGrid(Rectangle rectangle, int col, int row) {
        carGrid.add(rectangle, col, row);
    }

    public GridPane getCarGrid() {
        return carGrid;
    }

    public Label getTimeCountLabel() {
        return timeCountLabel;
    }

    public Label getMoveCountLabel() {
        return moveCountLabel;
    }

    public void updateTimerLabel(int minutes, int seconds) {
        timeCountLabel.setText(String.format("%02d:%02d", minutes, seconds));
    }

    public void updateMoveCountLabel(int moveCount) {
        moveCountLabel.setText(String.valueOf(moveCount));
    }

}
