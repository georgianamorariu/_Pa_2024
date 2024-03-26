package org.example;

import java.io.File;
import java.io.IOException;

// Main.java
public class Main {
    public static void main(String[] args) {

        String masterDirectory="repository";

        try {
            File directory = new File(masterDirectory);
            if (!directory.exists()) {
                if (directory.mkdir()) {
                    System.out.println("Directorul principal '" + masterDirectory + "' a fost creat.");
                } else {
                    throw new IOException("Eroare la crearea directorului principal '" + masterDirectory + "'.");
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            // Create a repository object
            DocumentRepository repository = new DocumentRepository("D:\\Georgiana\\PA2024\\_Pa_X_2024\\Lab5\\Lab5\\repository");

            // Display the content of the repository
            repository.displayRepositoryContent();
        } catch (RepositoryException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
