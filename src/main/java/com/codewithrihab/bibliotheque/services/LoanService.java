package com.codewithrihab.bibliotheque.services;

import com.codewithrihab.bibliotheque.entities.Book;
import com.codewithrihab.bibliotheque.entities.Loan;
import com.codewithrihab.bibliotheque.entities.Status;
import com.codewithrihab.bibliotheque.entities.User;
import com.codewithrihab.bibliotheque.repositories.BookRepository;
import com.codewithrihab.bibliotheque.repositories.LoanRepository;
import com.codewithrihab.bibliotheque.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public Loan borrowBook(Long userId, Long bookId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getQuantity() == null || book.getQuantity() <= 0) {
            throw new RuntimeException("Book is not available");
        }

        book.setQuantity(book.getQuantity() - 1);
        Loan loan = new Loan();

        loan.setUser(user);
        loan.setBook(book);

        LocalDate today = LocalDate.now();

        loan.setLoanDate(today);
        loan.setDueDate(today.plusDays(14));

        // New loan
        loan.setReturnDate(null);
        loan.setStatus(Status.BORROWED);

        return loanRepository.save(loan);
    }

    public Loan returnBook(Long loanId) {

        // Find the loan
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        // Check if already returned
        if (loan.getReturnDate() != null) {
            throw new RuntimeException("Book has already been returned");
        }

        loan.setReturnDate(LocalDate.now());

        loan.setStatus(Status.RETURNED);
        Book book = loan.getBook();
        book.setQuantity(book.getQuantity() + 1);

        return loanRepository.save(loan);
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }
}
