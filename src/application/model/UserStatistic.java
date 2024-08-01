package application.model;

public class UserStatistic {

    private static UserStatistic instance;
    private int moveCount;
    private int seconds;
    private String name;

    private UserStatistic() {
    }

    public static synchronized UserStatistic getInstance() {
        if (instance == null) {
            instance = new UserStatistic();
        }
        return instance;
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
}
