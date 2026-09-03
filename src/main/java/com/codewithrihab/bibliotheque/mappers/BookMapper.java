package com.codewithrihab.bibliotheque.mappers;

import com.codewithrihab.bibliotheque.dtos.BookDto;
import com.codewithrihab.bibliotheque.entities.Book;
import com.codewithrihab.bibliotheque.entities.BookRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDto toBookDto(Book book);
    Book toEntity(BookRequest bookDto);

}
