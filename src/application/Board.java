package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Klasse für das Spielfeld
public class Board {
    // Exemplarvariablen
    private int width;
    private int height;
    private List<Car> cars;
    private Car[][] carPositions;
    List<CarObserver> observers;

    // Konstruktor
    public Board(int width, int height, List<Car> carsToAdd) {
        this.width = width;
        this.height = height;
        this.cars = carsToAdd;
        this.carPositions = new Car[width][height];
        this.observers = new ArrayList<>();
        fillArray();
    }

    public void moveCar(Car car, int x, int y){
        //search car in array

        for(int i=0; i<car.getLength(); i++){
            if(car.getDirection() == Direction.HORIZONTAL){
                carPositions[car.getYPosition()][car.getXPosition()+i] = null;
            } else if(car.getDirection() == Direction.VERTICAL){
                carPositions[car.getYPosition()+i][car.getXPosition()] = null;
            }
        }

        //build into array again
        for(int i=0; i<car.getLength(); i++){
            if(car.getDirection() == Direction.HORIZONTAL){
                carPositions[y][x+i] = car;
            } else if(car.getDirection() == Direction.VERTICAL){
                carPositions[y+i][x] = car;
            }
        }

        car.setXPosition(x);
        car.setYPosition(y);

        notifyObservers();

    }

    // Getter für die Breite
    public int getWidth() {
        return width;
    }

    // Getter für die Höhe
    public int getHeight() {
        return height;
    }

    public void subscribeToUpdates(CarObserver carObserver){
        observers.add(carObserver);
    }

    public void notifyObservers(){
        for(CarObserver carObserver : observers){
            carObserver.update();
        }
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


    public void debugFunction(){
        notifyObservers();
    }

    private void fillArray(){
        // Setze das Array auf null (Standardwert für Objekte)
        for (int i = 0; i < width; i++) {
            Arrays.fill(carPositions[i], null);
        }

        for (Car car : cars) {
            int x = car.getXPosition();
            int y = car.getYPosition();
            Direction direction = car.getDirection();
            int length = car.getLength();

            // Fülle das Array basierend auf der Richtung des Autos
            for (int i = 0; i < length; i++) {
                if (direction == Direction.HORIZONTAL) {
                    carPositions[y][x + i] = car; // Setze das Auto an der entsprechenden Position
                } else if (direction == Direction.VERTICAL) {
                    carPositions[y + i][x] = car; // Setze das Auto an der entsprechenden Position
                }
            }
        }
    }

    // Methode zum Entfernen eines Fahrzeugs
    public void removeCar(Car car) {
        cars.remove(car);
    }



    public Car getCarAt(int x, int y){
        if(x >= 0 && x < width && y >= 0 && y < height){
            if(carPositions[x][y] != null) {
                return carPositions[x][y];
            }
        }
        return null;
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