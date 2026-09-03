package com.codewithrihab.bibliotheque.Controllers;

import com.codewithrihab.bibliotheque.dtos.LoanDto;
import com.codewithrihab.bibliotheque.entities.Loan;
import com.codewithrihab.bibliotheque.entities.LoanRequest;
import com.codewithrihab.bibliotheque.mappers.LoanMapper;
import com.codewithrihab.bibliotheque.services.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loan")
@AllArgsConstructor
public class LoanController {

    private final LoanService loanService;
    private final LoanMapper loanMapper;

    @PostMapping("/borrow")
    public ResponseEntity<LoanDto> borrowBook(
            @RequestBody LoanRequest request
    ) {
        Loan loan = loanService.borrowBook(
                request.getUserId(),
                request.getBookId()
        );
        LoanDto dto = loanMapper.toDto(loan);


        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{loanId}/return")
    public ResponseEntity<LoanDto> returnBook(
            @PathVariable Long loanId
    ) {
        Loan loan = loanService.returnBook(loanId);
        LoanDto dto = loanMapper.toDto(loan);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/getAllLoans")
    public ResponseEntity<?>  getAllLoans() {

        List<LoanDto> loans = loanService.getAllLoans().stream().map(loanMapper::toDto).toList();
        return ResponseEntity.ok(loans);
    }
}
