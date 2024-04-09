package org.example.hom6;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;

public class PositionalGame extends Application implements Serializable {
    private static final long serialVersionUID = 1L;

    private int gridSize = 8; // Default grid size
    private boolean player1Turn = true; // Player 1 starts
    private transient Board board;
    private transient GameGUI gameGUI;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        board = new Board(gridSize);
        gameGUI = new GameGUI(primaryStage, board);

        // Initialize the game by generating random sticks
        board.generateRandomSticks();

        gameGUI.drawBoard();

        // Set mouse click event for placing stones
        gameGUI.getCanvas().setOnMouseClicked(event -> {
            double x = event.getX();
            double y = event.getY();
            int row = (int) (y / gameGUI.getCellSize());
            int col = (int) (x / gameGUI.getCellSize());

            if (board.isValidMove(row, col)) {
                board.placeStone(row, col, player1Turn ? StoneColor.RED : StoneColor.BLUE);
                gameGUI.drawBoard();
                if (board.isGameOver(row, col)) {
                    announceWinner();
                } else {
                    player1Turn = !player1Turn;
                }
            } else {
                gameGUI.showAlert("Invalid Move", "Please select a valid node.");
            }
        });

        // Save button event
        gameGUI.getSaveButton().setOnAction(event -> saveGame());

        // Load button event
        gameGUI.getLoadButton().setOnAction(event -> loadGame());

        primaryStage.setTitle("Positional Game");
        primaryStage.show();
    }

    private void announceWinner() {
        String winner = player1Turn ? "Player 2" : "Player 1";
        gameGUI.showAlert("Game Over", "Winner: " + winner);
    }

    private void saveGame() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("game.ser"))) {
            oos.writeObject(this);
            gameGUI.showAlert("Game Saved", "Game state has been saved.");
        } catch (IOException e) {
            gameGUI.showAlert("Error", "Failed to save the game.");
        }
    }

    private void loadGame() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("game.ser"))) {
            PositionalGame loadedGame = (PositionalGame) ois.readObject();
            this.gridSize = loadedGame.gridSize;
            this.board = loadedGame.board;
            this.player1Turn = loadedGame.player1Turn;
            gameGUI.setBoard(board);
            gameGUI.drawBoard();
            gameGUI.showAlert("Game Loaded", "Game state has been loaded.");
        } catch (IOException | ClassNotFoundException e) {
            gameGUI.showAlert("Error", "Failed to load the game.");
        }
    }
}
