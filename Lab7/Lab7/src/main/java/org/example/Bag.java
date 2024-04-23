package org.example;

import java.util.*;

public class Bag {
    private List<Token> tokens;

    public Bag(int n) {
        tokens = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                tokens.add(new Token(i, j));
            }
        }
        Collections.shuffle(tokens);
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
}