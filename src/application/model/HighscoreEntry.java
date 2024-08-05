package application.model;

import java.time.LocalDate;

/**
 * Repräsentiert einen Eintrag in der Highscore-Tabelle.
 * Dieser Eintrag enthält Informationen wie die Anzahl der Züge, die verstrichene Zeit, den Namen des Spielers und das Datum des Eintrags.
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
public class HighscoreEntry {

    private final int moveCount;
    private final String elapsedTime;
    private String name;
    private LocalDate date;

    /**
     * Konstruktor für einen Highscore-Eintrag.
     *
     * @param name         Der Name des Spielers.
     * @param moveCount    Die Anzahl der Züge.
     * @param date         Das Datum des Eintrags.
     * @param elapsedTime  Die verstrichene Zeit im Format "hh:mm:ss".
     */
    public HighscoreEntry(String name, int moveCount, LocalDate date, String elapsedTime) {
        this.name = name;
        this.moveCount = moveCount;
        this.date = date;
        this.elapsedTime = elapsedTime;
    }

    /**
     * Gibt das Datum des Highscore-Eintrags zurück.
     *
     * @return Das Datum des Eintrags.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Setzt das Datum des Highscore-Eintrags.
     *
     * @param date Das neue Datum des Eintrags.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gibt die Anzahl der Züge zurück, die für diesen Highscore-Eintrag benötigt wurden.
     *
     * @return Die Anzahl der Züge.
     */
    public int getMoveCount() {
        return moveCount;
    }

    /**
     * Gibt den Namen des Spielers zurück, der diesen Highscore-Eintrag erstellt hat.
     *
     * @return Der Name des Spielers.
     */
    public String getName() {
        return name;
    }

    /**
     * Setzt den Namen des Spielers für diesen Highscore-Eintrag.
     *
     * @param name Der neue Name des Spielers.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gibt die verstrichene Zeit für diesen Highscore-Eintrag zurück.
     *
     * @return Die verstrichene Zeit im Format "hh:mm:ss".
     */
    public String getElapsedTime() {
        return elapsedTime;
    }

    /**
     * Gibt eine String-Darstellung des Highscore-Eintrags zurück.
     *
     * @return Eine String-Darstellung des Highscore-Eintrags.
     */
    @Override
    public String toString() {
        return "HighscoreEntry{" +
                "name='" + name + '\'' +
                ", moveCount=" + moveCount +
                ", date=" + date +
                ", elapsedTime='" + elapsedTime + '\'' +
                '}';
    }
}
