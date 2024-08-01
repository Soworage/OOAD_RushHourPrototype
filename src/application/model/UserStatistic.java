package application.model;

public class UserStatistic {
    private static UserStatistic instance;

    private int moveCount;
    private int seconds;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Private Konstruktor, um die direkte Instanziierung zu verhindern
    private UserStatistic() {
        // Initialisierung, falls erforderlich
    }

    // Statische Methode, um die einzige Instanz der Klasse zu bekommen
    public static synchronized UserStatistic getInstance() {
        if (instance == null) {
            instance = new UserStatistic();
        }
        return instance;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void addMove() {
        moveCount++;
    }

    public void addSeconds() {
        seconds++;
    }

    public int getSeconds() {
        return seconds;
    }
}