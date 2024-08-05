package application.model;

/**
 * Schnittstelle für die Erstellung von Spielbrettern und Highscore-Tabellen.
 * <p>Hauptverantwortlicher:</p>
 * <ul>
 *     <li>Erik Witte</li>
 * </ul>
 */
public interface BoardCreatorInterface {

    /**
     * Gibt das aktuelle Spielbrett zurück.
     *
     * @return Das Spielbrett.
     */
    Board getBoard();

    /**
     * Gibt die Highscore-Tabelle für das angegebene Brett-ID zurück.
     *
     * @param boardId Die eindeutige Kennung des Brettes.
     * @return Die Highscore-Tabelle für das angegebene Brett-ID.
     */
    HighscoreTable getHighscoreTable(int boardId);
}
