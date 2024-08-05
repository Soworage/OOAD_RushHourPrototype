package application.model;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* Hauptverantwortlicher: Erik Witte */
/* Mitwirkend: Alex Becker */

public class HardBoardCreator implements BoardCreatorInterface {
    private final List<Board> boards;
    private final Map<Integer, HighscoreTable> highscoreTables;

    public HardBoardCreator() {
        boards = new ArrayList<>();
        highscoreTables = new HashMap<>();
        populateBoards();
    }

    @Override
    public Board getBoard() {
        // impelemtend random number generator for finished product
        return boards.getFirst().clone();
    }

    @Override
    public HighscoreTable getHighscoreTable(int boardId) {
        return highscoreTables.get(boardId);
    }

    private void populateBoards() {
        List<Car> cars = List.of(
                new Car.Builder()
                        .setXPosition(0)
                        .setYPosition(0)
                        .setDirection(Direction.VERTICAL)
                        .setLength(2)
                        .setCarColor(javafx.scene.paint.Color.OLIVE)
                        .build(),
                new Car.Builder()
                        .setXPosition(1)
                        .setYPosition(0)
                        .setDirection(Direction.HORIZONTAL)
                        .setLength(3)
                        .setCarColor(javafx.scene.paint.Color.YELLOW)
                        .build(),
                new Car.Builder()
                        .setXPosition(4)
                        .setYPosition(0)
                        .setDirection(Direction.VERTICAL)
                        .setLength(2)
                        .setCarColor(javafx.scene.paint.Color.ORANGE)
                        .build(),
                new Car.Builder()
                        .setXPosition(5)
                        .setYPosition(0)
                        .setDirection(Direction.VERTICAL)
                        .setLength(2)
                        .setCarColor(javafx.scene.paint.Color.LIGHTBLUE)
                        .build(),
                new Car.Builder()
                        .setXPosition(0)
                        .setYPosition(2)
                        .setDirection(Direction.HORIZONTAL)
                        .setLength(2)
                        .setCarColor(javafx.scene.paint.Color.RED)
                        .build(),
                new Car.Builder()
                        .setXPosition(2)
                        .setYPosition(1)
                        .setDirection(Direction.VERTICAL)
                        .setLength(2)
                        .setCarColor(javafx.scene.paint.Color.PINK)
                        .build(),
                new Car.Builder()
                        .setXPosition(3)
                        .setYPosition(2)
                        .setDirection(Direction.VERTICAL)
                        .setLength(3)
                        .setCarColor(javafx.scene.paint.Color.PURPLE)
                        .build(),
                new Car.Builder()
                        .setXPosition(2)
                        .setYPosition(3)
                        .setDirection(Direction.VERTICAL)
                        .setLength(2)
                        .setCarColor(javafx.scene.paint.Color.DARKBLUE)
                        .build(),
                new Car.Builder()
                        .setXPosition(4)
                        .setYPosition(3)
                        .setDirection(Direction.HORIZONTAL)
                        .setLength(2)
                        .setCarColor(javafx.scene.paint.Color.DARKGREEN)
                        .build(),
                new Car.Builder()
                        .setXPosition(0)
                        .setYPosition(4)
                        .setDirection(Direction.HORIZONTAL)
                        .setLength(2)
                        .setCarColor(javafx.scene.paint.Color.BLACK)
                        .build(),
                new Car.Builder()
                        .setXPosition(3)
                        .setYPosition(5)
                        .setDirection(Direction.HORIZONTAL)
                        .setLength(2)
                        .setCarColor(Color.DARKCYAN)
                        .build()
        );
        HighscoreTable highscoreTable = new HighscoreTable();
        highscoreTables.put(boards.size(), highscoreTable);
        Board board = new Board(boards.size(), 6, 6, cars);
        boards.add(board);
    }
}
