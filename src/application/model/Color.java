package application.model;

/**
 * Enum für verschiedene Farben oder Farbtöne, die in der Anwendung verwendet werden.
 * Diese Farben können für unterschiedliche Zwecke wie visuelle Darstellungen oder
 * Farbanpassungen verwendet werden.
 *
 * <p>Hauptverantwortlicher:</p>
 * <ul>
 *     <li>Alex Mihel</li>
 * </ul>
 */
public enum Color {
    /**
     * Standardfarbe, die verwendet wird, wenn keine spezifische Farbe festgelegt ist.
     */
    DEFAULT,

    /**
     * Farbe für Protanomalie, eine Form der Rot-Grün-Schwäche, bei der rote Farben
     * schwerer zu unterscheiden sind.
     */
    PROTANOMALIEN,

    /**
     * Farbe für Protanopie, eine Form der Rot-Grün-Schwäche, bei der rote Farben
     * nicht erkannt werden.
     */
    PROTANOPIEN,

    /**
     * Farbe für Deuteranomalie, eine Form der Rot-Grün-Schwäche, bei der grüne Farben
     * schwerer zu unterscheiden sind.
     */
    DEUTERANOMALIEN,

    /**
     * Farbe für Deuteranopie, eine Form der Rot-Grün-Schwäche, bei der grüne Farben
     * nicht erkannt werden.
     */
    DEUTERANOPIEN
}

