package application.controller;

import application.model.HighscoreEntry;
import application.model.HighscoreTable;
import application.model.UserStatistic;

import java.time.LocalDate;
/* Hauptverantwortlicher: Alex Becker */
/* Mitwirkend: Matthias Henzel */
public class InputNameController {

    private final UserStatistic userStatistic;
    private final Coordinator coordinator;
    private final HighscoreTable highscoreTable;

    public InputNameController(UserStatistic userStatistic, Coordinator coordinator, HighscoreTable highscoreTable) {
        this.userStatistic = userStatistic;
        this.coordinator = coordinator;
        this.highscoreTable = highscoreTable;
    }


    public void saveName(String name) {
        userStatistic.setName(name);
        LocalDate now = LocalDate.now();
        HighscoreEntry entry = new HighscoreEntry(
                userStatistic.getName(),
                userStatistic.getMoveCount(),
                now,
                String.valueOf(userStatistic.getSeconds())
        );
        highscoreTable.addEntry(entry);
        coordinator.showHighScoreMenu();
    }

    public void goBackToMainMenu() {
        coordinator.showMainMenu();
    }
}
