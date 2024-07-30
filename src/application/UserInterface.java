package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.Parent;

import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.io.IOException;


public class UserInterface {
    private Stage primaryStage;
    private BoardManager boardManager;
    private static final int GRID_SIZE = 6;
    private static final int RECT_SIZE = 40;


    public UserInterface(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

   /* private void initializeUi(){
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
    	try {
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
*//*        BorderPane root = new BorderPane();
        GridPane grid = new GridPane();


        // Fülle das GridPane mit Rechtecken
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                Rectangle rect = new Rectangle(RECT_SIZE, RECT_SIZE);
                rect.setFill(Color.WHITE);
                rect.setStroke(Color.BLACK);
                // Setze die Aktion bei einem Klick auf das Quadrat

                grid.add(rect, col, row);
            }
        }

        root.setCenter(grid);*//*

        primaryStage.show();
    }*/

    /**
     * Lädt und zeigt die entsprechende Szene basierend auf dem MenuType.
     */
    public void showMenu(MenuType menuType) {
        String fxmlFile = "";

        switch (menuType) {
            case MenuType.MAIN_MENU:
                fxmlFile = "mainMenu.fxml";
                break;
            case MenuType.GAME_MENU:
                fxmlFile = "gameMenu.fxml";
                break;
            case MenuType.DIFFICULTY_MENU:
                fxmlFile = "difficultyMenu.fxml";
                break;
            case MenuType.COLORSCHEME_MENU:
                fxmlFile = "colorSchemeMenu.fxml";
                break;
            default:
                throw new IllegalArgumentException("Unbekannter Menütyp: " + menuType);
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            //setze ui in controller, damit der controller das menü wechseln kann
            if(loader.getController() instanceof mainMenuController){
                ((mainMenuController) loader.getController()).setUserInterface(this);
            }

            if(loader.getController() instanceof  diffSelectController){
                ((diffSelectController) loader.getController()).setUserInterface(this);
            }

            if(loader.getController() instanceof colorSchemeController){
                ((colorSchemeController) loader.getController()).setUserInterface(this);
            }

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
