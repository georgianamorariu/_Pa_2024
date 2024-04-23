package org.example;

public class Main {
    public static void main(String[] args) {
        int numberOfPlayers = 4;
        int n = 5;
        int timeLimitInSeconds = 60; // Limita de timp pentru joc în secunde

        Bag bag = new Bag(n, numberOfPlayers);
        Player[] players = new Player[numberOfPlayers];
        Player winner = null; // Variabilă pentru a ține jucătorul câștigător

        for (int i = 0; i < numberOfPlayers; i++) {
            players[i] = new Player("Player " + (i + 1), bag);
            players[i].start();
        }

        // Crearea și pornirea firului de execuție pentru cronometru
        TimeKeeper timeKeeper = new TimeKeeper(timeLimitInSeconds);
        timeKeeper.setDaemon(true); // Setarea firului de execuție ca fiind daemon
        timeKeeper.start();

        // Așteptăm ca toți jucătorii să termine jocul
        for (Player player : players) {
            try {
                player.join();
                // Actualizăm câștigătorul dacă este necesar
                if (winner == null || player.getPoints() > winner.getPoints()) {
                    winner = player;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Afisăm jucătorul câștigător și punctajul său
        System.out.println("Winner: " + winner.getPlayerName() + ", Points: " + winner.getPoints());
    }
}
