        package application.controller;

        import application.model.Board;
        import application.model.Car;
        import application.model.BoardManager;
        import application.model.CarObserver;
        import application.model.UserStatistic;
        import application.model.MenuType;
        import application.model.Difficulty;
        import application.model.GameSettings;
        import application.model.Direction;
        import application.view.UserInterface;
        import javafx.animation.KeyFrame;
        import javafx.animation.Timeline;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.input.DragEvent;
        import javafx.scene.input.Dragboard;
        import javafx.scene.input.ClipboardContent;
        import javafx.scene.input.MouseEvent;
        import javafx.scene.input.TransferMode;
        import javafx.scene.layout.GridPane;
        import javafx.scene.paint.Color;
        import javafx.scene.shape.Rectangle;
        import javafx.util.Duration;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

        public class GameController implements CarObserver {

            private static final int GRID_SIZE = 6;
            private static final int RECT_SIZE = 100;
            private static final int WINNING_ROW = 4;
            private static final int WINNING_COL = 2;
            private final Map<Car, List<Rectangle>> carRectangleMap = new HashMap<>();
            private Board board;
            private BoardManager boardManager;
            private Car selectedCar;
            private Rectangle selectedRectangle;
            private List<Rectangle> selectedRectangleList = new ArrayList<>();
            private UserStatistic statistic;
            private Timeline secondsCounter;

            private UserInterface userInterface;
            @FXML
            private Button backToMenuButton;
            @FXML
            private Label moveCountLabel;
            @FXML
            private Label timeCountLabel;
            @FXML
            private GridPane carGrid;

            public BoardManager getBoardManager() {
                return boardManager;
            }

            public void setBoardManager(BoardManager boardManager) {
                this.boardManager = boardManager;
            }

            public void setUserInterface(UserInterface userInterface) {
                this.userInterface = userInterface;
            }

            @FXML
            void backToMenuButton(ActionEvent event) {
                secondsCounter.stop();
                userInterface.showMenu(MenuType.MAIN_MENU);
            }


            public void postInit() {
                //get board
                Difficulty selectedDifficulty = GameSettings.getInstance().getDifficulty();
                board = getBoardManager().giveBoardToDifficulty(selectedDifficulty);
                //make sure board is cleaned
                board.makeReadyForUse();
                //save to userstats for session saving
                UserStatistic.getInstance().setSelectedBoard(board);


                //create timeline for time counting
                secondsCounter = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateTimer()));
                secondsCounter.setCycleCount(Timeline.INDEFINITE);
                secondsCounter.play();

                board.subscribeToUpdates(this);
                statistic = UserStatistic.getInstance();
                refreshMoveCountLabel();
                populateGridPane();
            }

            private void updateTimer() {
                statistic.addSeconds();
                int minutes = statistic.getSeconds() / 60;
                int seconds = statistic.getSeconds() % 60;
                timeCountLabel.setText(String.format("%02d:%02d", minutes, seconds));
            }

            private void refreshMoveCountLabel() {
                moveCountLabel.setText(String.valueOf(statistic.getMoveCount()));
            }

            @FXML
            void onDragExited(DragEvent event) {
                int targetRow = GridPane.getRowIndex(selectedRectangleList.get(0));
                int targetCol = GridPane.getColumnIndex(selectedRectangleList.get(0));
                System.out.println("Board exited, saving position");
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
                            carRectangleMap.computeIfAbsent(car, k -> new ArrayList<>()).add(rectangle);
                        }

                        rectangle.setStroke(Color.BLACK);

                        registerEvents(rectangle, col, row);
                        carGrid.add(rectangle, col, row);
                    }
                }
            }

            private void handleRectangleClick(MouseEvent event, int col, int row) {
                if (selectedCar != null) {
                    Car car = board.getCarAt(row, col);
                    System.out.println(carRectangleMap.get(car).size());
                }
                System.out.println("Rectangle clicked at: Column " + col + ", Row " + row);
                board.debugFunction();
            }

            private void dragDetected(MouseEvent event, int col, int row, Rectangle rectangle) {
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
                System.out.println(event.getGestureSource());
                System.out.println(event.getTarget());
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
                rectangle.setOnMouseClicked(event -> handleRectangleClick(event, col, row));
                rectangle.setOnDragDetected(mouseEvent -> dragDetected(mouseEvent, col, row, rectangle));
                rectangle.setOnDragDone(event -> dragEnded(event, col, row));
                rectangle.setOnDragOver(this::handleDragOver);
                rectangle.setOnDragEntered(this::handleDragEntered);
                rectangle.setOnDragDropped(event -> handleDragDropped(event, col, row));
            }

            private boolean isMoveOnBoardLegit(int newRow, int newCol) {
                if (!isDirectionCorrect(newRow, newCol)) {
                    System.out.println("Incorrect direction for movement");
                    return false;
                }

                if (!isWithinBoardBorders(newRow, newCol)) {
                    System.out.println("Movement out of board");
                    return false;
                }

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
                    Rectangle rectPlace = new Rectangle(RECT_SIZE, RECT_SIZE);
                    registerEvents(rectPlace, oldCol, oldRow);

                    rectPlace.setFill(Color.WHITE);
                    rectPlace.setStroke(Color.BLACK);
                    carGrid.getChildren().remove(rectangle);
                    carGrid.add(rectPlace, oldCol, oldRow);
                }

                for (int i = 0; i < rectangles.size(); i++) {
                    Rectangle rectangle = rectangles.get(i);

                    int rowOffset = selectedCar.getDirection() == Direction.VERTICAL ? i - rectangles.indexOf(selectedRectangle) : 0;
                    int colOffset = selectedCar.getDirection() == Direction.HORIZONTAL ? i - rectangles.indexOf(selectedRectangle) : 0;
                    int rowPosition = newRow + rowOffset;
                    int colPosition = newCol + colOffset;
                    registerEvents(rectangle, colPosition, rowPosition);
                    carGrid.add(rectangle, colPosition, rowPosition);
                }
            }

            private void handleDragDropped(DragEvent event, int col, int row) {
                if (event.getDragboard().hasString() && selectedRectangle != null) {
                    int targetRow = GridPane.getRowIndex(selectedRectangleList.get(0));
                    int targetCol = GridPane.getColumnIndex(selectedRectangleList.get(0));
                    System.out.println("Dropped at " + targetCol + " and " + targetRow);
                    if (board.moveCar(selectedCar, targetCol, targetRow)) {
                        statistic.addMove();
                        checkWinningCondition(selectedCar);
                        refreshMoveCountLabel();
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
                    selectedRectangle = null;
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
                        secondsCounter.stop();
                        userInterface.showMenu(MenuType.STATISTICS_MENU);
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
