package application.model;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Car implements Cloneable {

    private final Color carColor;
    private final List<CarObserver> observers = new ArrayList<>();
    private int xPosition;
    private int yPosition;
    private Direction direction;
    private int length;

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

    @Override
    public Car clone() {
        try {
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return (Car) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
