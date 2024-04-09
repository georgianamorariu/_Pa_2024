package org.example;

public interface Command {
    void execute(String[] args) throws CommandException;
}
