package com.codewithrihab.bibliotheque.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(nullable = false, unique = true)
    private String isbn;
    @Column(name= "publication_date")
    private LocalDate publicationDate;
    @Column(name= "page_number")
    private Integer pageNumber;
    @Column(name = "quantity")
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author bookAuthor;
    @ManyToOne
    @JoinColumn(name= "category_id")
    private Category category;
    @OneToMany(mappedBy = "book")
    private List<Loan> loans;

}
