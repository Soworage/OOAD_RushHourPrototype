package application.model;

import java.util.ArrayList;
import java.util.List;

public class HighscoreTable {
    private List<HighscoreEntry> highscoreList;

    public HighscoreTable(){
        highscoreList = new ArrayList<>();
    }

    public void addEntry(String name, int anzahlZuege){
        highscoreList.add(new HighscoreEntry(name, anzahlZuege));
    }

    public void addEntry(HighscoreEntry highscoreEntry){
        this.highscoreList.add(highscoreEntry);
        System.out.println("SAVED ENTRY");
        System.out.println(highscoreEntry);
    }

    public List<HighscoreEntry> getHighscoreList() {
        return highscoreList;
    }
}
