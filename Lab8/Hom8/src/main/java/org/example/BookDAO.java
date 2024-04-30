package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private Connection connection;

    public BookDAO(Connection connection) {
        this.connection = connection;
    }

    public void addBook(Book book) throws SQLException {
        String query = "INSERT INTO books (title, language, publication_date, num_pages) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getLanguage());
            statement.setDate(3, Date.valueOf(book.getPublicationDate()));
            statement.setInt(4, book.getNumPages());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                book.setBookId(rs.getInt(1));
            }
        }
    }

    public void updateBook(Book book) throws SQLException {
        String query = "UPDATE books SET title = ?, language = ?, publication_date = ?, num_pages = ? WHERE book_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getLanguage());
            statement.setDate(3, Date.valueOf(book.getPublicationDate()));
            statement.setInt(4, book.getNumPages());
            statement.setInt(5, book.getBookId());
            statement.executeUpdate();
        }
    }

    public void deleteBook(int bookId) throws SQLException {
        String query = "DELETE FROM books WHERE book_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, bookId);
            statement.executeUpdate();
        }
    }

    public Book getBookById(int bookId) throws SQLException {
        String query = "SELECT * FROM books WHERE book_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, bookId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String title = resultSet.getString("title");
                String language = resultSet.getString("language");
                LocalDate publicationDate = resultSet.getDate("publication_date").toLocalDate();
                int numPages = resultSet.getInt("num_pages");
                return new Book(bookId, title, language, publicationDate, numPages);
            }
        }
        return null;
    }

    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int bookId = resultSet.getInt("book_id");
                String title = resultSet.getString("title");
                String language = resultSet.getString("language");
                LocalDate publicationDate = resultSet.getDate("publication_date").toLocalDate();
                int numPages = resultSet.getInt("num_pages");
                books.add(new Book(bookId, title, language, publicationDate, numPages));
            }
        }
        return books;
    }
}
