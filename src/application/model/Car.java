package application.model;

import javafx.scene.paint.Color;

/**
 * Stellt ein Auto auf dem Spielbrett dar.
 * Jedes Auto hat eine Farbe, eine Richtung, eine Länge und eine Position.
 * Die Klasse unterstützt das Klonen von Autos und die Verwendung des Builder-Designmusters zur Erstellung von Autos.
 *
 * <p>Hauptverantwortlicher:</p>
 * <ul>
 *     <li>Erik Witte</li>
 * </ul>
 * <p>Mitwirkende:</p>
 * <ul>
 *     <li>Alex Becker</li>
 *     <li>Alex Mihel</li>
 * </ul>
 */
public class Car implements Cloneable {

    private final Color carColor;
    private final Direction direction;
    private final int length;
    private int xPosition;
    private int yPosition;

    /**
     * Konstruiert ein Auto mithilfe des Builder-Objekts.
     *
     * @param builder Der Builder, der die Attribute des Autos festlegt.
     */
    private Car(Builder builder) {
        this.xPosition = builder.xPosition;
        this.yPosition = builder.yPosition;
        this.direction = builder.direction;
        this.length = builder.length;
        this.carColor = builder.carColor;
    }

    /**
     * Gibt die Farbe des Autos zurück.
     *
     * @return Die Farbe des Autos.
     */
    public Color getCarColor() {
        return carColor;
    }

    /**
     * Gibt die x-Position des Autos zurück.
     *
     * @return Die x-Position des Autos.
     */
    public int getXPosition() {
        return xPosition;
    }

    /**
     * Setzt die x-Position des Autos.
     *
     * @param xPosition Die neue x-Position des Autos.
     */
    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    /**
     * Gibt die y-Position des Autos zurück.
     *
     * @return Die y-Position des Autos.
     */
    public int getYPosition() {
        return yPosition;
    }

    /**
     * Setzt die y-Position des Autos.
     *
     * @param yPosition Die neue y-Position des Autos.
     */
    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    /**
     * Gibt die Richtung des Autos zurück.
     *
     * @return Die Richtung des Autos.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Gibt die Länge des Autos zurück.
     *
     * @return Die Länge des Autos.
     */
    public int getLength() {
        return length;
    }

    /**
     * Gibt eine Zeichenfolgen-Darstellung des Autos zurück.
     *
     * @return Eine Zeichenfolgen-Darstellung des Autos.
     */
    @Override
    public String toString() {
        return "Car [xPosition=" + xPosition + ", yPosition=" + yPosition
                + ", direction=" + direction + ", length=" + length + "]";
    }

    /**
     * Erstellt eine Kopie dieses Autos.
     *
     * @return Eine Kopie dieses Autos.
     */
    @Override
    public Car clone() {
        try {
            return (Car) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    /**
     * Builder-Klasse zur Erstellung von Autos.
     * Ermöglicht das Setzen der Attribute des Autos Schritt für Schritt.
     */
    public static class Builder {
        private Color carColor;
        private Direction direction;
        private int length;
        private int xPosition;
        private int yPosition;

        /**
         * Setzt die Farbe des Autos im Builder.
         *
         * @param carColor Die Farbe des Autos.
         * @return Der Builder.
         */
        public Builder setCarColor(Color carColor) {
            this.carColor = carColor;
            return this;
        }

        /**
         * Setzt die Richtung des Autos im Builder.
         *
         * @param direction Die Richtung des Autos.
         * @return Der Builder.
         */
        public Builder setDirection(Direction direction) {
            this.direction = direction;
            return this;
        }

        /**
         * Setzt die Länge des Autos im Builder.
         *
         * @param length Die Länge des Autos.
         * @return Der Builder.
         */
        public Builder setLength(int length) {
            this.length = length;
            return this;
        }

        /**
         * Setzt die x-Position des Autos im Builder.
         *
         * @param xPosition Die x-Position des Autos.
         * @return Der Builder.
         */
        public Builder setXPosition(int xPosition) {
            this.xPosition = xPosition;
            return this;
        }

        /**
         * Setzt die y-Position des Autos im Builder.
         *
         * @param yPosition Die y-Position des Autos.
         * @return Der Builder.
         */
        public Builder setYPosition(int yPosition) {
            this.yPosition = yPosition;
            return this;
        }

        /**
         * Baut ein Auto basierend auf den festgelegten Attributen des Builders.
         *
         * @return Das erstellte Auto.
         */
        public Car build() {
            return new Car(this);
        }
    }
}
