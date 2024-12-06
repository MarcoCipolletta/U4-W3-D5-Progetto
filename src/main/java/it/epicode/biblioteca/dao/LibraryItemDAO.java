package it.epicode.biblioteca.dao;

import it.epicode.biblioteca.entity.LibraryItem;
import it.epicode.biblioteca.exception.ISBNException;
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

    public LibraryItem findByISBN(Long id) throws ISBNException {
        LibraryItem found = em.find(LibraryItem.class, id);
        if (found == null) {
            throw new ISBNException("Libro non trovato");
        }
        return found;
    }


    public List<LibraryItem> findAll() {
        return em.createNamedQuery("Trova_tutto", LibraryItem.class).getResultList();
    }

    public List<LibraryItem> findByYear(int year) {
        return em.createNamedQuery("Trova_per_anno", LibraryItem.class).setParameter("anno", year).getResultList();
    }

    public List<LibraryItem> findByTitleOrTitlePart(String titleOrTitlePart) {
        return em.createNamedQuery("Trova_per_titolo", LibraryItem.class).setParameter("title", "%" + titleOrTitlePart + "%").getResultList();
    };

    public void update(LibraryItem oggetto) {
        em.getTransaction().begin();
        em.merge(oggetto);
        em.getTransaction().commit();
    }

    public void deleteByISBN(Long id) throws ISBNException {
        LibraryItem object = findByISBN(id);

        em.getTransaction().begin();
        em.remove(object);
        em.getTransaction().commit();
    }


}