package application.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Verwaltet die Erstellung von Spielbrettern und Highscore-Tabellen für verschiedene Schwierigkeitsgrade.
 * <p>Hauptverantwortlicher:</p>
 * <ul>
 *     <li>Erik Witte</li>
 * </ul>
 * <p>Mitwirkende:</p>
 * <ul>
 *     <li>Alex Becker</li>
 *     <li>Alex Mihel</li>
 * </ul>
 */
public class BoardManager {

    private final Map<Difficulty, BoardCreatorInterface> boardCreators;

    /**
     * Konstruiert einen neuen BoardManager und initialisiert die Ersteller für verschiedene Schwierigkeitsgrade.
     */
    public BoardManager() {
        boardCreators = new HashMap<>();
        boardCreators.put(Difficulty.EASY, new EasyBoardCreator());
        boardCreators.put(Difficulty.HARD, new HardBoardCreator());
        boardCreators.put(Difficulty.MEDIUM, new MediumBoardCreator());
    }

    /**
     * Gibt die Highscore-Tabelle für das angegebene Brett und den angegebenen Schwierigkeitsgrad zurück.
     *
     * @param boardID    Die eindeutige Kennung des Brettes.
     * @param difficulty Der Schwierigkeitsgrad des Brettes.
     * @return Die Highscore-Tabelle für das angegebene Brett und Schwierigkeitsgrad.
     */
    public HighscoreTable getHighScoreTableForBoard(int boardID, Difficulty difficulty) {
        System.out.println("Got a request for Board id " + boardID + " and diff " + difficulty);
        return boardCreators.get(difficulty).getHighscoreTable(boardID);
    }

    /**
     * Gibt das Spielbrett für den angegebenen Schwierigkeitsgrad zurück.
     *
     * @param difficulty Der Schwierigkeitsgrad des Brettes.
     * @return Das Spielbrett für den angegebenen Schwierigkeitsgrad.
     */
    public Board giveBoardToDifficulty(Difficulty difficulty) {
        return boardCreators.get(difficulty).getBoard();
    }

}
