package it.epicode.biblioteca.tests;

import com.github.javafaker.Faker;
import it.epicode.biblioteca.dao.BookDAO;
import it.epicode.biblioteca.dao.BorrowDAO;
import it.epicode.biblioteca.dao.LibraryItemDAO;
import it.epicode.biblioteca.dao.UserDAO;
import it.epicode.biblioteca.entity.*;
import it.epicode.biblioteca.enums.Periodicity;
import it.epicode.biblioteca.exception.ISBNException;
import it.epicode.biblioteca.exception.InputException;
import it.epicode.biblioteca.exception.PeriodicityException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.*;

public class MainScanner {

    public static void addElemntByUser(Scanner scanner, LibraryItemDAO libraryItemDAO) throws ISBNException, PeriodicityException, InputException {
        System.out.println("Vuoi aggiungere un libro o una rivista? (1 = Libro, 2 = Rivista):");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice != 1 && choice != 2) {
            System.err.print("ERROR ");
            throw new InputException("Scelta non valida\n");
        }
        ;

        System.out.println("Inserisci il titolo:");
        String title = scanner.nextLine();

        System.out.println("Inserisci l'anno di pubblicazione:");
        int year = scanner.nextInt();


        System.out.println("Inserisci il numero di pagine:");
        int pages = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {

            System.out.println("Inserisci l'autore:");
            String author = scanner.nextLine();

            System.out.println("Inserisci il genere:");
            String genre = scanner.nextLine();

            Book book = new Book();
            book.setTitle(title);
            book.setYear(year);
            book.setPages(pages);
            book.setAuthor(author);
            book.setGenre(genre);

            libraryItemDAO.save(book);


            System.out.println("Libro aggiunto con successo! " + book);

        } else if (choice == 2) {

            System.out.println("Inserisci la periodicità (WEEKLY, MONTHLY, SEMIANNUAL).\n" +
                    " Scegli:\n1 per " + Periodicity.WEEKLY + "\n2 per " + Periodicity.MONTHLY + "\n3 per " + Periodicity.SEMIANNUALLY);
            int periodicityInput = scanner.nextInt();
            Periodicity periodicity = switch (periodicityInput) {
                case 1 -> Periodicity.WEEKLY;
                case 2 -> Periodicity.MONTHLY;
                case 3 -> Periodicity.SEMIANNUALLY;
                default -> {
                    System.err.print("ERROR ");
                    throw new PeriodicityException("Scelta non valida\n");
                }
            };

            Magazine magazine = new Magazine();
            magazine.setTitle(title);
            magazine.setYear(year);
            magazine.setPeriodicity(periodicity);
            magazine.setPages(pages);

            libraryItemDAO.save(magazine);
            System.out.println("Rivista aggiunta con successo! " + magazine);

        } else {
            System.out.println("Scelta non valida. Operazione annullata.");
        }
    }


    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit-jpa");
        EntityManager em = emf.createEntityManager();
        Scanner scanner = new Scanner(System.in);


        LibraryItemDAO libraryItemDAO = new LibraryItemDAO(em);
        UserDAO userDAO = new UserDAO(em);
        BorrowDAO borrowDAO = new BorrowDAO(em);
        BookDAO bookDAO = new BookDAO(em);


        while (true) {
            System.out.println("\nScegli un'opzione:");
            System.out.println("1. Aggiungi un catalogo nella biblioteca");
            System.out.println("2. Ricerca da codice ISBN");
            System.out.println("3. Rimuovi da codice ISBN");
            System.out.println("4. Ricerca per anno di pubblicazione");
            System.out.println("5. Ricerca per autore");
            System.out.println("6. Ricerca per titolo o parte di esso");
            System.out.println("7. Ricerca degli elementi attualmente in prestito per un utente");
            System.out.println("8. Ricerca dei prestiti attualmente attivi ma non ancora restituiti");
            System.out.println("9. Inserisci nuovo prestito");
            System.out.println("10. Restituisci un prestito");
            System.out.println("0. Esci");
            try {
                System.out.println("Inserisci la tua scelta:");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:

                            addElemntByUser(scanner, libraryItemDAO);

                        break;
                    case 2:
                            System.out.println("Inserisci l'ISBN del catalogo da cercare:");
                            System.out.println(libraryItemDAO.findByISBN(scanner.nextLong()));
                        break;
                    case 3:

                            System.out.println("Inserisci l'ISBN del catalogo da rimuovere:");
                            libraryItemDAO.deleteByISBN(scanner.nextLong());

                        break;
                    case 4:
                        System.out.println("Inserisci l'anno di pubblicazione:");

                        List<LibraryItem> catalogByYear = libraryItemDAO.findByYear(scanner.nextInt());
                        if (catalogByYear.isEmpty()) {
                            System.err.println("Nessun catalogo trovato per l'anno di pubblicazione inserito.\n");
                            break;
                        }
                        catalogByYear.forEach(catalog -> System.out.println(catalog));
                        System.out.println("\n");
                        break;
                    case 5:
                        System.out.println("Inserisci l'autore:");
                        List<Book> catalogByAuthor = bookDAO.findByAuthor(scanner.nextLine());
                        ;
                        if (catalogByAuthor.isEmpty()) {
                            System.err.println("Nessun catalogo trovato per l'autore inserito.\n");
                            break;
                        }
                        catalogByAuthor.forEach(catalog -> System.out.println(catalog));
                        System.out.println("\n");


                        break;
                    case 6:
                        System.out.println("Inserisci un titolo o parte di esso:");
                        List<LibraryItem> catalogByTitle = libraryItemDAO.findByTitleOrTitlePart(scanner.nextLine());
                        if (catalogByTitle.isEmpty()) {
                            System.err.println("Nessun catalogo trovato per il titolo inserito.\n");
                            break;
                        }
                        catalogByTitle.forEach(catalog -> System.out.println(catalog));
                        System.out.println("\n");

                        break;
                    case 7:
                        System.out.println("Inserisci il numero della carta di un utente per vedere i sui prestiti attivi");
                        List<Borrow> activeBorrows = borrowDAO.findActiveBorrowsByUserId(scanner.nextLong());
                        if (activeBorrows.isEmpty()) {
                            System.err.println("Nessun prestito trovato per l'utente inserito.\n");
                            Thread.sleep(500);
                            break;
                        }
                        activeBorrows.forEach(borrow -> System.out.println(borrow));
                        System.out.println("\n");
                        break;
                    case 8:
                        System.out.println("Prestiti scaduti ma non ancora restituiti:");
                        List<Borrow> expiredBorrows = borrowDAO.findByExpiredAndNotReturned();
                        if (expiredBorrows.isEmpty()) {
                            System.err.println("Nessun prestito scaduto trovato.\n");
                            break;
                        }
                        expiredBorrows.forEach(borrow -> System.out.println(borrow));
                        System.out.println("\n");
                        break;
                    case 9:
                        System.out.println("Inserisci il numero della carta del cliente");
                        Long userId = scanner.nextLong();
                        System.out.println("Inserisci l'ISBN del catalogo");
                        Long itemId = scanner.nextLong();
                        if (borrowDAO.isLibraryItemBorrowed(itemId)) {
                            System.err.println("Il catalogo è già in prestito.\n");
                            Thread.sleep(500);
                            break;
                        }
                        User user = userDAO.findById(userId);
                        LibraryItem item = libraryItemDAO.findByISBN(itemId);
                        Borrow borrow = new Borrow();
                        borrow.setUser(user);
                        borrow.setItem(item);
                        borrow.setBorrowDate(LocalDate.now());
                        borrow.setExpectedReturnDate(LocalDate.now().plusDays(30));
                        borrow.setReturnDate(null);
                        borrowDAO.save(borrow);
                        System.out.println("Prestito effettuato con successo.\n");
                        break;
                    case 10:
                        System.out.println("Inserisci l'ISBN del catalogo");
                        itemId = scanner.nextLong();
                        if (!borrowDAO.isLibraryItemBorrowed(itemId)) {
                            System.err.println("Il catalogo non è in prestito.\n");
                            Thread.sleep(500);
                            break;
                        }
                        borrow = borrowDAO.getBorrowWithLibraryItem(itemId);
                        borrow.setReturnDate(LocalDate.now());
                        borrowDAO.update(borrow);
                        System.out.println("Prestito restituito con successo.\n");
                        break;
                    case 0:
                        System.out.println("Uscita dal programma.");
                        return;
                    default:
                        System.out.println("Scelta non valida. Riprova.");

                }

            } catch (InputMismatchException e) {
                System.err.println("Scelta non valida. Riprova.");
                scanner.nextLine();
            } catch (ISBNException e) {
                System.err.println(e.getMessage());
            } catch (PeriodicityException e) {
                System.err.println(e.getMessage());
            } catch (InputException e) {
                System.err.println(e.getMessage());
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }



    }
}