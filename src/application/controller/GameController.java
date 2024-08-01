package application.controller;

import application.model.*;
import application.view.UserInterface;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.animation.KeyFrame;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameController implements CarObserver {

    private static final int GRID_SIZE = 6;  // Example size
    private static final int RECT_SIZE = 100; // Example size
    private static final int WINNING_ROW = 4;
    private static final int WINNING_COL = 2;
    private Board board;
    private BoardManager boardManager;
    private Car selectedCar;
    private Rectangle selectedRect;
    private Map<Car, List<Rectangle>> carRectangleMap = new HashMap<>();
    private List<Rectangle> selectedRectangleList = new ArrayList<>();
    private UserStatistic newStatistic;
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
        // Timeline erstellen --> Zöhlt die Sekunden hoch und setzt sie automatisch
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateTimer()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play(); // Timer starten

        board.subscribeToUpdates(this);
        newStatistic = UserStatistic.getInstance();
        refreshMovesLabel();
        populateGridPane();
    }

    private void updateTimer() {
        newStatistic.addSeconds();
        int minutes = newStatistic.getSeconds() / 60;
        int secs = newStatistic.getSeconds() % 60;
        timeC.setText(String.format("%02d:%02d", minutes, secs));
    }

    private void refreshMovesLabel(){
        movesC.setText(String.valueOf(newStatistic.getMoveCount()));
    }

    @FXML
    void onDragExited(DragEvent event) {
        int targetRow = carGrid.getRowIndex(selectedRectangleList.get(0));
        int targetCol = carGrid.getColumnIndex(selectedRectangleList.get(0));
        System.out.println("Board exited, saving pos");
        if(board.moveCar(selectedCar, targetCol, targetRow)){
            newStatistic.addMove(); //add to statistic
            checkWinningCondition(selectedCar);
            refreshMovesLabel();
        }
        event.consume();
    }



    private void populateGridPane() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                Rectangle rect = new Rectangle(RECT_SIZE, RECT_SIZE);
                rect.setFill(Color.WHITE);

                Car car = board.getCarAt(row, col);
                if (car != null) {
                    rect.setFill(car.getCarColor());
                    carRectangleMap.computeIfAbsent(car, k -> new ArrayList<>()).add(rect);
                }

                rect.setStroke(Color.BLACK);

                // Add event handler for mouse click
                registerEvents(rect, col, row);
                carGrid.add(rect, col, row);
            }
        }
    }

    private void handleRectangleClick(MouseEvent event, int col, int row) {
        if (selectedCar != null) {
            Car car = board.getCarAt(row, col);
            System.out.println(carRectangleMap.get(car).size());
        } else {
            // Handle the case when no car is selected
        }
        System.out.println("Rectangle clicked at: Column " + col + ", Row " + row);
        board.debugFunction();
    }

    private void dragDetected(MouseEvent event, int col, int row, Rectangle rect) {
        if (board.getCarAt(row, col) != null) {
            selectedRect = rect;
            selectedCar = board.getCarAt(row, col);
            selectedRectangleList = carRectangleMap.get(selectedCar);
            Dragboard db = rect.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString("");
            db.setContent(content);
            event.consume();
        }
    }

    // Accept all drag events
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
            // Calculate new positions for all rectangles of the car
            int newRow = GridPane.getRowIndex(target);
            int newCol = GridPane.getColumnIndex(target);

            // Check if move is legitimate
            if (isMoveOnBoardLegit(newRow, newCol)) {
                moveCarRectangles(carRectangleMap.get(selectedCar), newRow, newCol);
            }
        }
        event.consume();
    }

    private void registerEvents(Rectangle rect, int col, int row) {
        rect.setOnMouseClicked(event -> handleRectangleClick(event, col, row));
        rect.setOnDragDetected(mouseEvent -> dragDetected(mouseEvent, col, row, rect));
        rect.setOnDragDone(event -> dragEnded(event, col, row));
        rect.setOnDragOver(this::handleDragOver);
        rect.setOnDragEntered(this::handleDragEntered);
        rect.setOnDragDropped(event -> handleDragDropped(event, col, row));
    }

    private boolean isMoveOnBoardLegit(int newRow, int newCol) {
        // Check the direction of the vehicle and the new positions
        if (!isDirectionCorrect(newRow, newCol)) {
            System.out.println("Incorrect direction for movement");
            return false;
        }

        // Check if the new position is within the board's borders
        if (!isWithinBoardBorders(newRow, newCol)) {
            System.out.println("Movement out of board");
            return false;
        }

        // Check if the new position is blocked by other vehicles
        if (isPositionBlocked(newRow, newCol)) {
            System.out.println("Position is blocked by another vehicle");
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
        // Remove the rectangles from the GridPane
        for (Rectangle rect : rectangles) {
            int oldRow = carGrid.getRowIndex(rect);
            int oldCol = carGrid.getColumnIndex(rect);
            Rectangle rectPlace = new Rectangle(RECT_SIZE, RECT_SIZE);
            registerEvents(rectPlace, oldCol, oldRow);

            // Add event handler for mouse click
            rectPlace.setFill(Color.WHITE);
            rectPlace.setStroke(Color.BLACK);
            carGrid.getChildren().remove(rect);
            carGrid.add(rectPlace, oldCol, oldRow);
        }

        // Add the rectangles at the new positions
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
            // Find the target position of the drag-and-drop operation
            int targetRow = carGrid.getRowIndex(selectedRectangleList.get(0));
            int targetCol = carGrid.getColumnIndex(selectedRectangleList.get(0));
            System.out.println("Dropped at " + targetCol + " and " + targetRow);
            if(board.moveCar(selectedCar, targetCol, targetRow)){
                newStatistic.addMove(); //add to statistic
                checkWinningCondition(selectedCar);
                refreshMovesLabel();
            }
            System.out.println("Dropped at " + targetCol + " and " + targetRow);
            event.setDropCompleted(true);
        } else {
            event.setDropCompleted(false);
        }
        event.consume();
    }

    private void dragEnded(DragEvent event, int col, int row) {
        if (event.getTransferMode() == TransferMode.MOVE) {
            selectedRectangleList = null;
            selectedCar = null;
            selectedRect = null;
        }
        event.consume();
    }

    private void checkWinningCondition(Car car) {
        if (isRedCar(car)) {
            int carCol = car.getYPosition();
            int carRow = car.getXPosition();

            System.out.println("Checking winning condition for car at (" + carRow + ", " + carCol + ")");
            if (carRow == WINNING_ROW && carCol == WINNING_COL) {
                System.out.println("You won!");
                // Go to statistics menu
                getUserInterface().showMenu(MenuType.STATISTICS_MENU);
            }
        }
    }

    private boolean isRedCar(Car car) {
        boolean isRed = car != null && car.getCarColor().equals(Color.RED);
        if (isRed) {
            System.out.println("Red car detected.");
        }
        return isRed;
    }

    @Override
    public void update() {
        System.out.println("Refresh");
    }
}
