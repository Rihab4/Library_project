package com.codewithrihab.bibliotheque.Controllers;

import com.codewithrihab.bibliotheque.dtos.BookDto;
import com.codewithrihab.bibliotheque.entities.Book;
import com.codewithrihab.bibliotheque.entities.BookRequest;
import com.codewithrihab.bibliotheque.mappers.BookMapper;
import com.codewithrihab.bibliotheque.repositories.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor
public class BookController {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;


    @PostMapping("/add")
    public ResponseEntity<?> save(
            @RequestBody BookRequest book,
            UriComponentsBuilder uriBuilder) {

        if (book.getIsbn() == null || book.getIsbn().isBlank()) {
            return ResponseEntity
                    .badRequest()
                    .body("ISBN is required");
        }

        if (bookRepository.findByIsbn(book.getIsbn()) != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("A book with this ISBN already exists");
        }

        Book saved = bookMapper.toEntity(book);
        bookRepository.save(saved);

        BookDto bookDto = bookMapper.toBookDto(saved);

        var uri = uriBuilder
                .path("/api/v1/books/{id}")
                .buildAndExpand(bookDto.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(bookDto);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> getBookById(@PathVariable Long bookId) {
        if(bookRepository.findById(bookId).isPresent()) {
            return ResponseEntity.ok(bookMapper.toBookDto(bookRepository.findById(bookId).get()));
        }
        return ResponseEntity.notFound().build();
    }

}

