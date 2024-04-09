package org.example;

public class Main {
    public static void main(String[] args) {
        int numberOfPlayers = 4;
        int n = 7;

        Bag bag = new Bag(n);
        Player[] players = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            players[i] = new Player("Player " + (i + 1), bag);
            players[i].start();
        }
    }
}
