package org.example;

import java.io.File;

public class DocumentRepository {
    private final File masterDirectory;

    // Constructor
    public DocumentRepository(String masterDirectoryPath) throws RepositoryException {
        this.masterDirectory = new File(masterDirectoryPath);

        // Check if master directory exists
        if (!this.masterDirectory.exists() || !this.masterDirectory.isDirectory()) {
            throw new RepositoryException("Master directory does not exist.");
        }
    }

    // Display the content of the repository (without the content of the files)
    public void displayRepositoryContent() {
        File[] personDirectories = masterDirectory.listFiles(File::isDirectory);
        if (personDirectories != null) {
            for (File personDir : personDirectories) {
                System.out.println("Person: " + personDir.getName());
                File[] documents = personDir.listFiles();
                if (documents != null) {
                    for (File doc : documents) {
                        System.out.println("  Document: " + doc.getName());
                    }
                }
            }
        }
    }
}
