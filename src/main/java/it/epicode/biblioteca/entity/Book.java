package it.epicode.biblioteca.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@NamedQuery(name = "Trova_tutto_Book", query = "SELECT a FROM Book a")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Book extends LibraryItem{
 private String author;
 private String genre;


}
