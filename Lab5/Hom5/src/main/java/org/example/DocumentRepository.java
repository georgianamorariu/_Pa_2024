package org.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages a repository of documents associated with persons.
 */
public class DocumentRepository {
    private final File masterDirectory;
    private static final String DEFAULT_DOCUMENT_ID = "ID";

    /**
     * Constructs a DocumentRepository with the specified master directory path.
     *
     * @param masterDirectoryPath the path to the master directory
     * @throws RepositoryException if the master directory does not exist
     */
    public DocumentRepository(String masterDirectoryPath) throws RepositoryException {
        this.masterDirectory = new File(masterDirectoryPath);

        if (!this.masterDirectory.exists() || !this.masterDirectory.isDirectory()) {
            throw new RepositoryException("Master directory does not exist.");
        }
    }

    /**
     * Retrieves all persons from the repository.
     *
     * @return a list of persons
     */
    public List<Person> getAllPersons() {
        List<Person> persons = new ArrayList<>();
        File[] personDirectories = masterDirectory.listFiles(File::isDirectory);
        if (personDirectories != null) {
            for (File personDir : personDirectories) {
                persons.add(new Person(personDir.getName(), DEFAULT_DOCUMENT_ID));
            }
        }
        return persons;
    }

    /**
     * Displays the content of the repository.
     */
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
