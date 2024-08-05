package application.controller;

import application.model.UserStatistic;
import application.model.Board;
import application.model.BoardManager;
import application.model.Direction;
import application.model.GameSettings;
import application.model.CarObserver;
import application.model.Difficulty;
import application.model.Car;
import application.view.GameView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.TransferMode;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* Hauptverantwortlicher: Alex Becker */
/* Mitwirkend: Matthias Henzel */
public class GameController implements CarObserver {

    private static final int GRID_SIZE = 6;
    private static final int RECT_SIZE = 100;
    private static final int WINNING_ROW = 4;
    private static final int WINNING_COL = 2;
    private final Map<Car, List<Rectangle>> carRectangleMap = new HashMap<>();
    private final BoardManager boardManager;
    private final UserStatistic statistic;
    private final Coordinator coordinator;
    private final GameView gameView;
    private Board board;
    private Car selectedCar;
    private Rectangle selectedRectangle;
    private List<Rectangle> selectedRectangleList = new ArrayList<>();
    private Timeline secondsCounter;

    public GameController(BoardManager boardManager, UserStatistic statistic, Coordinator coordinator, GameView gameView) {
        this.boardManager = boardManager;
        this.statistic = statistic;
        this.coordinator = coordinator;
        this.gameView = gameView;
    }

    public void postInit() {
        Difficulty selectedDifficulty = GameSettings.getInstance().getDifficulty(); // Retrieve difficulty from GameSettings
        board = boardManager.giveBoardToDifficulty(selectedDifficulty);
        board.makeReadyForUse();
        statistic.setSelectedBoard(board);

        secondsCounter = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateTimer()));
        secondsCounter.setCycleCount(Timeline.INDEFINITE);
        secondsCounter.play();

        board.subscribeToUpdates(this);
        refreshMoveCountLabel();
        populateGridPane();
    }

    private void updateTimer() {
        statistic.addSeconds();
        int minutes = statistic.getSeconds() / 60;
        int seconds = statistic.getSeconds() % 60;

        // Use GameView to update the timer label
        gameView.updateTimerLabel(minutes, seconds);
    }

    private void refreshMoveCountLabel() {
        int moveCount = statistic.getMoveCount();

        // Use GameView to update the move count label
        gameView.updateMoveCountLabel(moveCount);
    }

    public void handleBackToMenu() {
        if (secondsCounter != null) {
            secondsCounter.stop();
            statistic.setToDefault();
        }
        coordinator.showMainMenu();
    }

    public void handleDragExited(DragEvent event) {
        int targetRow = GridPane.getRowIndex(selectedRectangleList.getFirst());
        int targetCol = GridPane.getColumnIndex(selectedRectangleList.getFirst());
        if (board.moveCar(selectedCar, targetCol, targetRow)) {
            statistic.addMove();
            checkWinningCondition(selectedCar);
            refreshMoveCountLabel();
        }
        event.consume();
    }

    private void populateGridPane() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                Rectangle rectangle = new Rectangle(RECT_SIZE, RECT_SIZE);
                rectangle.setFill(Color.WHITE);

                Car car = board.getCarAt(row, col);
                if (car != null) {
                    rectangle.setFill(car.getCarColor());
                    carRectangleMap.computeIfAbsent(car, event -> new ArrayList<>()).add(rectangle);
                }

                rectangle.setStroke(Color.BLACK);
                registerEvents(rectangle, col, row);

                // Use the GameView method to add the rectangle to the grid
                gameView.addRectangleToGrid(rectangle, col, row);
            }
        }
    }

    private void handleRectangleClick(int col, int row) {
        coordinator.getInactivityNotifier().startTimer();
        if (selectedCar != null) {
            Car car = board.getCarAt(row, col);
            System.out.println(carRectangleMap.get(car).size());
        }
        System.out.println("Rectangle clicked at: Column " + col + ", Row " + row);
        board.debugFunction();
    }

    private void dragDetected(MouseEvent event, int col, int row, Rectangle rectangle) {
        coordinator.getInactivityNotifier().startTimer();
        if (board.getCarAt(row, col) != null) {
            selectedRectangle = rectangle;
            selectedCar = board.getCarAt(row, col);
            selectedRectangleList = carRectangleMap.get(selectedCar);
            Dragboard dragboard = rectangle.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString("");
            dragboard.setContent(content);
            event.consume();
        }
    }

    private void handleDragOver(DragEvent event) {
        event.acceptTransferModes(TransferMode.MOVE);
        event.consume();
    }

    private void handleDragEntered(DragEvent event) {
        if (event.getGestureSource() != event.getTarget() &&
                event.getDragboard().hasString()) {
            Rectangle target = (Rectangle) event.getTarget();
            int newRow = GridPane.getRowIndex(target);
            int newCol = GridPane.getColumnIndex(target);

            if (isMoveOnBoardLegit(newRow, newCol)) {
                moveCarRectangles(carRectangleMap.get(selectedCar), newRow, newCol);
            }
        }
        event.consume();
    }

    private void registerEvents(Rectangle rectangle, int col, int row) {
        rectangle.setOnMouseClicked(event -> handleRectangleClick(col, row));
        rectangle.setOnDragDetected(mouseEvent -> dragDetected(mouseEvent, col, row, rectangle));
        rectangle.setOnDragDone(this::dragEnded);
        rectangle.setOnDragOver(this::handleDragOver);
        rectangle.setOnDragEntered(this::handleDragEntered);
        rectangle.setOnDragDropped(this::handleDragDropped);
    }

    private boolean isMoveOnBoardLegit(int newRow, int newCol) {
        if (!isDirectionCorrect(newRow, newCol)) {
            return false;
        }

        if (!isWithinBoardBorders(newRow, newCol)) {
            return false;
        }

        if (isPositionBlocked(newRow, newCol)) {
            return false;
        }

        return !isPathBlocked(newRow, newCol);

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
            int[] position = calculatePosition(newRow, newCol, i);

            if (position[0] < 0 || position[0] >= GRID_SIZE || position[1] < 0 || position[1] >= GRID_SIZE) {
                return false;
            }
        }
        return true;
    }

    private boolean isPositionBlocked(int newRow, int newCol) {
        for (int i = 0; i < selectedRectangleList.size(); i++) {
            int[] position = calculatePosition(newRow, newCol, i);

            if (board.getCarAt(position[0], position[1]) != null && board.getCarAt(position[0], position[1]) != selectedCar) {
                return true;
            }
        }
        return false;
    }

    private boolean isPathBlocked(int newRow, int newCol) {
        int currentRow = GridPane.getRowIndex(selectedRectangle);
        int currentCol = GridPane.getColumnIndex(selectedRectangle);

        int selectedIndex = selectedRectangleList.indexOf(selectedRectangle);

        // Check for horizontal movement
        if (selectedCar.getDirection() == Direction.HORIZONTAL) {
            int moveDistance = newCol - currentCol;
            int maxDistance = calculateMaxDistance(currentRow, currentCol, selectedIndex, moveDistance, Direction.HORIZONTAL);

            // Check if the desired move is within the allowable range
            return Math.abs(moveDistance) > maxDistance;
        } else if (selectedCar.getDirection() == Direction.VERTICAL) {
            int moveDistance = newRow - currentRow;
            int maxDistance = calculateMaxDistance(currentRow, currentCol, selectedIndex, moveDistance, Direction.VERTICAL);

            // Check if the desired move is within the allowable range
            return Math.abs(moveDistance) > maxDistance;
        }

        return false;
    }

    private int calculateMaxDistance(int currentRow, int currentCol, int selectedIndex, int moveDistance, Direction direction) {
        int maxDistance = 0;
        int start, step, limit;
        boolean isHorizontal = (direction == Direction.HORIZONTAL);

        if (isHorizontal) {
            start = currentCol - selectedIndex;
            limit = currentRow; // Use currentRow as the fixed coordinate
        } else {
            start = currentRow - selectedIndex;
            limit = currentCol; // Use currentCol as the fixed coordinate
        }

        step = Integer.signum(moveDistance);

        if (step != 0) {
            for (int i = start + step; i >= 0 && i < GRID_SIZE; i += step) {
                if (isBlockedAtPosition(isHorizontal ? limit : i, isHorizontal ? i : limit)) {
                    break;
                }
                maxDistance++;
            }
        }

        return maxDistance;
    }


    private boolean isBlockedAtPosition(int row, int col) {
        Car carAtPosition = board.getCarAt(row, col);
        return carAtPosition != null && carAtPosition != selectedCar;
    }

    private int[] calculatePosition(int newRow, int newCol, int index) {
        int rowOffset = (selectedCar.getDirection() == Direction.VERTICAL) ? index - selectedRectangleList.indexOf(selectedRectangle) : 0;
        int colOffset = (selectedCar.getDirection() == Direction.HORIZONTAL) ? index - selectedRectangleList.indexOf(selectedRectangle) : 0;
        int rowPosition = newRow + rowOffset;
        int colPosition = newCol + colOffset;

        return new int[]{rowPosition, colPosition};
    }

    private void moveCarRectangles(List<Rectangle> rectangles, int newRow, int newCol) {
        for (Rectangle rectangle : rectangles) {
            int oldRow = GridPane.getRowIndex(rectangle);
            int oldCol = GridPane.getColumnIndex(rectangle);
            // Remove the rectangle from its current position
            gameView.getCarGrid().getChildren().remove(rectangle);

            // Create a placeholder rectangle if needed
            Rectangle rectPlace = new Rectangle(RECT_SIZE, RECT_SIZE);
            registerEvents(rectPlace, oldCol, oldRow);
            rectPlace.setFill(Color.WHITE);
            rectPlace.setStroke(Color.BLACK);

            // Add placeholder rectangle to the old position to keep the grid consistent
            gameView.addRectangleToGrid(rectPlace, oldCol, oldRow);
        }

        for (int i = 0; i < rectangles.size(); i++) {
            Rectangle rectangle = rectangles.get(i);

            // Calculate new positions
            int rowOffset = selectedCar.getDirection() == Direction.VERTICAL ? i - rectangles.indexOf(selectedRectangle) : 0;
            int colOffset = selectedCar.getDirection() == Direction.HORIZONTAL ? i - rectangles.indexOf(selectedRectangle) : 0;
            int rowPosition = newRow + rowOffset;
            int colPosition = newCol + colOffset;

            // Register events for the moved rectangle
            registerEvents(rectangle, colPosition, rowPosition);

            // Add the rectangle to the grid at its new position
            gameView.addRectangleToGrid(rectangle, colPosition, rowPosition);
        }
    }


    private void handleDragDropped(DragEvent event) {
        if (event.getDragboard().hasString() && selectedRectangle != null) {
            int targetRow = GridPane.getRowIndex(selectedRectangleList.getFirst());
            int targetCol = GridPane.getColumnIndex(selectedRectangleList.getFirst());
            if (board.moveCar(selectedCar, targetCol, targetRow)) {
                statistic.addMove();
                checkWinningCondition(selectedCar);
                refreshMoveCountLabel();
            }
            event.setDropCompleted(true);
        } else {
            event.setDropCompleted(false);
        }
        event.consume();
    }

    private void dragEnded(DragEvent event) {
        if (event.getTransferMode() == TransferMode.MOVE) {
            selectedRectangleList = null;
            selectedCar = null;
            selectedRectangle = null;
        }
        event.consume();
    }

    private void checkWinningCondition(Car car) {
        if (isRedCar(car)) {
            int carCol = car.getYPosition();
            int carRow = car.getXPosition();

            if (carRow == WINNING_ROW && carCol == WINNING_COL) {
                if (secondsCounter != null) {
                    secondsCounter.stop();
                }
                coordinator.showStatistics();
            }
        }
    }

    private boolean isRedCar(Car car) {
        return car != null && car.getCarColor().equals(Color.RED);
    }

    @Override
    public void update() {
        refreshMoveCountLabel();
    }

}
