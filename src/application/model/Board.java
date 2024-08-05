package application.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Stellt ein Spielbrett dar, das Autos enthalten und verwalten kann.
 * Das Brett hat eine bestimmte Breite und Höhe und verfolgt die Positionen der Autos.
 * Es unterstützt auch das Verschieben von Autos, das Benachrichtigen von Beobachtern und das Klonen des Brettes.
 *
 * <p>Hauptverantwortlicher:</p>
 * <ul>
 *     <li>Alex Becker</li>
 * </ul>
 * <p>Mitwirkende:</p>
 * <ul>
 *     <li>Alex Mihel</li>
 *     <li>Matthias Henzel</li>
 *     <li>Erik Witte</li>
 * </ul>
 */
public class Board implements Cloneable {

    private final int width;
    private final int height;
    private final Car[][] carPositions;
    private final List<CarObserver> observers;
    private final int boardId;
    private List<Car> cars;

    /**
     * Konstruiert ein neues Board mit den angegebenen Parametern.
     *
     * @param boardId       Eindeutige Kennung des Brettes.
     * @param width         Breite des Brettes.
     * @param height        Höhe des Brettes.
     * @param carsToAdd     Liste der hinzuzufügenden Autos.
     */
    public Board(int boardId, int width, int height, List<Car> carsToAdd) {
        this.boardId = boardId;
        this.width = width;
        this.height = height;
        this.cars = carsToAdd;
        this.carPositions = new Car[width][height];
        this.observers = new ArrayList<>();
        fillCarPositions();
    }

    /**
     * Gibt die eindeutige Kennung des Brettes zurück.
     *
     * @return Die eindeutige Kennung des Brettes.
     */
    public int getBoardId() {
        return boardId;
    }

    /**
     * Bewegt ein Auto zu einer neuen Position auf dem Brett, wenn sich die Position geändert hat.
     * Benachrichtigt die Beobachter, wenn die Bewegung erfolgreich war.
     *
     * @param car Das zu bewegende Auto.
     * @param x   Die neue x-Koordinate.
     * @param y   Die neue y-Koordinate.
     * @return True, wenn das Auto bewegt wurde, andernfalls false.
     */
    public boolean moveCar(Car car, int x, int y) {
        if (car.getXPosition() != x || car.getYPosition() != y) {
            clearCarPosition(car);
            placeCarInNewPosition(car, x, y);
            car.setXPosition(x);
            car.setYPosition(y);
            notifyObservers();
            return true;
        }
        return false;
    }

    /**
     * Setzt das Brett zurück und platziert alle Autos in ihren ursprünglichen Positionen.
     */
    public void makeReadyForUse() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                carPositions[i][j] = null;
            }
        }

        fillCarPositions();
    }

    /**
     * Abonniert einen Auto-Beobachter, um Updates zu erhalten.
     *
     * @param carObserver Der zu abonnierende Beobachter.
     */
    public void subscribeToUpdates(CarObserver carObserver) {
        observers.add(carObserver);
    }

    /**
     * Benachrichtigt alle abonnierten Beobachter über eine Änderung.
     */
    public void notifyObservers() {
        for (CarObserver carObserver : observers) {
            carObserver.update();
        }
    }

    /**
     * Debug-Funktion, die die Beobachter benachrichtigt.
     * Wird hauptsächlich zu Testzwecken verwendet.
     */
    public void debugFunction() {
        notifyObservers();
    }

    /**
     * Gibt das Auto an den angegebenen Koordinaten zurück.
     *
     * @param x Die x-Koordinate.
     * @param y Die y-Koordinate.
     * @return Das Auto an der angegebenen Position oder null, wenn kein Auto dort ist.
     */
    public Car getCarAt(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return carPositions[x][y];
        }
        return null;
    }

    /**
     * Gibt eine Zeichenfolgen-Darstellung des Brettes zurück, einschließlich seiner Dimensionen und der darauf befindlichen Autos.
     *
     * @return Eine Zeichenfolgen-Darstellung des Brettes.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Board [width=").append(width).append(", height=").append(height).append("]\n");
        stringBuilder.append("Fahrzeuge:\n");
        for (Car car : cars) {
            stringBuilder.append(car.toString()).append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Füllt das carPositions-Array mit null-Werten und platziert alle Autos in ihren ursprünglichen Positionen.
     */
    private void fillCarPositions() {
        for (int i = 0; i < width; i++) {
            Arrays.fill(carPositions[i], null);
        }
        for (Car car : cars) {
            placeCarInInitialPosition(car);
        }
    }

    /**
     * Löscht die Position des angegebenen Autos vom Brett.
     *
     * @param car Das Auto, dessen Position gelöscht werden soll.
     */
    private void clearCarPosition(Car car) {
        for (int i = 0; i < car.getLength(); i++) {
            if (car.getDirection() == Direction.HORIZONTAL) {
                carPositions[car.getYPosition()][car.getXPosition() + i] = null;
            } else if (car.getDirection() == Direction.VERTICAL) {
                carPositions[car.getYPosition() + i][car.getXPosition()] = null;
            }
        }
    }

    /**
     * Platziert das angegebene Auto an einer neuen Position auf dem Brett.
     *
     * @param car Das zu platzierende Auto.
     * @param x   Die x-Koordinate der neuen Position.
     * @param y   Die y-Koordinate der neuen Position.
     */
    private void placeCarInNewPosition(Car car, int x, int y) {
        for (int i = 0; i < car.getLength(); i++) {
            if (car.getDirection() == Direction.HORIZONTAL) {
                carPositions[y][x + i] = car;
            } else if (car.getDirection() == Direction.VERTICAL) {
                carPositions[y + i][x] = car;
            }
        }
    }

    /**
     * Platziert das angegebene Auto an seiner ursprünglichen Position auf dem Brett.
     *
     * @param car Das zu platzierende Auto.
     */
    private void placeCarInInitialPosition(Car car) {
        int x = car.getXPosition();
        int y = car.getYPosition();
        Direction direction = car.getDirection();
        for (int i = 0; i < car.getLength(); i++) {
            if (direction == Direction.HORIZONTAL) {
                carPositions[y][x + i] = car;
            } else if (direction == Direction.VERTICAL) {
                carPositions[y + i][x] = car;
            }
        }
    }

    /**
     * Erstellt und gibt eine tiefe Kopie dieses Brettes zurück.
     *
     * @return Eine Kopie dieses Brettes.
     */
    @Override
    public Board clone() {
        try {
            Board clone = (Board) super.clone();
            clone.cars = new ArrayList<>(this.cars.size());

            for (Car car : this.cars) {
                clone.cars.add(car.clone());
            }

            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
