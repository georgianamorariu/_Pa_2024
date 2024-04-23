package org.example;

import org.example.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        AuthorDAO authorDAO = new AuthorDAO();

        DatabaseManager databaseConnection = DatabaseManager.getInstance();
        try (Connection connection = databaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            Author author1 = new Author(0, "John Doe");
            Author author2 = new Author(0, "Jane Smith");
            authorDAO.addAuthor(author1);
            authorDAO.addAuthor(author2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

