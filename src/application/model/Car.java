package application.model;

import javafx.scene.paint.Color;

/* Hauptverantwortlicher: Erik Witte */
/* Mitwirkend: Alex Becker, Alex Mihel*/
public class Car implements Cloneable {

    private final Color carColor;
    private final Direction direction;
    private final int length;
    private int xPosition;
    private int yPosition;

    private Car(Builder builder) {
        this.xPosition = builder.xPosition;
        this.yPosition = builder.yPosition;
        this.direction = builder.direction;
        this.length = builder.length;
        this.carColor = builder.carColor;
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

    public static class Builder {
        private Color carColor;
        private Direction direction;
        private int length;
        private int xPosition;
        private int yPosition;

        public Builder setCarColor(Color carColor) {
            this.carColor = carColor;
            return this;
        }

        public Builder setDirection(Direction direction) {
            this.direction = direction;
            return this;
        }

        public Builder setLength(int length) {
            this.length = length;
            return this;
        }

        public Builder setXPosition(int xPosition) {
            this.xPosition = xPosition;
            return this;
        }

        public Builder setYPosition(int yPosition) {
            this.yPosition = yPosition;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}

