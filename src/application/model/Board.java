package application.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board implements Cloneable {

    private int width;
    private int height;
    private List<Car> cars;
    private Car[][] carPositions;
    private List<CarObserver> observers;
    private int boardId;

    public Board(int boardId,int width, int height, List<Car> carsToAdd) {
        this.boardId = boardId;
        this.width = width;
        this.height = height;
        this.cars = carsToAdd;
        this.carPositions = new Car[width][height];
        this.observers = new ArrayList<>();
        fillCarPositions();
    }

    public int getBoardId() {
        return boardId;
    }

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

    public void makeReadyForUse(){
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                carPositions[i][j] = null;
            }
        }

        fillCarPositions();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void subscribeToUpdates(CarObserver carObserver) {
        observers.add(carObserver);
    }

    public void notifyObservers() {
        for (CarObserver carObserver : observers) {
            carObserver.update();
        }
    }

    public void addCar(Car car) {
        if (isValidPosition(car)) {
            cars.add(car);
        } else {
            System.out.println("Fahrzeugposition außerhalb des Spielfelds.");
        }
    }

    public void debugFunction() {
        notifyObservers();
    }

    public void removeCar(Car car) {
        cars.remove(car);
    }

    public Car getCarAt(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return carPositions[x][y];
        }
        return null;
    }

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

    private void fillCarPositions() {
        for (int i = 0; i < width; i++) {
            Arrays.fill(carPositions[i], null);
        }
        for (Car car : cars) {
            placeCarInInitialPosition(car);
        }
    }



    private void clearCarPosition(Car car) {
        for (int i = 0; i < car.getLength(); i++) {
            if (car.getDirection() == Direction.HORIZONTAL) {
                carPositions[car.getYPosition()][car.getXPosition() + i] = null;
            } else if (car.getDirection() == Direction.VERTICAL) {
                carPositions[car.getYPosition() + i][car.getXPosition()] = null;
            }
        }
    }

    private void placeCarInNewPosition(Car car, int x, int y) {
        for (int i = 0; i < car.getLength(); i++) {
            if (car.getDirection() == Direction.HORIZONTAL) {
                carPositions[y][x + i] = car;
            } else if (car.getDirection() == Direction.VERTICAL) {
                carPositions[y + i][x] = car;
            }
        }
    }

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

    private boolean isValidPosition(Car car) {
        int xPos = car.getXPosition();
        int yPos = car.getYPosition();
        int length = car.getLength();
        if (xPos < 0 || yPos < 0 || xPos + length > width || yPos + length > height) {
            return false;
        }
        return true;
    }

    @Override
    public Board clone() {
        try {
            Board clone = (Board) super.clone();
            clone.cars = new ArrayList<>(this.cars.size());

            for(Car car : this.cars){
                clone.cars.add(car.clone());
            }

            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
