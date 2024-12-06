package it.epicode.biblioteca.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NamedQuery(name = "Trova_tutto_User", query = "SELECT a FROM User a")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="card_number")
    private Long cardNumber;
    private String name;
    private String surname;
    @Column(name="birth_date")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "user")
    private List<Borrow> borrows;

    @Override
    public String toString() {
        return "Utente{" +
                "cardNumber=" + cardNumber +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", borrows=" + borrows.size() +
                '}';
    }
}
