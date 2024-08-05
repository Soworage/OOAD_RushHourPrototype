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

/**
 * Ansichtsklasse für das Spiel, die die Benutzeroberfläche während des Spiels darstellt.
 * <p>Hauptverantwortlicher:</p>
 * <ul>
 *     <li>Matthias Henzel</li>
 * </ul>
 * <p>Mitwirkend:</p>
 * <ul>
 *     <li>Alex Becker</li>
 * </ul>
 */
public class GameView implements InitializableController {

    @FXML
    private Button mainMenuButton;

    @FXML
    private Label moveCountLabel;

    @FXML
    private Label timeCountLabel;

    @FXML
    private GridPane carGrid;

    private GameController gameController;

    public GameView() {
        // Standard-Konstruktor
    }

    /**
     * Setzt den Controller für das Spiel.
     *
     * @param gameController Der Controller für das Spiel.
     */
    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    /**
     * Behandelt das Klicken auf den Button, um zum Hauptmenü zurückzukehren.
     * Der Controller wird benachrichtigt, um die Übergabe zum Hauptmenü zu handhaben.
     *
     * @param event Das Ereignis, das beim Klicken auf den Hauptmenü-Button ausgelöst wird.
     */
    @FXML
    void backToMenuButton(ActionEvent event) {
        gameController.handleBackToMenu();
    }

    /**
     * Behandelt das Ereignis, wenn ein Drag-Vorgang die Grenze des Drop-Ziels verlässt.
     * Das Ereignis wird an den GameController weitergeleitet, um die Logik zu handhaben.
     *
     * @param event Das DragEvent, das beim Verlassen des Drag-Ziels ausgelöst wird.
     */
    @FXML
    void onDragExited(DragEvent event) {
        gameController.handleDragExited(event);
    }

    @Override
    public void initializeWithSceneManager(SceneManager sceneManager) {
        // Noch nicht verwendet, aber vom Interface erforderlich
    }

    /**
     * Fügt ein Rechteck zum GridPane hinzu, um die Autos im Spiel darzustellen.
     *
     * @param rectangle Das Rectangle, das hinzugefügt werden soll.
     * @param col       Die Spalte im GridPane, in der das Rectangle platziert werden soll.
     * @param row       Die Zeile im GridPane, in der das Rectangle platziert werden soll.
     */
    public void addRectangleToGrid(Rectangle rectangle, int col, int row) {
        carGrid.add(rectangle, col, row);
    }

    /**
     * Gibt das GridPane zurück, das die Autos darstellt.
     *
     * @return Das GridPane mit den Autos.
     */
    public GridPane getCarGrid() {
        return carGrid;
    }

    /**
     * Gibt das Label für die Zeit zurück.
     *
     * @return Das Label, das die Zeit darstellt.
     */
    public Label getTimeCountLabel() {
        return timeCountLabel;
    }

    /**
     * Gibt das Label für die Anzahl der Züge zurück.
     *
     * @return Das Label, das die Anzahl der Züge darstellt.
     */
    public Label getMoveCountLabel() {
        return moveCountLabel;
    }

    /**
     * Aktualisiert das Zeit-Label mit den gegebenen Minuten und Sekunden.
     *
     * @param minutes Die Minuten, die im Zeit-Label angezeigt werden sollen.
     * @param seconds Die Sekunden, die im Zeit-Label angezeigt werden sollen.
     */
    public void updateTimerLabel(int minutes, int seconds) {
        timeCountLabel.setText(String.format("%02d:%02d", minutes, seconds));
    }

    /**
     * Aktualisiert das Züge-Label mit der angegebenen Anzahl der Züge.
     *
     * @param moveCount Die Anzahl der Züge, die im Züge-Label angezeigt werden soll.
     */
    public void updateMoveCountLabel(int moveCount) {
        moveCountLabel.setText(String.valueOf(moveCount));
    }
}
