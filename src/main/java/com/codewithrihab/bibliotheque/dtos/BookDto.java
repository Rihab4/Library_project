package com.codewithrihab.bibliotheque.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookDto {
 private Long id;
 private String title;
 private String isbn;
 private Integer quantity;
}
