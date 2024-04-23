package org.example;

import java.util.ArrayList;
import java.util.List;

public class Player extends Thread {
    private String playerName;
    private List<Token> tokens;
    private List<Token> sequence;
    private Bag bag;
    private int points;

    public Player(String name, Bag bag) {
        this.playerName = name;
        this.bag = bag;
        this.tokens = new ArrayList<>();
        this.sequence = new ArrayList<>();
        this.points = 0;
    }

    @Override
    public void run() {
        while (!bag.isEmpty()) {
            synchronized (bag) {
                try {
                    // Așteptăm până când este rândul acestui jucător
                    while (!bag.isPlayerTurn(playerName)) {
                        bag.wait();
                    }
                    if (!bag.isEmpty()) {
                        Token token = bag.extractToken();
                        tokens.add(token);
                        System.out.println(playerName + " extracted token: " + token);
                    }
                    bag.updatePlayerTurn(); // Trecem la următorul jucător
                    bag.notifyAll(); // Notificăm toți jucătorii că este rândul altui jucător
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // Verificăm dacă putem forma secvențe folosind jetoanele din lista jucătorului
            formSequences();
        }
        // La finalul jocului, determinăm câștigătorul și afișăm punctajul
        System.out.println(playerName + " has finished. Points: " + points);
    }

    // Metoda pentru a forma secvențe folosind jetoanele din lista jucătorului
    private void formSequences() {
        // Iterăm invers peste jetoanele adăugate recent
        for (int i = tokens.size() - 1; i >= 0; i--) {
            Token currentToken = tokens.get(i);
            if (sequence.isEmpty() || currentToken.getFirst() == sequence.get(sequence.size() - 1).getSecond()) {
                sequence.add(currentToken);
            } else {
                updatePoints();
                sequence.clear();
                sequence.add(currentToken);
            }
            if (sequence.size() == bag.getSize()) {
                updatePoints();
                sequence.clear();
            }
        }
    }

    // Metoda pentru a actualiza punctajul jucătorului
    private void updatePoints() {
        points = Math.max(points, sequence.size());
    }

    // Metoda pentru a obține punctajul jucătorului
    public int getPoints() {
        return points;
    }

    // Metoda pentru a obține numele jucătorului
    public String getPlayerName() {
        return playerName;
    }
}
