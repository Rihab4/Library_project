package com.codewithrihab.bibliotheque.entities;

import lombok.Data;

@Data
public class LoanRequest {
    private Long bookId;
    private Long userId;
}
