package application.model;

/**
 * Singleton-Klasse zur Verwaltung der Benutzerdaten und -statistiken.
 * <p>Hauptverantwortlicher:</p>
 * <ul>
 *     <li>Alex Becker</li>
 * </ul>
 * <p>Mitwirkend:</p>
 * <ul>
 *     <li>Matthias Henzel</li>
 *     <li>Alex Mihel</li>
 * </ul>
 */
public class UserStatistic {

    private static UserStatistic instance;
    private int moveCount;
    private int seconds;
    private String name;
    private Board selectedBoard;

    private UserStatistic() {
    }

    /**
     * Gibt die einzige Instanz von {@code UserStatistic} zurück.
     *
     * @return Die Singleton-Instanz von {@code UserStatistic}.
     */
    public static synchronized UserStatistic getInstance() {
        if (instance == null) {
            instance = new UserStatistic();
        }
        return instance;
    }

    /**
     * Gibt das aktuell ausgewählte Board zurück.
     *
     * @return Das aktuell ausgewählte Board.
     */
    public Board getSelectedBoard() {
        return selectedBoard;
    }

    /**
     * Setzt das aktuell ausgewählte Board.
     *
     * @param selectedBoard Das Board, das ausgewählt werden soll.
     */
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

    /**
     * Gibt den Namen des Benutzers zurück.
     *
     * @return Der Name des Benutzers.
     */
    public String getName() {
        return name;
    }

    /**
     * Setzt den Namen des Benutzers.
     *
     * @param name Der Name des Benutzers.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gibt die Anzahl der Bewegungen zurück.
     *
     * @return Die Anzahl der Bewegungen.
     */
    public int getMoveCount() {
        return moveCount;
    }

    /**
     * Erhöht die Anzahl der Bewegungen um eins.
     */
    public void addMove() {
        moveCount++;
    }

    /**
     * Gibt die Anzahl der Sekunden zurück.
     *
     * @return Die Anzahl der Sekunden.
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     * Erhöht die Anzahl der Sekunden um eins.
     */
    public void addSeconds() {
        seconds++;
    }

    /**
     * Setzt die Benutzerdaten auf die Standardwerte zurück.
     */
    public void setToDefault() {
        setName("");
        setSelectedBoard(null);
        resetMoveCount();
        resetSeconds();
        resetSelectedBoard();
        System.out.println("Reset of session confirmed");
    }
}
