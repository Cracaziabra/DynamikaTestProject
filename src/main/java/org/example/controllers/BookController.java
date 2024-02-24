package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.core.Book;
import org.example.dtos.BookDto;
import org.example.mappers.BookMapper;
import org.example.services.bookservice.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(bookMapper.toDtoList(bookService.getAllBooks()));
    }

    @PostMapping
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto) {
        Book addedBook = bookService.addBook(bookMapper.toEntity(bookDto));

        return ResponseEntity.status(HttpStatus.CREATED).body(bookMapper.toDto(addedBook));
    }
}
