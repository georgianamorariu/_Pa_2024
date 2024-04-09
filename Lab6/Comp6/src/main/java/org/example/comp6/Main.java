package org.example.comp6;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    private int gridSize = 8; // Default grid size
    private Canvas canvas;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // Configuration Panel
        VBox configPanel = new VBox(10);
        configPanel.setPadding(new Insets(10));
        Label gridSizeLabel = new Label("Grid Size:");
        TextField gridSizeField = new TextField(Integer.toString(gridSize));
        Button newGameButton = new Button("New Game");
        newGameButton.setOnAction(e -> {
            gridSize = Integer.parseInt(gridSizeField.getText());
            drawBoard(canvas, gridSize);
        });
        configPanel.getChildren().addAll(gridSizeLabel, gridSizeField, newGameButton);
        root.setTop(configPanel);

        // Drawing Panel (Canvas)
        canvas = new Canvas(400, 400);
        drawBoard(canvas, gridSize);
        root.setCenter(canvas);

        // Control Panel
        HBox controlPanel = new HBox(10);
        controlPanel.setPadding(new Insets(10));
        controlPanel.setAlignment(Pos.CENTER);
        Button loadButton = new Button("Load");
        Button saveButton = new Button("Save");
        Button exitButton = new Button("Exit");
        controlPanel.getChildren().addAll(loadButton, saveButton, exitButton);
        root.setBottom(controlPanel);

        Scene scene = new Scene(root, 600, 600);
        primaryStage.setTitle("Positional Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to draw the grid on the canvas
    private void drawBoard(Canvas canvas, int gridSize) {
        double width = canvas.getWidth();
        double height = canvas.getHeight();
        double cellSize = Math.min(width, height) / gridSize;

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, width, height);

        // Draw grid lines
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        for (int i = 0; i <= gridSize; i++) {
            double x = i * cellSize;
            double y = i * cellSize;
            gc.strokeLine(0, y, width, y); // Horizontal lines
            gc.strokeLine(x, 0, x, height); // Vertical lines
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
