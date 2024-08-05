package application.view;

import application.controller.InitializableController;
import application.model.MenuType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Verwaltet das Wechseln zwischen verschiedenen Szenen (Ansichten) innerhalb der Anwendung.
 * <p>Hauptverantwortlicher:</p>
 * <ul>
 *     <li>Matthias Henzel</li>
 * </ul>
 */
public class SceneManager {

    private final Stage primaryStage;
    private InitializableController currentController;

    /**
     * Konstruktor für die SceneManager-Klasse.
     *
     * @param primaryStage Die Hauptbühne (Stage), auf der die Szenen angezeigt werden.
     */
    public SceneManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Setzt den aktuellen Controller, der die Logik für die Szene bereitstellt.
     *
     * @param controller Der Controller, der für die aktuelle Szene zuständig ist.
     */
    public void setController(InitializableController controller) {
        this.currentController = controller;
    }

    /**
     * Zeigt das Menü entsprechend dem angegebenen Menütyp an.
     * Lädt die entsprechende FXML-Datei und setzt die Szene der Hauptbühne.
     *
     * @param menuType Der Typ des Menüs, das angezeigt werden soll.
     */
    public void showMenu(MenuType menuType) {
        String fxmlFile = switch (menuType) {
            case MAIN_MENU -> "mainMenu.fxml";
            case GAME_MENU -> "gameMenu.fxml";
            case DIFFICULTY_MENU -> "difficultyMenu.fxml";
            case COLORSCHEME_MENU -> "colorSchemeMenu.fxml";
            case STATISTICS_MENU -> "statisticsMenu.fxml";
            case HIGHSCORE_MENU -> "highScoreTable.fxml";
            case INPUTNAME_MENU -> "inputNameField.fxml";
            case RESETPIN_MENU -> "pinMenu.fxml";
        };

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            loader.setController(currentController); // Setzt den Controller für die FXML-Datei
            Parent root = loader.load(); // Lädt die FXML-Datei
            Scene scene = new Scene(root);

            primaryStage.setScene(scene); // Setzt die neue Szene auf die Hauptbühne
            primaryStage.show(); // Zeigt die Hauptbühne an
        } catch (IOException e) {
            e.printStackTrace(); // Protokolliert Fehler beim Laden der FXML-Datei
        }
    }
}
