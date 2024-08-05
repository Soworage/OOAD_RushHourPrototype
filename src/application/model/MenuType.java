package application.model;

/**
 * Enum für die verschiedenen Menütypen in der Anwendung.
 * <p>Hauptverantwortlicher:</p>
 * <ul>
 *     <li>Alex Becker</li>
 * </ul>
 */
public enum MenuType {
    /**
     * Hauptmenü, das beim Start der Anwendung angezeigt wird.
     */
    MAIN_MENU,

    /**
     * Menü für die Spieleinstellungen.
     */
    GAME_MENU,

    /**
     * Menü zur Auswahl des Schwierigkeitsgrads.
     */
    DIFFICULTY_MENU,

    /**
     * Menü zur Auswahl des Farbschemas.
     */
    COLORSCHEME_MENU,

    /**
     * Menü zur Anzeige der Highscores.
     */
    HIGHSCORE_MENU,

    /**
     * Menü zur Eingabe des Namens.
     */
    INPUTNAME_MENU,

    /**
     * Menü zur Anzeige von Statistiken.
     */
    STATISTICS_MENU,

    /**
     * Menü zur Rücksetzung der PIN.
     */
    RESETPIN_MENU
}
