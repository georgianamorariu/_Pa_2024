package org.example;

import java.io.File;
import java.io.IOException;

/**
 * Entry point for the application.
 */
public class Main {
    /**
     * Main method of the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        String masterDirectory = "repository";

        try {
            File directory = new File(masterDirectory);
            if (!directory.exists()) {
                if (directory.mkdir()) {
                    System.out.println("Main directory '" + masterDirectory + "' created.");
                } else {
                    throw new IOException("Error creating main directory '" + masterDirectory + "'.");
                }
            }
        } catch (IOException e) {
            System.err.println("Error creating main directory: " + e.getMessage());
            return; // Exiting the application if the main directory creation fails
        }

        try {
            DocumentRepository repository = new DocumentRepository(masterDirectory);
            CommandShell shell = new CommandShell(repository);
            shell.run();
        } catch (RepositoryException e) {
            System.err.println("Error initializing repository: " + e.getMessage());
        }
    }
}
