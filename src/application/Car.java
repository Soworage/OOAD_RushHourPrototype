package application;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

// Klasse für das Fahrzeug
public class Car {
    // Exemplarvariablen
    private int xPosition;
    private int yPosition;
    private Direction direction;
    private int length;
    private Color carColor;
    private List<CarObserver> observers = new ArrayList<>();


    // Konstruktor
    public Car(int xPosition, int yPosition, Direction direction, int length, Color carColor) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.direction = direction;
        this.carColor = carColor;
        this.length = length;

    }

    public Color getCarColor() {
        return carColor;
    }

    // Getter-Methoden
    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getLength() {
        return length;
    }

    // Setter-Methoden
    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setLength(int length) {
        this.length = length;
    }

    // Methode zur Darstellung des Fahrzeugs als String
    @Override
    public String toString() {
        return "Car [xPosition=" + xPosition + ", yPosition=" + yPosition
                + ", direction=" + direction + ", length=" + length + "]";
    }
}