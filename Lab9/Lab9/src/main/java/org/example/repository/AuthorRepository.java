package org.example.repository;


import org.example.model.Author;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AuthorRepository {
    private final EntityManager entityManager;

    public AuthorRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(Author author) {
        entityManager.getTransaction().begin();
        entityManager.persist(author);
        entityManager.getTransaction().commit();
    }

    public Author findById(int id) {
        return entityManager.find(Author.class, id);
    }

    public List<Author> findByName(String namePattern) {
        TypedQuery<Author> query = entityManager.createQuery(
                "SELECT a FROM Author a WHERE a.authorName LIKE :namePattern", Author.class);
        query.setParameter("namePattern", "%" + namePattern + "%");
        return query.getResultList();
    }
}
