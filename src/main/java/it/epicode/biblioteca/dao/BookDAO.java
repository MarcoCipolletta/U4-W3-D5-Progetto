package it.epicode.biblioteca.dao;

import it.epicode.biblioteca.entity.Book;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class BookDAO {
    private EntityManager em;

    public void save(Book oggetto) {
        em.getTransaction().begin();
        em.persist(oggetto);
        em.getTransaction().commit();
    }

    public Book findById(Long id) {
        return em.find(Book.class, id);
    }

    public List<Book> findAll() {
        return em.createNamedQuery("Trova_tutto_Book", Book.class).getResultList();
    }

    public List<Book> findByAuthor(String author) {
        return em.createNamedQuery("Trova_per_autore", Book.class).setParameter("author", author).getResultList();
    }

    public void update(Book oggetto) {
        em.getTransaction().begin();
        em.merge(oggetto);
        em.getTransaction().commit();
    }

    public void delete(Book oggetto) {
        em.getTransaction().begin();
        em.remove(oggetto);
        em.getTransaction().commit();
    }


}