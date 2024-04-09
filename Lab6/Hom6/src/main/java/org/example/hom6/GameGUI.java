package org.example.hom6;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.Serializable;

public class GameGUI implements Serializable {
    private static final long serialVersionUID = 1L;

    private transient Stage primaryStage;
    private transient Board board;
    private int prevRow = -1;
    private int prevCol = -1;
    private boolean player1Turn = true;

    private Canvas canvas;
    private Button saveButton;
    private Button loadButton;

    public GameGUI(Stage primaryStage, Board board) {
        this.primaryStage = primaryStage;
        this.board = board;
        BorderPane root = new BorderPane();

        canvas = new Canvas(400, 400);
        saveButton = new Button("Save");
        loadButton = new Button("Load");

        HBox controlPanel = new HBox(10);
        controlPanel.setPadding(new Insets(10));
        controlPanel.getChildren().addAll(saveButton, loadButton);

        root.setCenter(canvas);
        root.setBottom(controlPanel);

        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public double getCellSize() {
        return canvas.getWidth() / board.getSize();
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public Button getLoadButton() {
        return loadButton;
    }

    public void drawBoard() {
        double cellSize = getCellSize();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Draw grid lines
        gc.setStroke(Color.BLACK);
        for (int i = 0; i <= board.getSize(); i++) {
            double pos = i * cellSize;
            gc.strokeLine(pos, 0, pos, canvas.getHeight()); // Vertical lines
            gc.strokeLine(0, pos, canvas.getWidth(), pos); // Horizontal lines
        }

        // Draw sticks
        gc.setStroke(Color.BLACK);
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                if (board.getSticks()[row][col]) {
                    double x1 = col * cellSize + cellSize / 2.0;
                    double y1 = row * cellSize + cellSize / 2.0;

                    // Check if stick is horizontal or vertical
                    if (row < board.getSize() - 1 && board.getSticks()[row + 1][col]) {
                        double x2 = x1;
                        double y2 = (row + 1) * cellSize + cellSize / 2.0;
                        gc.strokeLine(x1, y1, x2, y2);
                    }
                    if (col < board.getSize() - 1 && board.getSticks()[row][col + 1]) {
                        double x2 = (col + 1) * cellSize + cellSize / 2.0;
                        double y2 = y1;
                        gc.strokeLine(x1, y1, x2, y2);
                    }
                }
            }
        }

        // Draw stones
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                if (board.getStones()[row][col] != null) {
                    gc.setFill(board.getStones()[row][col].getColor());
                    gc.fillOval(col * cellSize, row * cellSize, cellSize, cellSize);
                }
            }
        }
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
