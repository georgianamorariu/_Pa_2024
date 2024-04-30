package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {
    private Connection connection;

    public AuthorDAO(Connection connection) {
        this.connection = connection;
    }

    public void addAuthor(Author author) throws SQLException {
        String query = "INSERT INTO authors (author_name, birth_date) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, author.getAuthorName());
            statement.setDate(2, Date.valueOf(author.getBirthDate()));
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                author.setAuthorId(rs.getInt(1));
            }
        }
    }

    public void updateAuthor(Author author) throws SQLException {
        String query = "UPDATE authors SET author_name = ?, birth_date = ? WHERE author_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, author.getAuthorName());
            statement.setDate(2, Date.valueOf(author.getBirthDate()));
            statement.setInt(3, author.getAuthorId());
            statement.executeUpdate();
        }
    }

    public void deleteAuthor(int authorId) throws SQLException {
        String query = "DELETE FROM authors WHERE author_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, authorId);
            statement.executeUpdate();
        }
    }

    public Author getAuthorById(int authorId) throws SQLException {
        String query = "SELECT * FROM authors WHERE author_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, authorId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String authorName = resultSet.getString("author_name");
                LocalDate birthDate = resultSet.getDate("birth_date").toLocalDate();
                return new Author(authorId, authorName, birthDate);
            }
        }
        return null;
    }

    public List<Author> getAllAuthors() throws SQLException {
        List<Author> authors = new ArrayList<>();
        String query = "SELECT * FROM authors";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int authorId = resultSet.getInt("author_id");
                String authorName = resultSet.getString("author_name");
                LocalDate birthDate = resultSet.getDate("birth_date").toLocalDate();
                authors.add(new Author(authorId, authorName, birthDate));
            }
        }
        return authors;
    }
}
