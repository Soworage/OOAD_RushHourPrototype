package application.model;

/* Hauptverantwortlicher: Erik Witte */


public interface BoardCreatorInterface {
    Board getBoard();

    HighscoreTable getHighscoreTable(int boardId);
}
