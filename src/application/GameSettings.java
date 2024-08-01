package application;

public class GameSettings {
    private static GameSettings instance;
    private  Difficulty difficulty;
    private Color colorSetting;

    private GameSettings() {
        // Private Constructor to prevent instantiation
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

    public Color getColorSetting() {
        return colorSetting;
    }

    public void setColorSetting(Color colorSetting) {
        this.colorSetting = colorSetting;
    }

    public void resetToDefault() {

    }
}