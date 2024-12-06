package it.epicode.biblioteca.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@NamedQuery(name = "Trova_tutto_LibraryItem", query = "SELECT a FROM LibraryItem a")
@NamedQuery(name = "Trova_per_anno_LibraryItem", query = "SELECT a FROM LibraryItem a WHERE a.year = :anno")
public abstract class LibraryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long ISBNCode;
    private String title;
    private int year;
    private int pages;

    @OneToMany(mappedBy = "item")
    private List<Borrow> borrows;


}

