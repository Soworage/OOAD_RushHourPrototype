package application.model;

import java.util.ArrayList;
import java.util.List;

/* Hauptverantwortlicher: Alex Becker */
/* Mitwirkend: Matthias Henzel, Alex Mihel*/
public class HighscoreTable {

    private final List<HighscoreEntry> highscoreList;

    public HighscoreTable() {
        highscoreList = new ArrayList<>();
    }


    public void addEntry(HighscoreEntry highscoreEntry) {
        highscoreList.add(highscoreEntry);
        System.out.println("SAVED ENTRY");
        System.out.println(highscoreEntry);
    }

    public List<HighscoreEntry> getHighscoreList() {
        return highscoreList;
    }
}
