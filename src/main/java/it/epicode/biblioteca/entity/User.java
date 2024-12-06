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
    private Long cardNumber;
    private String name;
    private String surname;
    private LocalDate birthDate;

    @OneToMany(mappedBy = "user")
    private List<Borrow> borrows;


}
