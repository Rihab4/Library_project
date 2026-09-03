package com.codewithrihab.bibliotheque.Controllers;

import com.codewithrihab.bibliotheque.dtos.AuthorDto;
import com.codewithrihab.bibliotheque.entities.Author;
import com.codewithrihab.bibliotheque.entities.AuthorRequest;
import com.codewithrihab.bibliotheque.mappers.AuthorMapper;
import com.codewithrihab.bibliotheque.repositories.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/author")
@AllArgsConstructor
public class AuthorController {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @PostMapping("/addAuthor")
    public ResponseEntity<?> RegisterAuthor(@RequestBody AuthorRequest authorRequest,
                                            UriComponentsBuilder uriBuilder) {
        if (authorRepository.existsByFirstNameAndLastNameAndDateOfBirth(authorRequest.getFirstName(),
                authorRequest.getLastName(), authorRequest.getDateOfBirth())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("This author already exists");
        }

        var author = authorMapper.toEntity(authorRequest);
        authorRepository.save(author);
        var dto = authorMapper.toDto(author);
        var uri = uriBuilder
                .path("/api/v1/author/{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(dto);
    }

    @GetMapping("/getAll")
    public List<AuthorDto> getAllAuthors() {
        List<Author>  authors = authorRepository.findAll();
        return authors.stream().map(authorMapper::toDto).toList();
    }
}
