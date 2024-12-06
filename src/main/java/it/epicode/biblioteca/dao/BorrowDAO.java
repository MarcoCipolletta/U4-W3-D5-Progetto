package it.epicode.biblioteca.dao;

import it.epicode.biblioteca.entity.Borrow;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
public class BorrowDAO {
    private EntityManager em;

    public void save(Borrow oggetto) {
        em.getTransaction().begin();
        em.persist(oggetto);
        em.getTransaction().commit();
    }

    public Borrow findById(Long id) {
        return em.find(Borrow.class, id);
    }

    public List<Borrow> findAll() {
        return em.createNamedQuery("Trova_tutto_Borrow", Borrow.class).getResultList();
    }

    public List<Borrow> findActiveBorrowsByUserId(Long userId) {
        return em.createNamedQuery("Trova_borrows_active_by_user_id", Borrow.class).setParameter("id", userId).getResultList();
    }

    public List<Borrow> findByExpiredAndNotReturned() {
        return em.createNamedQuery("Trova_expired_borrows_but_not_returned", Borrow.class).setParameter("date", LocalDate.now()).getResultList();
    }
    public Borrow getBorrowWithLibraryItem(Long id) {
        return em.createNamedQuery("Trova_active_borrow_by_library_item", Borrow.class).setParameter("itemId", id).getSingleResult();
    }
    public boolean isLibraryItemBorrowed(Long itemId) {
        List<Borrow> activeBorrows = em.createNamedQuery("Trova_active_borrow_by_library_item", Borrow.class)
                .setParameter("itemId", itemId)
                .getResultList();
        return !activeBorrows.isEmpty();
    }

    public void update(Borrow oggetto) {
        em.getTransaction().begin();
        em.merge(oggetto);
        em.getTransaction().commit();
    }

    public void delete(Borrow oggetto) {
        em.getTransaction().begin();
        em.remove(oggetto);
        em.getTransaction().commit();
    }


}