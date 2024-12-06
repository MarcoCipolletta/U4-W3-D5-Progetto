package it.epicode.biblioteca.tests;

import com.github.javafaker.Faker;
import it.epicode.biblioteca.dao.BorrowDAO;
import it.epicode.biblioteca.dao.LibraryItemDAO;
import it.epicode.biblioteca.dao.UserDAO;
import it.epicode.biblioteca.entity.*;
import it.epicode.biblioteca.enums.Periodicity;
import it.epicode.biblioteca.exception.ISBNException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainInsert {
    public static void main(String[] args) {
        Faker faker = new Faker(new Locale("it"));
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit-jpa");
        EntityManager em = emf.createEntityManager();

        LibraryItemDAO libraryItemDAO = new LibraryItemDAO(em);
        BorrowDAO borrowDAO = new BorrowDAO(em);


        for (int i = 0; i < 100; i++) {
            Book book = new Book();
            book.setAuthor(faker.book().author());
            book.setGenre(faker.book().genre());
            book.setTitle(faker.book().title());
            book.setPages(faker.number().numberBetween(100, 1000));
            book.setYear(faker.number().numberBetween(1960, 2022));
            book.setBorrows(new ArrayList<>());

            libraryItemDAO.save(book);

            Magazine magazine = new Magazine();
            magazine.setTitle(faker.book().title());
            magazine.setYear(faker.number().numberBetween(1960, 2022));
            magazine.setPeriodicity(Periodicity.values()[faker.number().numberBetween(0, 2)]);
            magazine.setPages(faker.number().numberBetween(50, 120));
            magazine.setBorrows(new ArrayList<>());


            libraryItemDAO.save(magazine);
        }

        UserDAO userDAO = new UserDAO(em);
        for (int i = 0; i < 25; i++) {
            User user = new User();
            user.setName(faker.name().firstName());
            user.setSurname(faker.name().lastName());
            LocalDate date = faker.date().birthday().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            ;
            user.setBirthDate(date);
            user.setBorrows(new ArrayList<>());

            userDAO.save(user);

        }

        LibraryItem elemento;
        LibraryItem elemento2;
        LibraryItem elemento3;
        LibraryItem elemento4;
        LibraryItem elemento5;
        LibraryItem elemento6;
        LibraryItem elemento7;
        LibraryItem elemento8;
        try {
            elemento = libraryItemDAO.findByISBN(1L);
            elemento2 = libraryItemDAO.findByISBN(6L);
            elemento3 = libraryItemDAO.findByISBN(34L);
            elemento4 = libraryItemDAO.findByISBN(45L);
            elemento5 = libraryItemDAO.findByISBN(76L);
            elemento6 = libraryItemDAO.findByISBN(57L);
            elemento7 = libraryItemDAO.findByISBN(18L);
            elemento8 = libraryItemDAO.findByISBN(9L);
        } catch (ISBNException e) {
            throw new RuntimeException(e);
        }

        User user = userDAO.findById(1L);
        User user2 = userDAO.findById(7L);
        User user3 = userDAO.findById(13L);
        User user4 = userDAO.findById(24L);
        User user5 = userDAO.findById(15L);


        Borrow borrow = new Borrow();
        borrow.setUser(user);
        borrow.setItem(elemento);
        LocalDate borrowDate = faker.date().between(
                        Date.from(LocalDate.of(2023, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                        Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant())
                ).toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        borrow.setBorrowDate(borrowDate);
        borrow.setExpectedReturnDate(borrowDate.plusDays(30));
        borrow.setReturnDate(null);

        Borrow borrow2 = new Borrow();
        borrow2.setUser(user2);
        borrow2.setItem(elemento2);
        LocalDate borrowDate2 = faker.date().between(
                        Date.from(LocalDate.of(2023, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                        Date.from(LocalDate.now().minusDays(33).atStartOfDay(ZoneId.systemDefault()).toInstant())
                ).toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        borrow2.setBorrowDate(borrowDate2);
        borrow2.setExpectedReturnDate(borrowDate2.plusDays(30));
        borrow2.setReturnDate(borrowDate2.plusDays(10));

        Borrow borrow3 = new Borrow();
        borrow3.setUser(user);
        borrow3.setItem(elemento3);
        LocalDate borrowDate3 = faker.date().between(
                        Date.from(LocalDate.of(2023, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                        Date.from(LocalDate.now().minusDays(33).atStartOfDay(ZoneId.systemDefault()).toInstant())
                ).toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        borrow3.setBorrowDate(borrowDate3);
        borrow3.setExpectedReturnDate(borrowDate3.plusDays(30));
        borrow3.setReturnDate(borrowDate3.plusDays(28));

        Borrow borrow4 = new Borrow();
        borrow4.setUser(user3);
        borrow4.setItem(elemento4);
        LocalDate borrowDate4 = faker.date().between(
                        Date.from(LocalDate.of(2023, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                        Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant())
                ).toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        borrow4.setBorrowDate(borrowDate4);
        borrow4.setExpectedReturnDate(borrowDate4.plusDays(30));
        borrow4.setReturnDate(null);

        Borrow borrow5 = new Borrow();
        borrow5.setUser(user4);
        borrow5.setItem(elemento5);
        LocalDate borrowDate5 = faker.date().between(
                        Date.from(LocalDate.of(2023, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                        Date.from(LocalDate.now().minusDays(33).atStartOfDay(ZoneId.systemDefault()).toInstant())
                ).toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        borrow5.setBorrowDate(borrowDate5);
        borrow5.setExpectedReturnDate(borrowDate5.plusDays(30));
        borrow5.setReturnDate(borrowDate5.plusDays(28));

        Borrow borrow6 = new Borrow();
        borrow6.setUser(user5);
        borrow6.setItem(elemento6);
        LocalDate borrowDate6 = faker.date().between(
                        Date.from(LocalDate.now().minusDays(18).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                        Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant())
                ).toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        borrow6.setBorrowDate(borrowDate6);
        borrow6.setExpectedReturnDate(borrowDate6.plusDays(30));
        borrow6.setReturnDate(null);

        Borrow borrow7 = new Borrow();
        borrow7.setUser(user);
        borrow7.setItem(elemento7);
        LocalDate borrowDate7 = faker.date().between(
                        Date.from(LocalDate.of(2023, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                        Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant())
                ).toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        borrow7.setBorrowDate(borrowDate7);
        borrow7.setExpectedReturnDate(borrowDate7.plusDays(30));
        borrow7.setReturnDate(null);

        Borrow borrow8 = new Borrow();
        borrow8.setUser(user2);
        borrow8.setItem(elemento8);
        LocalDate borrowDate8 = faker.date().between(
                        Date.from(LocalDate.of(2023, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                        Date.from(LocalDate.now().minusDays(33).atStartOfDay(ZoneId.systemDefault()).toInstant())
                ).toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        borrow8.setBorrowDate(borrowDate8);
        borrow8.setExpectedReturnDate(borrowDate8.plusDays(30));
        borrow8.setReturnDate(borrowDate8.plusDays(10));


        borrowDAO.save(borrow);
        borrowDAO.save(borrow2);
        borrowDAO.save(borrow3);
        borrowDAO.save(borrow4);
        borrowDAO.save(borrow5);
        borrowDAO.save(borrow6);
        borrowDAO.save(borrow7);
        borrowDAO.save(borrow8);
        user.getBorrows().add(borrow);
        user.getBorrows().add(borrow3);
        user.getBorrows().add(borrow7);
        user2.getBorrows().add(borrow2);
        user2.getBorrows().add(borrow8);
        user3.getBorrows().add(borrow4);
        user4.getBorrows().add(borrow5);
        user5.getBorrows().add(borrow6);
        userDAO.update(user);
        userDAO.update(user2);
        userDAO.update(user3);
        userDAO.update(user4);
        userDAO.update(user5);
        elemento.getBorrows().add(borrow);
        elemento2.getBorrows().add(borrow2);
        elemento3.getBorrows().add(borrow3);
        elemento4.getBorrows().add(borrow4);
        elemento5.getBorrows().add(borrow5);
        elemento6.getBorrows().add(borrow6);
        elemento7.getBorrows().add(borrow7);
        elemento8.getBorrows().add(borrow8);
        libraryItemDAO.update(elemento);
        libraryItemDAO.update(elemento2);
        libraryItemDAO.update(elemento3);
        libraryItemDAO.update(elemento4);
        libraryItemDAO.update(elemento5);
        libraryItemDAO.update(elemento6);
        libraryItemDAO.update(elemento7);
        libraryItemDAO.update(elemento8);

        em.close();
        emf.close();


    }
}