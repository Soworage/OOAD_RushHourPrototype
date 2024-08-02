package application.model;

public class GameSettings {

    private static GameSettings instance;
    private Difficulty difficulty;

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

    public void resetToDefault() {
        this.difficulty = null;
        Color colorSetting = null;
        System.out.println("GameSettings reset confirmed");
    }
}
