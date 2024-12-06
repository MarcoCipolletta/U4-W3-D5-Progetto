package it.epicode.biblioteca.tests;

import com.github.javafaker.Faker;
import it.epicode.biblioteca.dao.LibraryItemDAO;
import it.epicode.biblioteca.entity.Book;
import it.epicode.biblioteca.entity.LibraryItem;
import it.epicode.biblioteca.entity.Magazine;
import it.epicode.biblioteca.enums.Periodicity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Locale;

public class MainInsert {
    public static void main(String[] args) {
        Faker faker = new Faker(new Locale("it"));
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit-jpa");
        EntityManager em = emf.createEntityManager();

        LibraryItemDAO libraryItemDAO = new LibraryItemDAO(em);

        for (int i = 0; i < 10; i++) {
            Book book = new Book();
            book.setAuthor(faker.book().author());
            book.setGenre(faker.book().genre());
            book.setTitle(faker.book().title());
            book.setPages(faker.number().numberBetween(100, 1000));
            book.setYear(faker.number().numberBetween(1960, 2022));

            libraryItemDAO.save(book);

            Magazine magazine = new Magazine();
            magazine.setTitle(faker.book().title());
            magazine.setYear(faker.number().numberBetween(1960, 2022));
            magazine.setPeriodicity(Periodicity.values()[faker.number().numberBetween(0, 2)]);
            magazine.setPages(faker.number().numberBetween(50, 120));

            libraryItemDAO.save(magazine);
        }


    }
}