package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;


public class GameController {


    private static final int GRID_SIZE = 6;  // Beispielgröße
    private static final int RECT_SIZE = 100; // Beispielgröße
    private Board board;
    private BoardManager boardManager;


    public BoardManager getBoardManager() {
        return boardManager;
    }

    public void setBoardManager(BoardManager boardManager) {
        this.boardManager = boardManager;
    }

    @FXML
    private Button backToMenuButton;

    @FXML
    private Label movesC;

    @FXML
    private Label timeC;

    @FXML
    private GridPane carGrid;


    @FXML
    void backToMenuButton(ActionEvent event) {

    }

    @FXML
    void initialize() {
        boardManager = new BoardManager(); //ENTFERNEN !! NUR ZU TESTZWECKEN !!
        board = boardManager.getBoards().getFirst();
        populateGridPane();
    }

    private void populateGridPane() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                Rectangle rect = new Rectangle(RECT_SIZE, RECT_SIZE);
                rect.setFill(Color.WHITE);

                final int currentRow = row;
                final int currentCol = col;

                Car car = board.getCarAt(row, col);
                if(car != null){
                    rect.setFill(Color.RED);

                } else {
                    rect.setStroke(Color.BLACK);
                }
                // Event-Handler für den Mausklick hinzufügen
                rect.setOnMouseClicked(event -> handleRectangleClick(event, currentCol, currentRow));
                

                // Hier kannst du auch Event-Handler hinzufügen
                carGrid.add(rect, col, row);
            }
        }
    }

    private void handleRectangleClick(MouseEvent event, int col, int row) {
        System.out.println("Rectangle clicked at: Column " + col + ", Row " + row);
    }


}
