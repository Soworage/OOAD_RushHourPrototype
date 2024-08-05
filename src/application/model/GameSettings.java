package application.model;

/**
 * Stellt die Einstellungen für das Spiel dar.
 * Diese Klasse verwendet das Singleton-Muster, um sicherzustellen, dass nur eine Instanz
 * der Spielkonfiguration existiert und global zugänglich ist.
 *
 * <p>Hauptverantwortlicher:</p>
 * <ul>
 *     <li>Alex Becker</li>
 * </ul>
 */
public class GameSettings {

    private static GameSettings instance;
    private Difficulty difficulty;
    private Color colorScheme;

    /**
     * Private Konstruktor, um die direkte Instanziierung von außen zu verhindern.
     */
    private GameSettings() {
    }

    /**
     * Gibt die einzige Instanz der GameSettings-Klasse zurück.
     * Wenn die Instanz noch nicht erstellt wurde, wird sie erstellt.
     *
     * @return Die einzige Instanz von GameSettings.
     */
    public static GameSettings getInstance() {
        if (instance == null) {
            instance = new GameSettings();
        }
        return instance;
    }

    /**
     * Gibt den aktuell festgelegten Schwierigkeitsgrad zurück.
     *
     * @return Der aktuell festgelegte Schwierigkeitsgrad.
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Setzt den Schwierigkeitsgrad für das Spiel.
     *
     * @param difficulty Der neue Schwierigkeitsgrad.
     */
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Gibt das aktuell festgelegte Farbschema zurück.
     *
     * @return Das aktuell festgelegte Farbschema.
     */
    public Color getColorScheme() {
        return colorScheme;
    }

    /**
     * Setzt das Farbschema für das Spiel.
     *
     * @param colorScheme Das neue Farbschema.
     */
    public void setColorScheme(Color colorScheme) {
        this.colorScheme = colorScheme;
    }

    /**
     * Setzt die Spielkonfiguration auf die Standardwerte zurück.
     * Setzt den Schwierigkeitsgrad und das Farbschema auf null und gibt eine Bestätigungsmeldung aus.
     */
    public void resetToDefault() {
        this.difficulty = null;
        this.colorScheme = null;
        System.out.println("GameSettings reset confirmed");
    }
}
