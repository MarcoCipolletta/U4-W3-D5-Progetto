package it.epicode.biblioteca.entity;

import it.epicode.biblioteca.exception.ISBNException;
import it.epicode.biblioteca.exception.InputException;
import it.epicode.biblioteca.exception.PeriodicityException;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Scanner;

@Data
@Entity
@NamedQuery(name = "Trova_tutto", query = "SELECT a FROM LibraryItem a")
@NamedQuery(name = "Trova_per_anno", query = "SELECT a FROM LibraryItem a WHERE a.year = :anno")
@NamedQuery(name = "Trova_per_titolo", query = "SELECT a FROM LibraryItem a WHERE LOWER(a.title) LIKE LOWER(:title)")
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

