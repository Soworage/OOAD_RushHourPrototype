package application.model;


import java.util.HashMap;
import java.util.Map;

/* Hauptverantwortlicher: Erik Witte */
/* Mitwirkend: Alex Becker, Alex Mihel */

public class BoardManager {

    private final Map<Difficulty, BoardCreatorInterface> boardCreators;

    public BoardManager() {
        boardCreators = new HashMap<>();
        boardCreators.put(Difficulty.EASY, new EasyBoardCreator());
        boardCreators.put(Difficulty.HARD, new HardBoardCreator());
        boardCreators.put(Difficulty.MEDIUM, new MediumBoardCreator());
    }


    public HighscoreTable getHighScoreTableForBoard(int boardID, Difficulty difficulty) {
        System.out.println("Got a request for Board id " + boardID + "and diff " + difficulty);
        return boardCreators.get(difficulty).getHighscoreTable(boardID);
    }

    public Board giveBoardToDifficulty(Difficulty difficulty) {
        return boardCreators.get(difficulty).getBoard();
    }

}
