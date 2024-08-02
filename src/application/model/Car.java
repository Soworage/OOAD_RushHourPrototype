package application.model;

import javafx.scene.paint.Color;


public class Car implements Cloneable {

    private final Color carColor;
    private final Direction direction;
    private final int length;
    private int xPosition;
    private int yPosition;

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


    public int getLength() {
        return length;
    }


    @Override
    public String toString() {
        return "Car [xPosition=" + xPosition + ", yPosition=" + yPosition
                + ", direction=" + direction + ", length=" + length + "]";
    }

    @Override
    public Car clone() {
        try {
            return (Car) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
