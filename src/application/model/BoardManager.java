package application.model;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardManager {

    private final List<Board> easyBoards = new ArrayList<>();
    private final List<Board> mediumBoards = new ArrayList<>();
    private final List<Board> hardBoards = new ArrayList<>();

    private final Map<Integer, HighscoreTable> highScoreTablesForEasyBoards = new HashMap<>();
    private final Map<Integer, HighscoreTable> highScoreTablesForMediumBoards = new HashMap<>();
    private final Map<Integer, HighscoreTable> highScoreTablesForHardBoards = new HashMap<>();

    public BoardManager() {
        createEasyBoard();
        createMediumBoard();
        createHardBoard();
    }

    public List<Board> getEasyBoards() {
        return easyBoards;
    }

    public List<Board> getMediumBoards() {
        return mediumBoards;
    }

    public List<Board> getHardBoards() {
        return hardBoards;
    }


    public HighscoreTable getHighScoreTableForBoard(int boardID, Difficulty difficulty) {
        System.out.println("Got a request for Board id "  + boardID + "and diff " + difficulty);
        switch(difficulty) {
            case EASY:
                return highScoreTablesForEasyBoards.get(boardID);
            case MEDIUM:
                return highScoreTablesForMediumBoards.get(boardID);
            case HARD:
                return highScoreTablesForHardBoards.get(boardID);
        }
        return null;
    }

    private void createEasyBoard() {
        List<Car> cars = List.of(
                new Car(0, 0, Direction.VERTICAL, 3, Color.YELLOW),
                new Car(3, 0, Direction.VERTICAL, 2, Color.OLIVE),
                new Car(4, 0, Direction.HORIZONTAL, 2, Color.ORANGE),
                new Car(4, 1, Direction.HORIZONTAL, 2, Color.BLUE),
                new Car(3, 2, Direction.HORIZONTAL, 2, Color.RED),
                new Car(5, 2, Direction.VERTICAL, 2, Color.PINK),
                new Car(0, 3, Direction.VERTICAL, 2, Color.BLACK),
                new Car(1, 3, Direction.HORIZONTAL, 3, Color.MAGENTA),
                new Car(1, 4, Direction.HORIZONTAL, 3, Color.BLUE),
                new Car(4, 4, Direction.HORIZONTAL, 2, Color.GREEN),
                new Car(0, 5, Direction.HORIZONTAL, 3, Color.LIGHTGREEN)
        );

        //create highScoreTable for Board
        HighscoreTable highscoreTable = new HighscoreTable();
        highScoreTablesForEasyBoards.put(easyBoards.size(), highscoreTable);
        Board board = new Board(easyBoards.size(),6, 6, cars);
        easyBoards.add(board);

    }

    private void createMediumBoard() {
        List<Car> cars = List.of(
                new Car(1, 0, Direction.VERTICAL, 2, Color.OLIVE),
                new Car(2, 0, Direction.VERTICAL, 2, Color.ORANGE),
                new Car(3, 0, Direction.HORIZONTAL, 2, Color.LIGHTBLUE),
                new Car(3, 1, Direction.HORIZONTAL, 2, Color.PINK),
                new Car(0, 2, Direction.HORIZONTAL, 2, Color.RED),
                new Car(2, 2, Direction.VERTICAL, 2, Color.PURPLE),
                new Car(5, 3, Direction.VERTICAL, 2, Color.GREEN),
                new Car(0, 4, Direction.HORIZONTAL, 3, Color.YELLOW),
                new Car(3, 4, Direction.VERTICAL, 2, Color.DARKBLUE),
                new Car(4, 4, Direction.VERTICAL, 2, Color.BROWN),
                new Car(1, 5, Direction.HORIZONTAL, 2, Color.LIGHTYELLOW)
        );
        HighscoreTable highscoreTable = new HighscoreTable();
        highScoreTablesForMediumBoards.put(mediumBoards.size(), highscoreTable);
        Board board = new Board(mediumBoards.size(),6, 6, cars);
        mediumBoards.add(board);
    }

    private void createHardBoard() {
        List<Car> cars = List.of(
                new Car(0, 0, Direction.VERTICAL, 2, Color.OLIVE),
                new Car(1, 0, Direction.HORIZONTAL, 3, Color.YELLOW),
                new Car(4, 0, Direction.VERTICAL, 2, Color.ORANGE),
                new Car(5, 0, Direction.VERTICAL, 2, Color.LIGHTBLUE),
                new Car(0, 2, Direction.HORIZONTAL, 2, Color.RED),
                new Car(2, 1, Direction.VERTICAL, 2, Color.PINK),
                new Car(3, 2, Direction.VERTICAL, 3, Color.PURPLE),
                new Car(2, 3, Direction.VERTICAL, 2, Color.DARKBLUE),
                new Car(4, 3, Direction.HORIZONTAL, 2, Color.DARKGREEN),
                new Car(0, 4, Direction.HORIZONTAL, 2, Color.BLACK),
                new Car(3, 5, Direction.HORIZONTAL, 2, Color.DARKCYAN)
        );
        HighscoreTable highscoreTable = new HighscoreTable();
        highScoreTablesForHardBoards.put(hardBoards.size(), highscoreTable);
        Board board = new Board(hardBoards.size(),6, 6, cars);
        hardBoards.add(board);
    }

    public Board giveBoardToDifficulty(Difficulty difficulty) {
        switch (difficulty) {
            case EASY -> {
                return easyBoards.get(0).clone();
            }
            case MEDIUM -> {
                return mediumBoards.get(0).clone();
            }
            case HARD -> {
                return hardBoards.get(0).clone();
            }
        }
        return null;
    }
}
