package application;

import java.util.ArrayList;
import java.util.List;

// Klasse für das Spielfeld
public class Board {
    // Exemplarvariablen
    private int width;
    private int height;
    private List<Car> cars;

    // Konstruktor
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.cars = new ArrayList<>();
    }

    // Getter für die Breite
    public int getWidth() {
        return width;
    }

    // Getter für die Höhe
    public int getHeight() {
        return height;
    }

    // Methode zum Hinzufügen eines Fahrzeugs
    public void addCar(Car car) {
        // Überprüfen, ob das Fahrzeug innerhalb des Spielfeldes liegt
        if (isValidPosition(car)) {
            cars.add(car);
        } else {
            System.out.println("Fahrzeugposition außerhalb des Spielfelds.");
        }
    }

    // Methode zum Entfernen eines Fahrzeugs
    public void removeCar(Car car) {
        cars.remove(car);
    }

    public void moveCar(Car car){
        if(isValidPosition(car)){
            //.. move car
        }
    }

    // Methode zur Überprüfung der Gültigkeit der Fahrzeugposition
    private boolean isValidPosition(Car car) {
        int xPos = car.getXPosition();
        int yPos = car.getYPosition();
        double length = car.getLength();

        // Überprüfen, ob das Fahrzeug innerhalb der Grenzen des Spielfeldes liegt
        if (xPos < 0 || yPos < 0 || xPos + length > width || yPos + length > height) {
            return false;
        }
        return true;
    }

    // Methode zur Darstellung des Spielfelds als String
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Board [width=").append(width).append(", height=").append(height).append("]\n");
        sb.append("Fahrzeuge:\n");
        for (Car car : cars) {
            sb.append(car.toString()).append("\n");
        }
        return sb.toString();
    }
}