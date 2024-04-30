package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookAuthorDAO {
    private Connection connection;

    public BookAuthorDAO(Connection connection) {
        this.connection = connection;
    }

    public void addBookAuthor(BookAuthor bookAuthor) throws SQLException {
        String query = "INSERT INTO book_authors (book_id, author_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, bookAuthor.getBookId());
            statement.setInt(2, bookAuthor.getAuthorId());
            statement.executeUpdate();
        }
    }

    public void deleteBookAuthor(int bookId, int authorId) throws SQLException {
        String query = "DELETE FROM book_authors WHERE book_id = ? AND author_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, bookId);
            statement.setInt(2, authorId);
            statement.executeUpdate();
        }
    }

    public List<Integer> getAuthorIdsByBookId(int bookId) throws SQLException {
        List<Integer> authorIds = new ArrayList<>();
        String query = "SELECT author_id FROM book_authors WHERE book_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, bookId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                authorIds.add(resultSet.getInt("author_id"));
            }
        }
        return authorIds;
    }

    public List<Integer> getBookIdsByAuthorId(int authorId) throws SQLException {
        List<Integer> bookIds = new ArrayList<>();
        String query = "SELECT book_id FROM book_authors WHERE author_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, authorId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                bookIds.add(resultSet.getInt("book_id"));
            }
        }
        return bookIds;
    }
}
