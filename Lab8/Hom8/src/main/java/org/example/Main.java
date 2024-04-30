package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DatabaseManager.getInstance().getConnection();
            AuthorDAO authorDAO = new AuthorDAO(connection);
            BookDAO bookDAO = new BookDAO(connection);
            BookAuthorDAO bookAuthorDAO = new BookAuthorDAO(connection);

            System.out.println("Testăm operațiile pentru Author:");
            Author author1 = new Author(0, "Marin Unu", LocalDate.of(1980, 5, 15));
            authorDAO.addAuthor(author1);
            System.out.println("Autor adăugat: " + author1);
            Author author2 = authorDAO.getAuthorById(author1.getAuthorId());
            System.out.println("Autor obținut după ID: " + author2);
            author2.setAuthorName("Marin Doi");
            authorDAO.updateAuthor(author2);
            System.out.println("Autor actualizat: " + author2);
            authorDAO.deleteAuthor(author1.getAuthorId());
            System.out.println("Autor șters cu succes.");

            System.out.println("\nTestăm operațiile pentru Book:");
            Book book1 = new Book(0, "DZ Programming", "English", LocalDate.of(2022, 1, 1), 500);
            bookDAO.addBook(book1);
            System.out.println("Carte adăugată: " + book1);
            Book book2 = bookDAO.getBookById(book1.getBookId());
            System.out.println("Carte obținută după ID: " + book2);
            book2.setTitle("FZ Programming");
            bookDAO.updateBook(book2);
            System.out.println("Carte actualizată: " + book2);
            bookDAO.deleteBook(book1.getBookId());
            System.out.println("Carte ștearsă cu succes.");

            System.out.println("\nTestăm operațiile pentru BookAuthor:");
            BookAuthor bookAuthor = new BookAuthor(11, 6); // asociem prima carte cu primul autor
            bookAuthorDAO.addBookAuthor(bookAuthor);
            System.out.println("Asociere carte-autor adăugată: " + bookAuthor);
            List<Integer> authorIds = bookAuthorDAO.getAuthorIdsByBookId(1);
            System.out.println("ID-urile autorilor pentru cartea cu ID-ul 11: " + authorIds);
            List<Integer> bookIds = bookAuthorDAO.getBookIdsByAuthorId(1);
            System.out.println("ID-urile cărților pentru autorul cu ID-ul 6: " + bookIds);

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
