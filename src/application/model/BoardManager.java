package application.model;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class BoardManager {

    private final List<Board> easyBoards = new ArrayList<>();
    private final List<Board> mediumBoards = new ArrayList<>();
    private final List<Board> hardBoards = new ArrayList<>();

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
        Board board = new Board(6, 6, cars);
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
        Board board = new Board(6, 6, cars);
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
        Board board = new Board(6, 6, cars);
        hardBoards.add(board);
    }

    public Board giveBoardToDifficulty(Difficulty difficulty) {
        switch (difficulty) {
            case EASY -> {
                return easyBoards.get(0);
            }
            case MEDIUM -> {
                return mediumBoards.get(0);
            }
            case HARD -> {
                return hardBoards.get(0);
            }
        }
        return null;
    }
}
