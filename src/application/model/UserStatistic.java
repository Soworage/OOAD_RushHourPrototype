package application.model;

public class UserStatistic {

    private static UserStatistic instance;
    private int moveCount;
    private int seconds;
    private String name;
    private Board selectedBoard;

    private UserStatistic() {
    }

    public static synchronized UserStatistic getInstance() {
        if (instance == null) {
            instance = new UserStatistic();
        }
        return instance;
    }

    public Board getSelectedBoard() {
        return selectedBoard;
    }

    public void setSelectedBoard(Board selectedBoard) {
        this.selectedBoard = selectedBoard;
    }

    private void resetMoveCount() {
        this.moveCount = 0;
    }

    private void resetSeconds() {
        this.seconds = 0;
    }

    private void resetSelectedBoard() {
        this.selectedBoard = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void addMove() {
        moveCount++;
    }

    public int getSeconds() {
        return seconds;
    }

    public void addSeconds() {
        seconds++;
    }

    public void setToDefault() {
        setName("");
        setSelectedBoard(null);
        resetMoveCount();
        resetSeconds();
        resetSelectedBoard();
        System.out.println("Reset of session confirmed");
    }
}
