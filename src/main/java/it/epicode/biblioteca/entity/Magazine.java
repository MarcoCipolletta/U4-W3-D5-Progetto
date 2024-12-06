package it.epicode.biblioteca.entity;

import it.epicode.biblioteca.enums.Periodicity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@NamedQuery(name = "Trova_tutto_Magazine", query = "SELECT a FROM Magazine a")
public class Magazine extends LibraryItem {
    @Enumerated(EnumType.STRING)
    private Periodicity periodicity;

    @Override
    public String toString() {
        return "Rivista{" +
                "ISBNCode='" + getISBNCode() + '\'' +
                ", titolo='" + getTitle() + '\'' +
                ", anno='" + getYear() + '\'' +
                ", pagine='" + getPages() + '\'' +
                ", periodicità='" + periodicity + '\'' +
                '}';
    }
}
