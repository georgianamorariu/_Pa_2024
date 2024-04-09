package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Represents a command for exporting persons from a repository to a file.
 */
public class ExportCommand implements Command {
    private final DocumentRepository repository;

    /**
     * Constructs an ExportCommand with the specified repository.
     *
     * @param repository the document repository
     */
    public ExportCommand(DocumentRepository repository) {
        this.repository = repository;
    }

    /**
     * Executes the export command with the given arguments.
     *
     * @param args the command arguments
     * @throws CommandException if an error occurs during execution
     */
    @Override
    public void execute(String[] args) throws CommandException {
        if (args.length != 1) {
            throw new CommandException("Invalid arguments. Usage: export <outputFilePath>");
        }

        String outputFilePath = args[0];
        File outputFile = new File(outputFilePath);

        try {
            List<Person> persons = repository.getAllPersons();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(outputFile, persons);
        } catch (IOException e) {
            throw new CommandException("Error exporting repository: " + e.getMessage());
        }
    }
}
