package application.model;

public interface BoardCreatorInterface {
    Board getBoard();

    HighscoreTable getHighscoreTable(int boardId);
}
