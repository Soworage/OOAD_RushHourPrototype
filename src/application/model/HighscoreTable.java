package application.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Verwaltert eine Tabelle mit Highscore-Einträgen.
 * Diese Klasse ermöglicht das Hinzufügen neuer Highscore-Einträge und das Abrufen der Liste aller Einträge.
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
public class HighscoreTable {

    private final List<HighscoreEntry> highscoreList;

    /**
     * Konstruktor für eine neue Highscore-Tabelle.
     * Initialisiert die Liste der Highscore-Einträge.
     */
    public HighscoreTable() {
        highscoreList = new ArrayList<>();
    }

    /**
     * Fügt einen neuen Highscore-Eintrag zur Tabelle hinzu.
     *
     * @param highscoreEntry Der hinzuzufügende Highscore-Eintrag.
     */
    public void addEntry(HighscoreEntry highscoreEntry) {
        highscoreList.add(highscoreEntry);
        System.out.println("EINTRAG GESPEICHERT");
        System.out.println(highscoreEntry);
    }

    /**
     * Gibt die Liste aller Highscore-Einträge zurück.
     *
     * @return Eine Liste von Highscore-Einträgen.
     */
    public List<HighscoreEntry> getHighscoreList() {
        return highscoreList;
    }
}

