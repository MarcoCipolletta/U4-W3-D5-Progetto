package it.epicode.biblioteca.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
public class BorrowDAO {
    private EntityManager em;

    public void save(Borrow  oggetto) {
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