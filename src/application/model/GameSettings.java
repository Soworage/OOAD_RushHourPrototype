package application.model;

/* Hauptverantwortlicher: Alex Becker */

public class GameSettings {

    private static GameSettings instance;
    private Difficulty difficulty;
    private Color colorScheme;

    private GameSettings() {
    }

    public static GameSettings getInstance() {
        if (instance == null) {
            instance = new GameSettings();
        }
        return instance;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Color getColorScheme() {
        return colorScheme;
    }

    public void setColorScheme(Color colorScheme) {
        this.colorScheme = colorScheme;
    }

    public void resetToDefault() {
        this.difficulty = null;
        this.colorScheme = null;
        System.out.println("GameSettings reset confirmed");
    }
}
