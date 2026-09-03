package com.codewithrihab.bibliotheque.entities;

import lombok.Data;

import java.time.LocalDate;
@Data
public class AuthorRequest {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
}
