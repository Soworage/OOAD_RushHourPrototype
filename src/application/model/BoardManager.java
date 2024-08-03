package application.model;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardManager {

    private EasyBoardCreator easyBoardCreator;
    private final List<Board> mediumBoards = new ArrayList<>();
    private final List<Board> hardBoards = new ArrayList<>();

    private final Map<Integer, HighscoreTable> highScoreTablesForMediumBoards = new HashMap<>();
    private final Map<Integer, HighscoreTable> highScoreTablesForHardBoards = new HashMap<>();

    public BoardManager() {
        easyBoardCreator = new EasyBoardCreator();
        createMediumBoard();
        createHardBoard();
    }


    public HighscoreTable getHighScoreTableForBoard(int boardID, Difficulty difficulty) {
        System.out.println("Got a request for Board id " + boardID + "and diff " + difficulty);
        return switch (difficulty) {
            case EASY -> easyBoardCreator.getHighscoreTable(boardID);
            case MEDIUM -> highScoreTablesForMediumBoards.get(boardID);
            case HARD -> highScoreTablesForHardBoards.get(boardID);
        };
    }

    private void createMediumBoard() {
        List<Car> cars = List.of(
                new Car.Builder()
                        .setXPosition(1)
                        .setYPosition(0)
                        .setDirection(Direction.VERTICAL)
                        .setLength(2)
                        .setCarColor(Color.OLIVE)
                        .build(),
                new Car.Builder()
                        .setXPosition(2)
                        .setYPosition(0)
                        .setDirection(Direction.VERTICAL)
                        .setLength(2)
                        .setCarColor(Color.ORANGE)
                        .build(),
                new Car.Builder()
                        .setXPosition(3)
                        .setYPosition(0)
                        .setDirection(Direction.HORIZONTAL)
                        .setLength(2)
                        .setCarColor(Color.LIGHTBLUE)
                        .build(),
                new Car.Builder()
                        .setXPosition(3)
                        .setYPosition(1)
                        .setDirection(Direction.HORIZONTAL)
                        .setLength(2)
                        .setCarColor(Color.PINK)
                        .build(),
                new Car.Builder()
                        .setXPosition(0)
                        .setYPosition(2)
                        .setDirection(Direction.HORIZONTAL)
                        .setLength(2)
                        .setCarColor(Color.RED)
                        .build(),
                new Car.Builder()
                        .setXPosition(2)
                        .setYPosition(2)
                        .setDirection(Direction.VERTICAL)
                        .setLength(2)
                        .setCarColor(Color.PURPLE)
                        .build(),
                new Car.Builder()
                        .setXPosition(5)
                        .setYPosition(3)
                        .setDirection(Direction.VERTICAL)
                        .setLength(2)
                        .setCarColor(Color.GREEN)
                        .build(),
                new Car.Builder()
                        .setXPosition(0)
                        .setYPosition(4)
                        .setDirection(Direction.HORIZONTAL)
                        .setLength(3)
                        .setCarColor(Color.YELLOW)
                        .build(),
                new Car.Builder()
                        .setXPosition(3)
                        .setYPosition(4)
                        .setDirection(Direction.VERTICAL)
                        .setLength(2)
                        .setCarColor(Color.DARKBLUE)
                        .build(),
                new Car.Builder()
                        .setXPosition(4)
                        .setYPosition(4)
                        .setDirection(Direction.VERTICAL)
                        .setLength(2)
                        .setCarColor(Color.BROWN)
                        .build(),
                new Car.Builder()
                        .setXPosition(1)
                        .setYPosition(5)
                        .setDirection(Direction.HORIZONTAL)
                        .setLength(2)
                        .setCarColor(Color.LIGHTYELLOW)
                        .build()
        );
        HighscoreTable highscoreTable = new HighscoreTable();
        highScoreTablesForMediumBoards.put(mediumBoards.size(), highscoreTable);
        Board board = new Board(mediumBoards.size(), 6, 6, cars);
        mediumBoards.add(board);
    }

    private void createHardBoard() {
        List<Car> cars = List.of(
                new Car.Builder()
                        .setXPosition(0)
                        .setYPosition(0)
                        .setDirection(Direction.VERTICAL)
                        .setLength(2)
                        .setCarColor(Color.OLIVE)
                        .build(),
                new Car.Builder()
                        .setXPosition(1)
                        .setYPosition(0)
                        .setDirection(Direction.HORIZONTAL)
                        .setLength(3)
                        .setCarColor(Color.YELLOW)
                        .build(),
                new Car.Builder()
                        .setXPosition(4)
                        .setYPosition(0)
                        .setDirection(Direction.VERTICAL)
                        .setLength(2)
                        .setCarColor(Color.ORANGE)
                        .build(),
                new Car.Builder()
                        .setXPosition(5)
                        .setYPosition(0)
                        .setDirection(Direction.VERTICAL)
                        .setLength(2)
                        .setCarColor(Color.LIGHTBLUE)
                        .build(),
                new Car.Builder()
                        .setXPosition(0)
                        .setYPosition(2)
                        .setDirection(Direction.HORIZONTAL)
                        .setLength(2)
                        .setCarColor(Color.RED)
                        .build(),
                new Car.Builder()
                        .setXPosition(2)
                        .setYPosition(1)
                        .setDirection(Direction.VERTICAL)
                        .setLength(2)
                        .setCarColor(Color.PINK)
                        .build(),
                new Car.Builder()
                        .setXPosition(3)
                        .setYPosition(2)
                        .setDirection(Direction.VERTICAL)
                        .setLength(3)
                        .setCarColor(Color.PURPLE)
                        .build(),
                new Car.Builder()
                        .setXPosition(2)
                        .setYPosition(3)
                        .setDirection(Direction.VERTICAL)
                        .setLength(2)
                        .setCarColor(Color.DARKBLUE)
                        .build(),
                new Car.Builder()
                        .setXPosition(4)
                        .setYPosition(3)
                        .setDirection(Direction.HORIZONTAL)
                        .setLength(2)
                        .setCarColor(Color.DARKGREEN)
                        .build(),
                new Car.Builder()
                        .setXPosition(0)
                        .setYPosition(4)
                        .setDirection(Direction.HORIZONTAL)
                        .setLength(2)
                        .setCarColor(Color.BLACK)
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
        highScoreTablesForHardBoards.put(hardBoards.size(), highscoreTable);
        Board board = new Board(hardBoards.size(), 6, 6, cars);
        hardBoards.add(board);
    }

    public Board giveBoardToDifficulty(Difficulty difficulty) {
        switch (difficulty) {
            case EASY -> {
                return easyBoardCreator.getBoard();
            }
            case MEDIUM -> {
                return mediumBoards.getFirst().clone();
            }
            case HARD -> {
                return hardBoards.getFirst().clone();
            }
        }
        return null;
    }
}
