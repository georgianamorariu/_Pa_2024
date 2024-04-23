package org.example;

public class TimeKeeper extends Thread {
    private final int timeLimitInSeconds;

    public TimeKeeper(int timeLimitInSeconds) {
        this.timeLimitInSeconds = timeLimitInSeconds;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(timeLimitInSeconds * 100000L); // Convertim limitele de timp în milisecunde
            System.out.println("Time's up! Game over!");
            System.exit(0); // Oprim jocul în cazul depășirii limitelor de timp
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
