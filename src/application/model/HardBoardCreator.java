package application.model;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Erzeugt Spielbretter und Highscore-Tabellen für den schwierigen Schwierigkeitsgrad.
 * <p>Hauptverantwortlicher:</p>
 * <ul>
 *     <li>Erik Witte</li>
 * </ul>
 * <p>Mitwirkend:</p>
 * <ul>
 *     <li>Alex Becker</li>
 * </ul>
 */
public class HardBoardCreator implements BoardCreatorInterface {

    private final List<Board> boards;
    private final Map<Integer, HighscoreTable> highscoreTables;

    /**
     * Konstruiert einen neuen HardBoardCreator und initialisiert die Liste der Bretter und Highscore-Tabellen.
     */
    public HardBoardCreator() {
        boards = new ArrayList<>();
        highscoreTables = new HashMap<>();
        populateBoards();
    }

    /**
     * Gibt das erste Board in der Liste zurück.
     * (Hinweis: In der endgültigen Version sollte ein Zufallszahlengenerator verwendet werden.)
     *
     * @return Eine Kopie des ersten Boards in der Liste.
     */
    @Override
    public Board getBoard() {
        // Implementierung eines Zufallszahlengenerators für das Endprodukt
        return boards.get(0).clone();
    }

    /**
     * Gibt die Highscore-Tabelle für das angegebene Brett-ID zurück.
     *
     * @param boardId Die ID des Brettes.
     * @return Die Highscore-Tabelle für das angegebene Brett.
     */
    @Override
    public HighscoreTable getHighscoreTable(int boardId) {
        return highscoreTables.get(boardId);
    }

    /**
     * Initialisiert die Liste der Bretter und die Highscore-Tabellen.
     * Erstellt ein Beispielbrett mit vordefinierten Autos und fügt es der Liste der Bretter hinzu.
     */
    private void populateBoards() {
        List<Car> cars = List.of(
                new Car.Builder()
                        .setXPosition(0)
                        .setYPosition(0)
                        .setDirection(Direction.VERTICAL)
                        .setLength(2)
                        .setCarColor(Color.OLIVE)
                        .build(),
                new Car.Builder()
                        .setXPosition(1)
                        .setYPosition(0)
                        .setDirection(Direction.HORIZONTAL)
                        .setLength(3)
                        .setCarColor(Color.YELLOW)
                        .build(),
                new Car.Builder()
                        .setXPosition(4)
                        .setYPosition(0)
                        .setDirection(Direction.VERTICAL)
                        .setLength(2)
                        .setCarColor(Color.ORANGE)
                        .build(),
                new Car.Builder()
                        .setXPosition(5)
                        .setYPosition(0)
                        .setDirection(Direction.VERTICAL)
                        .setLength(2)
                        .setCarColor(Color.LIGHTBLUE)
                        .build(),
                new Car.Builder()
                        .setXPosition(0)
                        .setYPosition(2)
                        .setDirection(Direction.HORIZONTAL)
                        .setLength(2)
                        .setCarColor(Color.RED)
                        .build(),
                new Car.Builder()
                        .setXPosition(2)
                        .setYPosition(1)
                        .setDirection(Direction.VERTICAL)
                        .setLength(2)
                        .setCarColor(Color.PINK)
                        .build(),
                new Car.Builder()
                        .setXPosition(3)
                        .setYPosition(2)
                        .setDirection(Direction.VERTICAL)
                        .setLength(3)
                        .setCarColor(Color.PURPLE)
                        .build(),
                new Car.Builder()
                        .setXPosition(2)
                        .setYPosition(3)
                        .setDirection(Direction.VERTICAL)
                        .setLength(2)
                        .setCarColor(Color.DARKBLUE)
                        .build(),
                new Car.Builder()
                        .setXPosition(4)
                        .setYPosition(3)
                        .setDirection(Direction.HORIZONTAL)
                        .setLength(2)
                        .setCarColor(Color.DARKGREEN)
                        .build(),
                new Car.Builder()
                        .setXPosition(0)
                        .setYPosition(4)
                        .setDirection(Direction.HORIZONTAL)
                        .setLength(2)
                        .setCarColor(Color.BLACK)
                        .build(),
                new Car.Builder()
                        .setXPosition(3)
                        .setYPosition(5)
                        .setDirection(Direction.HORIZONTAL)
                        .setLength(2)
                        .setCarColor(Color.DARKCYAN)
                        .build()
        );

        // Erstelle eine Highscore-Tabelle für das Board
        HighscoreTable highscoreTable = new HighscoreTable();
        highscoreTables.put(boards.size(), highscoreTable);
        Board board = new Board(boards.size(), 6, 6, cars);
        boards.add(board);
    }
}
