package application;

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

    public String getHighscoreList(){
        StringBuilder bob = new StringBuilder();
        for (HighscoreEntry h : highscoreList){
            bob.append("");
        }
        return "";
    }
}
