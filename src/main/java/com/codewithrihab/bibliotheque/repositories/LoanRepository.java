package com.codewithrihab.bibliotheque.repositories;

import com.codewithrihab.bibliotheque.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan,Long> {
    boolean existsByBookIdAndReturnDateIsNull(Long bookId);

}
