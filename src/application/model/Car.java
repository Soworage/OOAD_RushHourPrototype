package application.model;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class Car {

    private int xPosition;
    private int yPosition;
    private Direction direction;
    private int length;
    private Color carColor;
    private List<CarObserver> observers = new ArrayList<>();

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

    public int getXPosition() {
        return xPosition;
    }

    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Car [xPosition=" + xPosition + ", yPosition=" + yPosition
                + ", direction=" + direction + ", length=" + length + "]";
    }
}
