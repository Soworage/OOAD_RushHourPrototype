package application.model;

//import java.time.LocalDate;

import java.time.LocalDate;

public class HighscoreEntry {
    private String name;
    private int anzahlZuege;
    private LocalDate datum;
    String elapsedTime;

    public HighscoreEntry(String name, int anzahlZuege){
        this.name = name;
        this.anzahlZuege = anzahlZuege;
        datum = LocalDate.now();
    }

    public HighscoreEntry(String name, int anzahlZuege, LocalDate datum, String elapsedTime) {
        this.name = name;
        this.anzahlZuege = anzahlZuege;
        this.datum = datum;
        this.elapsedTime = elapsedTime;
    }

    public LocalDate getDatum(){
        return datum;
    }

    public int getAnzahlZuege(){
        return anzahlZuege;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAnzahlZuege(int anzahlZuege) {
        this.anzahlZuege = anzahlZuege;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public String getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(String elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return "HighscoreEntry{" +
                "name='" + name + '\'' +
                ", anzahlZuege=" + anzahlZuege +
                ", datum=" + datum +
                ", elapsedTime='" + elapsedTime + '\'' +
                '}';
    }
}
