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

/**
 * Die Klasse GameController steuert die Logik und Interaktion innerhalb des Spiels.
 * Sie ist verantwortlich für die Verwaltung des Spielbretts, die Aktualisierung der
 * Spielstatistiken und die Handhabung von Drag-and-Drop-Ereignissen zum Verschieben von Autos.
 */
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

    /**
     * Konstruktor für die Klasse GameController. Initialisiert den Controller
     * mit den erforderlichen Managern und Ansichten.
     *
     * @param boardManager Der BoardManager, der das Spielbrett verwaltet.
     * @param statistic Die Benutzerstatistiken.
     * @param coordinator Der Koordinator, der für die Steuerung der Anwendungsflüsse verantwortlich ist.
     * @param gameView Die Ansicht, die das Spiel darstellt.
     */
    public GameController(BoardManager boardManager, UserStatistic statistic, Coordinator coordinator, GameView gameView) {
        this.boardManager = boardManager;
        this.statistic = statistic;
        this.coordinator = coordinator;
        this.gameView = gameView;
    }

    /**
     * Initialisiert das Spiel nach der Erstellung des Controllers.
     * Lädt das Spielbrett basierend auf dem ausgewählten Schwierigkeitsgrad und
     * startet den Timer.
     */
    public void postInit() {
        Difficulty selectedDifficulty = GameSettings.getInstance().getDifficulty(); // Ruft den Schwierigkeitsgrad aus den Spiel-Einstellungen ab.
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

    /**
     * Aktualisiert den Timer und zeigt die vergangene Zeit im Spiel an.
     */
    private void updateTimer() {
        statistic.addSeconds();
        int minutes = statistic.getSeconds() / 60;
        int seconds = statistic.getSeconds() % 60;

        // Verwende GameView, um das Timer-Label zu aktualisieren
        gameView.updateTimerLabel(minutes, seconds);
    }

    /**
     * Aktualisiert das Label, das die Anzahl der Züge im Spiel anzeigt.
     */
    private void refreshMoveCountLabel() {
        int moveCount = statistic.getMoveCount();

        // Verwende GameView, um das Zugzähl-Label zu aktualisieren
        gameView.updateMoveCountLabel(moveCount);
    }

    /**
     * Handhabt die Rückkehr zum Hauptmenü und stoppt den Timer.
     */
    public void handleBackToMenu() {
        if (secondsCounter != null) {
            secondsCounter.stop();
            statistic.setToDefault();
        }
        coordinator.showMainMenu();
    }

    /**
     * Wird ausgelöst, wenn ein Drag-Ereignis das Ziel verlässt. Bewegt das ausgewählte Auto auf dem Spielbrett.
     *
     * @param event Das Drag-Ereignis, das das Ziel verlässt.
     */
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

    /**
     * Füllt das GridPane mit Rechtecken, die das Spielbrett darstellen,
     * und registriert die entsprechenden Ereignisse für jedes Rechteck.
     */
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

                // Verwende die GameView-Methode, um das Rechteck dem Raster hinzuzufügen
                gameView.addRectangleToGrid(rectangle, col, row);
            }
        }
    }

    /**
     * Handhabt das Klicken auf ein Rechteck im Spielbrett.
     *
     * @param col Die Spalte des geklickten Rechtecks.
     * @param row Die Zeile des geklickten Rechtecks.
     */
    private void handleRectangleClick(int col, int row) {
        coordinator.getInactivityNotifier().startTimer();
        if (selectedCar != null) {
            Car car = board.getCarAt(row, col);
            System.out.println(carRectangleMap.get(car).size());
        }
        System.out.println("Rechteck angeklickt bei: Spalte " + col + ", Zeile " + row);
        board.debugFunction();
    }

    /**
     * Wird ausgelöst, wenn ein Drag-Ereignis erkannt wird.
     * Initialisiert den Drag-and-Drop-Vorgang für ein Auto.
     *
     * @param event Das Mausereignis, das den Drag-and-Drop-Vorgang auslöst.
     * @param col Die Spalte des Rechtecks, das gezogen wird.
     * @param row Die Zeile des Rechtecks, das gezogen wird.
     * @param rectangle Das Rechteck, das gezogen wird.
     */
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

    /**
     * Handhabt das DragOver-Ereignis, um zu bestimmen, ob ein Element über
     * ein Ziel gezogen werden kann.
     *
     * @param event Das DragOver-Ereignis.
     */
    private void handleDragOver(DragEvent event) {
        event.acceptTransferModes(TransferMode.MOVE);
        event.consume();
    }

    /**
     * Wird ausgelöst, wenn ein Drag-and-Drop-Ereignis ein Ziel betritt.
     * Bewegt das Auto zu der neuen Position, wenn der Zug gültig ist.
     *
     * @param event Das DragEntered-Ereignis.
     */
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

    /**
     * Registriert die Ereignisse für ein Rechteck im GridPane.
     *
     * @param rectangle Das Rechteck, für das Ereignisse registriert werden sollen.
     * @param col Die Spalte des Rechtecks im Raster.
     * @param row Die Zeile des Rechtecks im Raster.
     */
    private void registerEvents(Rectangle rectangle, int col, int row) {
        rectangle.setOnMouseClicked(event -> handleRectangleClick(col, row));
        rectangle.setOnDragDetected(mouseEvent -> dragDetected(mouseEvent, col, row, rectangle));
        rectangle.setOnDragDone(this::dragEnded);
        rectangle.setOnDragOver(this::handleDragOver);
        rectangle.setOnDragEntered(this::handleDragEntered);
        rectangle.setOnDragDropped(this::handleDragDropped);
    }

    /**
     * Überprüft, ob der Zug auf dem Spielfeld gültig ist.
     *
     * @param newRow Die neue Zeile, in die das Auto bewegt werden soll.
     * @param newCol Die neue Spalte, in die das Auto bewegt werden soll.
     * @return true, wenn der Zug gültig ist; andernfalls false.
     */
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

    /**
     * Überprüft, ob die Richtung des Zuges korrekt ist.
     *
     * @param newRow Die neue Zeile, in die das Auto bewegt werden soll.
     * @param newCol Die neue Spalte, in die das Auto bewegt werden soll.
     * @return true, wenn die Richtung korrekt ist; andernfalls false.
     */
    private boolean isDirectionCorrect(int newRow, int newCol) {
        Direction direction = selectedCar.getDirection();
        if (direction == Direction.HORIZONTAL) {
            return selectedCar.getYPosition() == newRow;
        } else if (direction == Direction.VERTICAL) {
            return selectedCar.getXPosition() == newCol;
        }
        return false;
    }

    /**
     * Überprüft, ob sich das Auto innerhalb der Grenzen des Spielfeldes befindet.
     *
     * @param newRow Die neue Zeile, in die das Auto bewegt werden soll.
     * @param newCol Die neue Spalte, in die das Auto bewegt werden soll.
     * @return true, wenn sich das Auto innerhalb der Grenzen befindet; andernfalls false.
     */
    private boolean isWithinBoardBorders(int newRow, int newCol) {
        for (int i = 0; i < selectedRectangleList.size(); i++) {
            int[] position = calculatePosition(newRow, newCol, i);

            if (position[0] < 0 || position[0] >= GRID_SIZE || position[1] < 0 || position[1] >= GRID_SIZE) {
                return false;
            }
        }
        return true;
    }

    /**
     * Überprüft, ob die neue Position des Autos blockiert ist.
     *
     * @param newRow Die neue Zeile, in die das Auto bewegt werden soll.
     * @param newCol Die neue Spalte, in die das Auto bewegt werden soll.
     * @return true, wenn die Position blockiert ist; andernfalls false.
     */
    private boolean isPositionBlocked(int newRow, int newCol) {
        for (int i = 0; i < selectedRectangleList.size(); i++) {
            int[] position = calculatePosition(newRow, newCol, i);

            if (board.getCarAt(position[0], position[1]) != null && board.getCarAt(position[0], position[1]) != selectedCar) {
                return true;
            }
        }
        return false;
    }

    /**
     * Überprüft, ob der Pfad des Autos blockiert ist.
     *
     * @param newRow Die neue Zeile, in die das Auto bewegt werden soll.
     * @param newCol Die neue Spalte, in die das Auto bewegt werden soll.
     * @return true, wenn der Pfad blockiert ist; andernfalls false.
     */
    private boolean isPathBlocked(int newRow, int newCol) {
        int currentRow = GridPane.getRowIndex(selectedRectangle);
        int currentCol = GridPane.getColumnIndex(selectedRectangle);

        int selectedIndex = selectedRectangleList.indexOf(selectedRectangle);

        // Prüfen auf horizontale Bewegung
        if (selectedCar.getDirection() == Direction.HORIZONTAL) {
            int moveDistance = newCol - currentCol;
            int maxDistance = calculateMaxDistance(currentRow, currentCol, selectedIndex, moveDistance, Direction.HORIZONTAL);

            // Überprüfen, ob der gewünschte Zug innerhalb des erlaubten Bereichs liegt
            return Math.abs(moveDistance) > maxDistance;
        } else if (selectedCar.getDirection() == Direction.VERTICAL) {
            int moveDistance = newRow - currentRow;
            int maxDistance = calculateMaxDistance(currentRow, currentCol, selectedIndex, moveDistance, Direction.VERTICAL);

            // Überprüfen, ob der gewünschte Zug innerhalb des erlaubten Bereichs liegt
            return Math.abs(moveDistance) > maxDistance;
        }

        return false;
    }

    /**
     * Berechnet die maximale Entfernung, die ein Auto in eine bestimmte Richtung bewegt werden kann.
     *
     * @param currentRow Die aktuelle Zeile des Autos.
     * @param currentCol Die aktuelle Spalte des Autos.
     * @param selectedIndex Der Index des ausgewählten Rechtecks in der Liste der Rechtecke.
     * @param moveDistance Die Entfernung, um die das Auto bewegt werden soll.
     * @param direction Die Richtung, in die das Auto bewegt werden soll.
     * @return Die maximale Entfernung, die das Auto in die gewünschte Richtung bewegt werden kann.
     */
    private int calculateMaxDistance(int currentRow, int currentCol, int selectedIndex, int moveDistance, Direction direction) {
        int maxDistance = 0;
        int start, step, limit;
        boolean isHorizontal = (direction == Direction.HORIZONTAL);

        if (isHorizontal) {
            start = currentCol - selectedIndex;
            limit = currentRow; // Verwende currentRow als feste Koordinate
        } else {
            start = currentRow - selectedIndex;
            limit = currentCol; // Verwende currentCol als feste Koordinate
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

    /**
     * Überprüft, ob eine bestimmte Position auf dem Spielfeld blockiert ist.
     *
     * @param row Die Zeile der zu überprüfenden Position.
     * @param col Die Spalte der zu überprüfenden Position.
     * @return true, wenn die Position blockiert ist; andernfalls false.
     */
    private boolean isBlockedAtPosition(int row, int col) {
        Car carAtPosition = board.getCarAt(row, col);
        return carAtPosition != null && carAtPosition != selectedCar;
    }

    /**
     * Berechnet die neue Position eines Rechtecks auf dem Spielfeld.
     *
     * @param newRow Die neue Zeile des Rechtecks.
     * @param newCol Die neue Spalte des Rechtecks.
     * @param index Der Index des Rechtecks in der Liste der Rechtecke.
     * @return Ein Array mit der neuen Zeilen- und Spaltenposition.
     */
    private int[] calculatePosition(int newRow, int newCol, int index) {
        int rowOffset = (selectedCar.getDirection() == Direction.VERTICAL) ? index - selectedRectangleList.indexOf(selectedRectangle) : 0;
        int colOffset = (selectedCar.getDirection() == Direction.HORIZONTAL) ? index - selectedRectangleList.indexOf(selectedRectangle) : 0;
        int rowPosition = newRow + rowOffset;
        int colPosition = newCol + colOffset;

        return new int[]{rowPosition, colPosition};
    }

    /**
     * Bewegt die Rechtecke eines Autos zu einer neuen Position auf dem Spielfeld.
     *
     * @param rectangles Die Liste der Rechtecke, die das Auto darstellen.
     * @param newRow Die neue Zeile, in die das Auto bewegt werden soll.
     * @param newCol Die neue Spalte, in die das Auto bewegt werden soll.
     */
    private void moveCarRectangles(List<Rectangle> rectangles, int newRow, int newCol) {
        for (Rectangle rectangle : rectangles) {
            int oldRow = GridPane.getRowIndex(rectangle);
            int oldCol = GridPane.getColumnIndex(rectangle);
            // Entferne das Rechteck von seiner aktuellen Position
            gameView.getCarGrid().getChildren().remove(rectangle);

            // Erstelle bei Bedarf ein Platzhalter-Rechteck
            Rectangle rectPlace = new Rectangle(RECT_SIZE, RECT_SIZE);
            registerEvents(rectPlace, oldCol, oldRow);
            rectPlace.setFill(Color.WHITE);
            rectPlace.setStroke(Color.BLACK);

            // Füge das Platzhalter-Rechteck an der alten Position hinzu, um das Raster konsistent zu halten
            gameView.addRectangleToGrid(rectPlace, oldCol, oldRow);
        }

        for (int i = 0; i < rectangles.size(); i++) {
            Rectangle rectangle = rectangles.get(i);

            // Berechne neue Positionen
            int rowOffset = selectedCar.getDirection() == Direction.VERTICAL ? i - rectangles.indexOf(selectedRectangle) : 0;
            int colOffset = selectedCar.getDirection() == Direction.HORIZONTAL ? i - rectangles.indexOf(selectedRectangle) : 0;
            int rowPosition = newRow + rowOffset;
            int colPosition = newCol + colOffset;

            // Registriere Ereignisse für das verschobene Rechteck
            registerEvents(rectangle, colPosition, rowPosition);

            // Füge das Rechteck im Raster an seiner neuen Position hinzu
            gameView.addRectangleToGrid(rectangle, colPosition, rowPosition);
        }
    }

    /**
     * Handhabt das Ereignis, wenn ein Element erfolgreich an einem Ziel abgelegt wird.
     * Überprüft, ob das Auto erfolgreich bewegt wurde, und aktualisiert die Spielstatistiken.
     *
     * @param event Das DragDropped-Ereignis.
     */
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

    /**
     * Wird ausgelöst, wenn ein Drag-and-Drop-Vorgang abgeschlossen ist.
     * Setzt die Auswahl der Rechtecke und Autos zurück.
     *
     * @param event Das DragEnded-Ereignis.
     */
    private void dragEnded(DragEvent event) {
        if (event.getTransferMode() == TransferMode.MOVE) {
            selectedRectangleList = null;
            selectedCar = null;
            selectedRectangle = null;
        }
        event.consume();
    }

    /**
     * Überprüft die Gewinnbedingung für ein Auto.
     * Zeigt die Statistikansicht an, wenn das rote Auto die Gewinnposition erreicht.
     *
     * @param car Das zu überprüfende Auto.
     */
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

    /**
     * Überprüft, ob ein Auto rot ist.
     *
     * @param car Das zu überprüfende Auto.
     * @return true, wenn das Auto rot ist; andernfalls false.
     */
    private boolean isRedCar(Car car) {
        return car != null && car.getCarColor().equals(Color.RED);
    }

    /**
     * Aktualisiert die Ansicht, wenn sich die Anzahl der Züge ändert.
     */
    @Override
    public void update() {
        refreshMoveCountLabel();
    }

}
