package application.model;

/**
 * Klasse zur Verwaltung der PIN-Eingabe.
 * <p>Hauptverantwortlicher:</p>
 * <ul>
 *     <li>Alex Mihel</li>
 * </ul>
 */
public class PINManager {

    private static final String CORRECT_PIN = "0815";

    /**
     * Überprüft, ob die eingegebene PIN korrekt ist.
     *
     * @param enteredPIN Die vom Benutzer eingegebene PIN.
     * @return {@code true}, wenn die eingegebene PIN korrekt ist, ansonsten {@code false}.
     */
    public boolean userEnterPIN(String enteredPIN) {
        return enteredPIN.equals(CORRECT_PIN);
    }
}

