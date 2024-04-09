package org.example.hom6;

import java.io.Serializable;
import java.util.Random;

public class Board implements Serializable {
    private static final long serialVersionUID = 1L;

    private int size;
    private boolean[][] sticks;
    private StoneColor[][] stones;

    public Board(int size) {
        this.size = size;
        sticks = new boolean[size][size];
        stones = new StoneColor[size][size];
    }

    public void generateRandomSticks() {
        Random random = new Random();
        for (int i = 0; i < size * size; i++) {
            int row = random.nextInt(size);
            int col = random.nextInt(size);
            sticks[row][col] = true;
        }
    }

    public boolean isValidMove(int row, int col) {
        // Check if the selected node is within the board boundaries
        if (row < 0 || row >= size || col < 0 || col >= size)
            return false;

        // Check if the selected node has already been occupied by a stone
        if (stones[row][col] != null)
            return false;

        // Check if the selected node is connected by a stick
        if (!sticks[row][col])
            return false;

        return true; // Move is valid
    }


    public void placeStone(int row, int col, StoneColor color) {
        stones[row][col] = color;
    }

    public boolean isGameOver(int row, int col) {
        // Implement game over condition
        return false;
    }

    public int getSize() {
        return size;
    }

    public boolean[][] getSticks() {
        return sticks;
    }

    public StoneColor[][] getStones() {
        return stones;
    }
}
