package application;

import java.util.ArrayList;
import java.util.List;

public class BoardManager {
    private List<Board> boards = new ArrayList<Board>();

    public BoardManager() {
        //create example board
        List<Car> cars = new ArrayList<>();
        Car car = new Car(2, 2, Direction.HORIZONTAL, 3);
        cars.add(car);
        Board board = new Board(6,6, cars);
        boards.add(board);
    }

    public List<Board> getBoards() {
        return boards;
    }
}
