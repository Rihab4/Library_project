package com.codewithrihab.bibliotheque.entities;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookRequest {

    private String title;
    private String isbn;
    private LocalDate publicationDate;
    private Integer pageNumber;
    private Integer quantity;
    private Long authorId;
    private Long CategoryId;

}
