package org.example;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Represents a command for viewing a document.
 */
public class ViewCommand implements Command {
    /**
     * Executes the view command with the given arguments.
     *
     * @param args the command arguments
     * @throws CommandException if an error occurs during execution
     */
    @Override
    public void execute(String[] args) throws CommandException {
        if (args.length != 1) {
            throw new CommandException("Invalid arguments. Usage: view <documentName>");
        }

        String documentName = args[0];
        File documentFile = new File(documentName);
        if (!documentFile.exists() || !documentFile.isFile()) {
            throw new CommandException("Document '" + documentName + "' does not exist.");
        }

        try {
            Desktop.getDesktop().open(documentFile);
        } catch (IOException e) {
            throw new CommandException("Error opening document: " + e.getMessage());
        }
    }
}
