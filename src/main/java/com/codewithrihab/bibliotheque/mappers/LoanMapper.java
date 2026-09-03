package com.codewithrihab.bibliotheque.mappers;

import com.codewithrihab.bibliotheque.dtos.LoanDto;
import com.codewithrihab.bibliotheque.entities.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "user.id", target = "userId")
    LoanDto toDto(Loan loan);
    Loan toEntity(LoanDto loanDto);
}
