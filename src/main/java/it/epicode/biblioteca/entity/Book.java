package it.epicode.biblioteca.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@NamedQuery(name = "Trova_tutto_Book", query = "SELECT a FROM Book a")
@NamedQuery(name = "Trova_per_autore", query = "SELECT a FROM Book a WHERE LOWER(a.author) = LOWER(:author)")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Book extends LibraryItem {
    private String author;
    private String genre;

    @Override
    public String toString() {
        return "Libro{" +
                "ISBNCode='" + getISBNCode() + '\'' +
                ", titolo='" + getTitle() + '\'' +
                ", anno='" + getYear() + '\'' +
                ", pagine='" + getPages() + '\'' +
                ", autore='" + author + '\'' +
                ", genere='" + genre + '\'' +
                '}';
    }
}
