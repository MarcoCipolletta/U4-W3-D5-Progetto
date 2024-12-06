package it.epicode.biblioteca.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@NamedQuery(name = "Trova_tutto_Borrow", query = "SELECT a FROM Borrow a")
@NamedQuery(name = "Trova_active_borrow_by_library_item", query = "SELECT a FROM Borrow a WHERE a.item.ISBNCode = :itemId AND a.returnDate IS NULL")
@NamedQuery(name="Trova_borrows_active_by_user_id", query = "SELECT a FROM Borrow a WHERE a.user.cardNumber = :id AND a.returnDate IS NULL")
@NamedQuery(name= "Trova_expired_borrows_but_not_returned", query = "SELECT a FROM Borrow a WHERE a.returnDate IS NULL AND a.expectedReturnDate < :date")

public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "library_item_id")
    private LibraryItem item;
    @Column(name="borrow_date")
    private LocalDate borrowDate;
    @Column(name="expected_return_date")
    private LocalDate expectedReturnDate;
    @Column(name="return_date")
    private LocalDate returnDate;


    @Override
    public String toString() {
        return "Prestito{" +
                "id=" + id +
                ", user=" + user.getName() +
                ", item=" + item.getTitle() +
                ", data prestito=" + borrowDate +
                ", data riconsegna prevista=" + expectedReturnDate +
                ", data riconsegna=" + (returnDate == null? "Ancora da consegnare" : returnDate) +
                "}";
    }
}
