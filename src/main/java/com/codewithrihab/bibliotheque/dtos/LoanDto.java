package com.codewithrihab.bibliotheque.dtos;

import com.codewithrihab.bibliotheque.entities.Status;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LoanDto {

    private LocalDate loanDate;
    private LocalDate returnDate;
    private LocalDate dueDate;
    private Status status;
    private Long bookId;
    private Long userId;
}
