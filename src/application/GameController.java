package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import org.w3c.dom.css.Rect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GameController implements CarObserver {

    private static final int GRID_SIZE = 6;  // Beispielgröße
    private static final int RECT_SIZE = 100; // Beispielgröße
    private Board board;
    private BoardManager boardManager;
    private Car selectedCar;
    private Rectangle selectedRect;
    private Map<Car, List<Rectangle>> carRectangleMap = new HashMap<>();
    private List<Rectangle> selectedRectangleList = new ArrayList<>();

    public BoardManager getBoardManager() {
        return boardManager;
    }

    public void setBoardManager(BoardManager boardManager) {
        this.boardManager = boardManager;
    }

    private UserInterface userInterface;

    public UserInterface getUserInterface() {
        return userInterface;
    }
    public void setUserInterface(UserInterface userInterface) {
        this.userInterface = userInterface;
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
                getUserInterface().showMenu(MenuType.MAIN_MENU);
    }

    @FXML
    void initialize() {
        BoardManager boardManager1 = new BoardManager();
        Difficulty selectedDifficulty = GameSettings.getInstance().getDifficulty();
        board = boardManager1.giveBoardToDiff(selectedDifficulty);
        board.subscribeToUpdates(this);
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
                    rect.setFill(car.getCarColor());
                    carRectangleMap.computeIfAbsent(car, k -> new ArrayList<>()).add(rect);
                }

                rect.setStroke(Color.BLACK);

                // Event-Handler für den Mausklick hinzufügen
                rect.setOnMouseClicked(event -> handleRectangleClick(event, currentCol, currentRow));
                rect.setOnDragDetected(mouseEvent -> dragDetected(mouseEvent, currentCol, currentRow, rect));
                rect.setOnDragDone(event -> dragEnded(event, currentCol, currentRow) );
                rect.setOnDragOver(event -> handleDragOver(event));
                rect.setOnDragEntered(event -> handleDragEntered(event));
                rect.setOnDragDropped(event -> handleDragDropped(event, currentCol, currentRow));
                rect.setOnMouseDragged(event -> onMouseReleased(event, currentCol, currentRow));
                carGrid.add(rect, col, row);
            }
        }
    }

    private void handleRectangleClick(MouseEvent event, int col, int row) {
        if(selectedCar != null){
            Car car = board.getCarAt(row, col);

            System.out.println(carRectangleMap.get(car).size());
        } else{

        }
        System.out.println("Rectangle clicked at: Column " + col + ", Row " + row);
        Car car = board.getCarAt(row, col);
        board.debugFunction();
    }


    private void dragDetected(MouseEvent event, int col, int row, Rectangle rect) {
        selectedRect = rect;
        System.out.println("tap at:" + row + ", " + col + ", " + rect);
        selectedCar = board.getCarAt(row, col);
        selectedRectangleList = carRectangleMap.get(selectedCar);
        Dragboard db = rect.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        content.putString("rectangle");
        db.setContent(content);
        event.consume();
    }


    //akzeptiert alle dragsrectangle
    private void handleDragOver(DragEvent event) {
        event.acceptTransferModes(TransferMode.MOVE);
        event.consume();
    }

    private void handleDragEntered(DragEvent event) {
        System.out.println(event.getGestureSource());
        System.out.println(event.getTarget());
        if (event.getGestureSource() != event.getTarget() &&
                event.getDragboard().hasString()) {
            Rectangle target = (Rectangle) event.getTarget();
            // Berechne die neuen Positionen für alle Rechtecke des Autos
            int newRow = GridPane.getRowIndex(target);
            int newCol = GridPane.getColumnIndex(target);


            //check if move is legit
            if(isMoveOnBoardLegit(newRow, newCol)){
                moveCarRectangles(carRectangleMap.get(selectedCar), newRow, newCol);
            }
        }
        event.consume();
    }
    private void registerEvents(Rectangle rect, int col, int row){
        rect.setOnMouseClicked(event -> handleRectangleClick(event, col, row));
        rect.setOnDragDetected(mouseEvent -> dragDetected(mouseEvent, col, row, rect));
        rect.setOnDragDone(event -> dragEnded(event, col, row) );
        rect.setOnDragOver(this::handleDragOver);
        rect.setOnDragEntered(this::handleDragEntered);
        rect.setOnDragDropped(event -> handleDragDropped(event, col, row));
        rect.setOnMouseDragged(event -> onMouseReleased(event, col, row));
    }

    private void onMouseReleased(MouseEvent event, int col, int row){
        event.consume();
    }
    private boolean isMoveOnBoardLegit(int newRow, int newCol) {
        // Überprüfe die Richtung des Fahrzeugs und die neuen Positionen
        if (!isDirectionCorrect(newRow, newCol)) {
            System.out.println("Falsche Richtung für die Bewegung");
            return false;
        }

        // Überprüfe, ob die neue Position innerhalb der Grenzen des Boards liegt
        if (!isWithinBoardBorders(newRow, newCol)) {
            System.out.println("Bewegung außerhalb des Boards");
            return false;
        }

        // Überprüfe, ob die neue Position von anderen Fahrzeugen blockiert ist
        if (isPositionBlocked(newRow, newCol)) {
            System.out.println("Position wird von einem anderen Fahrzeug blockiert");
            return false;
        }

        return true;
    }

    private boolean isDirectionCorrect(int newRow, int newCol) {
        Direction direction = selectedCar.getDirection();
        if (direction == Direction.HORIZONTAL) {
            return selectedCar.getYPosition() == newRow;
        } else if (direction == Direction.VERTICAL) {
            return selectedCar.getXPosition() == newCol;
        }
        return false;
    }

    private boolean isWithinBoardBorders(int newRow, int newCol) {
        for (int i = 0; i < selectedRectangleList.size(); i++) {
            Rectangle rect = selectedRectangleList.get(i);

            int rowOffset = (selectedCar.getDirection() == Direction.VERTICAL) ? i - selectedRectangleList.indexOf(selectedRect) : 0;
            int colOffset = (selectedCar.getDirection() == Direction.HORIZONTAL) ? i - selectedRectangleList.indexOf(selectedRect) : 0;
            int rowPosition = newRow + rowOffset;
            int colPosition = newCol + colOffset;

            if (rowPosition < 0 || rowPosition >= GRID_SIZE || colPosition < 0 || colPosition >= GRID_SIZE) {
                return false;
            }
        }
        return true;
    }

    private boolean isPositionBlocked(int newRow, int newCol) {
        for (int i = 0; i < selectedRectangleList.size(); i++) {
            Rectangle rect = selectedRectangleList.get(i);

            int rowOffset = (selectedCar.getDirection() == Direction.VERTICAL) ? i - selectedRectangleList.indexOf(selectedRect) : 0;
            int colOffset = (selectedCar.getDirection() == Direction.HORIZONTAL) ? i - selectedRectangleList.indexOf(selectedRect) : 0;
            int rowPosition = newRow + rowOffset;
            int colPosition = newCol + colOffset;

            if (board.getCarAt(rowPosition, colPosition) != null && board.getCarAt(rowPosition, colPosition) != selectedCar) {
                return true;
            }
        }
        return false;
    }
    private void moveCarRectangles(List<Rectangle> rectangles, int newRow, int newCol) {
        // Entfernerectangle die Rechtecke vom GridPane

        for (Rectangle rect : rectangles) {
            int oldRow = carGrid.getRowIndex(rect);
            int oldCol = carGrid.getColumnIndex(rect);
            Rectangle rectplace = new Rectangle(RECT_SIZE, RECT_SIZE);
            registerEvents(rectplace, oldCol, oldRow);

            // Event-Handler für den Mausklick hinzufügen
            rectplace.setFill(Color.WHITE);
            rectplace.setStroke(Color.BLACK);
            carGrid.getChildren().remove(rect);
            carGrid.add(rectplace, oldCol, oldRow);
        }


        // Füge die Rechtecke an den neuen Positionen hinzurectangle
        for (int i = 0; i < rectangles.size(); i++) {
            Rectangle rect = rectangles.get(i);

            int rowOffset = selectedCar.getDirection() == Direction.VERTICAL ? i - rectangles.indexOf(selectedRect) : 0;
            int colOffset = selectedCar.getDirection() == Direction.HORIZONTAL ? i - rectangles.indexOf(selectedRect) : 0;
            int rowPosition = newRow + rowOffset;
            int colPosition = newCol + colOffset;
            registerEvents(rect, colPosition, rowPosition);
            carGrid.add(rect, colPosition, rowPosition);
        }

    }

    private void handleDragDropped(DragEvent event, int col, int row) {
        if (event.getDragboard().hasString() && selectedRect != null) {
            // Finde die Zielposition des Drag-and-Drop-Vorgangs
            int targetRow = carGrid.getRowIndex(selectedRectangleList.getFirst());
            int targetCol = carGrid.getColumnIndex(selectedRectangleList.getFirst());
            System.out.println(targetCol + " " + targetRow);
            board.moveCar(selectedCar, targetCol, targetRow);
            System.out.println("dropped at " + targetCol + " and " + targetRow);
            event.setDropCompleted(true);
        } else {

            event.setDropCompleted(false);
        }
        event.consume();
    }



    private void dragEnded(DragEvent event, int col, int row) {
        if(event.getTransferMode() == TransferMode.MOVE){
            selectedRectangleList = null;
            selectedCar = null;
            selectedRect = null;
        }
        event.consume();
    }

    @Override
    public void update() {
        System.out.println("refresh");
    }
}
