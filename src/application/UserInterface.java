package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.paint.Color;


public class UserInterface {
    private Stage primaryStage;
    private static final int GRID_SIZE = 6;
    private static final int RECT_SIZE = 40;


    public UserInterface(Stage primaryStage) {
        this.primaryStage = primaryStage;
        initializeUi();
    }

    private void initializeUi(){
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root,400,400);
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

        root.setCenter(grid);

        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void pressOnMenuStartButton(){

    }
}
