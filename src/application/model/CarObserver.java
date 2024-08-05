package application.model;

/**
 * Schnittstelle für Beobachter von Autos, die über Änderungen informiert werden möchten.
 * Die Beobachter implementieren diese Schnittstelle, um über Updates benachrichtigt zu werden.
 */
public interface CarObserver {

    /**
     * Wird aufgerufen, wenn ein Update für das beobachtete Auto verfügbar ist.
     */
    void update();
}
