package it.epicode.biblioteca.tests;

import com.github.javafaker.Faker;
import it.epicode.biblioteca.dao.LibraryItemDAO;
import it.epicode.biblioteca.dao.UserDAO;
import it.epicode.biblioteca.entity.Borrow;
import it.epicode.biblioteca.entity.LibraryItem;
import it.epicode.biblioteca.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Locale;

public class MainGetElements {
    public static void main(String[] args) {
        Faker faker = new Faker(new Locale("it"));
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit-jpa");
        EntityManager em = emf.createEntityManager();

        LibraryItemDAO libraryItemDAO = new LibraryItemDAO(em);
        UserDAO userDAO = new UserDAO(em);

        System.out.println(libraryItemDAO.findByISBN(2L));

        LibraryItem found = libraryItemDAO.findByISBN(2L);

        System.out.println(found.getISBNCode() + " " + found.getTitle() + " " + found.getYear() + " ");

        User user = userDAO.findById(1L);

        System.out.println(user.getName() + " " + user.getSurname() + " " + user.getBirthDate());
        for (int i = 0; i < user.getBorrows().size(); i++) {
            Borrow borrow = user.getBorrows().get(i);
            System.out.println(borrow.getBorrowDate());
        }


    }
}