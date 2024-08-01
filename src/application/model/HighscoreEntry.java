package application.model;

//import java.time.LocalDate;

public class HighscoreEntry {
    private String name;
    private int anzahlZuege;
    //private LocalDate datum;

    public HighscoreEntry(String name, int anzahlZuege){
        this.name = name;
        this.anzahlZuege = anzahlZuege;
       // datum = LocalDate.now();
    }

   /* public LocalDate getDatum(){
        return datum;
    }*/

    public int getAnzahlZuege(){
        return anzahlZuege;
    }

    public String getName(){
        return name;
    }

    public String toString(){
        return "test";}
}
