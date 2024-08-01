package application.model;

public class GameSettings {

    private static GameSettings instance;
    private Difficulty difficulty;
    private Color colorSetting;

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

    public Color getColorSetting() {
        return colorSetting;
    }

    public void setColorSetting(Color colorSetting) {
        this.colorSetting = colorSetting;
    }

    public void resetToDefault() {
        this.difficulty = null;
        this.colorSetting = null;
        System.out.println("GameSettings reset confirmed");
    }
}
