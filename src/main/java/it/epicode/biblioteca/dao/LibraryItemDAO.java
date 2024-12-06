package it.epicode.biblioteca.dao;

import it.epicode.biblioteca.entity.LibraryItem;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class LibraryItemDAO {
    private EntityManager em;

    public void save(LibraryItem oggetto) {
        em.getTransaction().begin();
        em.persist(oggetto);
        em.getTransaction().commit();
    }

    public LibraryItem findByISBN(Long id) {
        return em.find(LibraryItem.class, id);
    }

    public List<LibraryItem> findByYear(int year) {
        return em.createNamedQuery("Trova_per_anno_LibraryItem", LibraryItem.class).setParameter("anno", year).getResultList();
    }

    public List<LibraryItem> findAll() {
        return em.createNamedQuery("Trova_tutto_LibraryItem", LibraryItem.class).getResultList();
    }

    public void update(LibraryItem oggetto) {
        em.getTransaction().begin();
        em.merge(oggetto);
        em.getTransaction().commit();
    }

    public void delete(LibraryItem oggetto) {
        em.getTransaction().begin();
        em.remove(oggetto);
        em.getTransaction().commit();
    }


}