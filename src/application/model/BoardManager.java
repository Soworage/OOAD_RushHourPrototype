package application.model;


public class BoardManager {

    private final EasyBoardCreator easyBoardCreator;
    private final MediumBoardCreator mediumBoardCreator;
    private final HardBoardCreator hardBoardCreator;

    public BoardManager() {
        easyBoardCreator = new EasyBoardCreator();
        mediumBoardCreator = new MediumBoardCreator();
        hardBoardCreator = new HardBoardCreator();
    }


    public HighscoreTable getHighScoreTableForBoard(int boardID, Difficulty difficulty) {
        System.out.println("Got a request for Board id " + boardID + "and diff " + difficulty);
        return switch (difficulty) {
            case EASY -> easyBoardCreator.getHighscoreTable(boardID);
            case MEDIUM -> mediumBoardCreator.getHighscoreTable(boardID);
            case HARD -> hardBoardCreator.getHighscoreTable(boardID);
        };
    }

    public Board giveBoardToDifficulty(Difficulty difficulty) {
        switch (difficulty) {
            case EASY -> {
                return easyBoardCreator.getBoard();
            }
            case MEDIUM -> {
                return mediumBoardCreator.getBoard();
            }
            case HARD -> {
                return hardBoardCreator.getBoard();
            }
        }
        return null;
    }
}
