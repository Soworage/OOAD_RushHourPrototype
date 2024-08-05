package application.model;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Erzeugt und verwaltet Boards mit mittlerem Schwierigkeitsgrad.
 * Implementiert das `BoardCreatorInterface` zur Erstellung von Boards und Highscore-Tabellen.
 * <p>Hauptverantwortlicher:</p>
 * <ul>
 *     <li>Erik Witte</li>
 * </ul>
 */
public class MediumBoardCreator implements BoardCreatorInterface {
    private final List<Board> boards;
    private final Map<Integer, HighscoreTable> highscoreTables;

    /**
     * Konstruktor für den MediumBoardCreator.
     * Initialisiert die Liste der Boards und Highscore-Tabellen und füllt sie mit Daten.
     */
    public MediumBoardCreator() {
        boards = new ArrayList<>();
        highscoreTables = new HashMap<>();
        populateBoards();
    }

    /**
     * Gibt ein Board zurück.
     * <p>Für das finale Produkt ist ein Zufallszahlengenerator vorgesehen.</p>
     *
     * @return Ein Klon des ersten Boards in der Liste.
     */
    @Override
    public Board getBoard() {
        return boards.get(0).clone();
    }

    /**
     * Gibt die Highscore-Tabelle für ein bestimmtes Board-ID zurück.
     *
     * @param boardId Die ID des Boards, für das die Highscore-Tabelle abgerufen werden soll.
     * @return Die Highscore-Tabelle für das angegebene Board.
     */
    @Override
    public HighscoreTable getHighscoreTable(int boardId) {
        return highscoreTables.get(boardId);
    }

    /**
     * Füllt die Boards mit Car-Objekten und initialisiert die Highscore-Tabellen.
     */
    private void populateBoards() {
        List<Car> cars = List.of(
                new Car.Builder()
                        .setXPosition(1)
                        .setYPosition(0)
                        .setDirection(Direction.VERTICAL)
                        .setLength(2)
                        .setCarColor(javafx.scene.paint.Color.OLIVE)
                        .build(),
                new Car.Builder()
                        .setXPosition(2)
                        .setYPosition(0)
                        .setDirection(Direction.VERTICAL)
                        .setLength(2)
                        .setCarColor(javafx.scene.paint.Color.ORANGE)
                        .build(),
                new Car.Builder()
                        .setXPosition(3)
                        .setYPosition(0)
                        .setDirection(Direction.HORIZONTAL)
                        .setLength(2)
                        .setCarColor(javafx.scene.paint.Color.LIGHTBLUE)
                        .build(),
                new Car.Builder()
                        .setXPosition(3)
                        .setYPosition(1)
                        .setDirection(Direction.HORIZONTAL)
                        .setLength(2)
                        .setCarColor(javafx.scene.paint.Color.PINK)
                        .build(),
                new Car.Builder()
                        .setXPosition(0)
                        .setYPosition(2)
                        .setDirection(Direction.HORIZONTAL)
                        .setLength(2)
                        .setCarColor(javafx.scene.paint.Color.RED)
                        .build(),
                new Car.Builder()
                        .setXPosition(2)
                        .setYPosition(2)
                        .setDirection(Direction.VERTICAL)
                        .setLength(2)
                        .setCarColor(javafx.scene.paint.Color.PURPLE)
                        .build(),
                new Car.Builder()
                        .setXPosition(5)
                        .setYPosition(3)
                        .setDirection(Direction.VERTICAL)
                        .setLength(2)
                        .setCarColor(javafx.scene.paint.Color.GREEN)
                        .build(),
                new Car.Builder()
                        .setXPosition(0)
                        .setYPosition(4)
                        .setDirection(Direction.HORIZONTAL)
                        .setLength(3)
                        .setCarColor(javafx.scene.paint.Color.YELLOW)
                        .build(),
                new Car.Builder()
                        .setXPosition(3)
                        .setYPosition(4)
                        .setDirection(Direction.VERTICAL)
                        .setLength(2)
                        .setCarColor(javafx.scene.paint.Color.DARKBLUE)
                        .build(),
                new Car.Builder()
                        .setXPosition(4)
                        .setYPosition(4)
                        .setDirection(Direction.VERTICAL)
                        .setLength(2)
                        .setCarColor(javafx.scene.paint.Color.BROWN)
                        .build(),
                new Car.Builder()
                        .setXPosition(1)
                        .setYPosition(5)
                        .setDirection(Direction.HORIZONTAL)
                        .setLength(2)
                        .setCarColor(Color.LIGHTYELLOW)
                        .build()
        );

        highscoreTables.put(boards.size(), new HighscoreTable());
        Board board = new Board(boards.size(), 6, 6, cars);
        boards.add(board);
    }
}
