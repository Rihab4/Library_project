package com.codewithrihab.bibliotheque.mappers;

import com.codewithrihab.bibliotheque.dtos.AuthorDto;
import com.codewithrihab.bibliotheque.entities.Author;
import com.codewithrihab.bibliotheque.entities.AuthorRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDto toDto(Author author);
    Author toEntity(AuthorRequest authorRequest);
}
