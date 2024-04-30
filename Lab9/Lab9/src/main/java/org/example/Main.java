package org.example;

import org.example.model.Author;
import org.example.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws ParseException {
        EntityManagerFactory entityManagerFactory = EntityManagerFactoryProvider.getEntityManagerFactory();

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Author author = new Author("Razvan Hai", new SimpleDateFormat("yyyy-MM-dd").parse("1980-01-01"));
        Book book = new Book("Selling houses", "English", new Date(), 300);

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(author);
        entityManager.persist(book);

        // Terminăm tranzacția
        transaction.commit();

        // Închidem EntityManager
        entityManager.close();

        // Închidem EntityManagerFactory
        entityManagerFactory.close();
    }
}
