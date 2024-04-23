package org.example;

import java.util.*;

public class Player extends Thread {
    private String name;
    private List<Token> tokens;
    private List<Token> sequence;
    private Bag bag;
    private int points;

    public Player(String name, Bag bag) {
        this.name = name;
        this.bag = bag;
        this.tokens = new ArrayList<>();
        this.sequence = new ArrayList<>();
        this.points = 0;
    }

    @Override
    public void run() {
        while (!bag.isEmpty()) {
            synchronized (bag) {
                if (!bag.isEmpty()) {
                    Token token = bag.extractToken();
                    tokens.add(token);
                    System.out.println(name + " extracted token: " + token);
                }
            }
            checkSequence();
        }
        //System.out.println(name + " has finished. Points: " + points);
    }

    private void checkSequence() {
        for (Token token : tokens) {
            if (sequence.isEmpty() || token.getFirst() == sequence.get(sequence.size() - 1).getSecond()) {
                sequence.add(token);
            } else {
                updatePoints();
                sequence.clear();
                sequence.add(token);
            }
            if (sequence.size() == bag.getSize()) {
                updatePoints();
                sequence.clear();
            }
        }
    }

    private void updatePoints() {
        points = Math.max(points, sequence.size());
    }
}