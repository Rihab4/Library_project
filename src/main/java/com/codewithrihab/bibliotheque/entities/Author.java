package com.codewithrihab.bibliotheque.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name= "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name= "date_of_birth")
    private LocalDate dateOfBirth;
    @OneToMany(mappedBy = "bookAuthor", cascade = CascadeType.ALL)
    private List<Book> books;
}
