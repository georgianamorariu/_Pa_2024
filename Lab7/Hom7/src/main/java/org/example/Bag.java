package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bag {
    private List<Token> tokens;
    private int numberOfPlayers;
    private int currentPlayerIndex;

    public Bag(int n, int numberOfPlayers) {
        tokens = new ArrayList<>();
        this.numberOfPlayers = numberOfPlayers;
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                tokens.add(new Token(i, j));
            }
        }
        Collections.shuffle(tokens);
        currentPlayerIndex = 0;
    }

    public synchronized Token extractToken() {
        if (!isEmpty()) {
            return tokens.remove(0);
        }
        return null;
    }

    public boolean isEmpty() {
        return tokens.isEmpty();
    }

    public int getSize() {
        return tokens.size();
    }

    // Verificăm dacă este rândul jucătorului dat
    public synchronized boolean isPlayerTurn(String playerName) {
        return playerName.equals("Player " + (currentPlayerIndex + 1));
    }

    // Actualizăm indicele jucătorului pentru următorul rând
    public synchronized void updatePlayerTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % numberOfPlayers;
    }
}
