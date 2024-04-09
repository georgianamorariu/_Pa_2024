package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandShell {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandShell(DocumentRepository repository) {
        commands.put("view", new ViewCommand());
        commands.put("report", new ReportCommand(repository));
        commands.put("export", new ExportCommand(repository));
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter command: ");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            String commandName = parts[0];
            String[] arguments = new String[parts.length - 1];
            System.arraycopy(parts, 1, arguments, 0, parts.length - 1);

            Command command = commands.get(commandName);
            if (command == null) {
                System.out.println("Invalid command.");
                continue;
            }

            try {
                command.execute(arguments);
            } catch (CommandException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
