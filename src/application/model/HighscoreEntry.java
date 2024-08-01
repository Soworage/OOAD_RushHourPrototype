package application.model;

import java.time.LocalDate;

public class HighscoreEntry {

    private String name;
    private int moveCount;
    private LocalDate date;
    private String elapsedTime;

    public HighscoreEntry(String name, int moveCount) {
        this.name = name;
        this.moveCount = moveCount;
        this.date = LocalDate.now();
    }

    public HighscoreEntry(String name, int moveCount, LocalDate date, String elapsedTime) {
        this.name = name;
        this.moveCount = moveCount;
        this.date = date;
        this.elapsedTime = elapsedTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(String elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

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
