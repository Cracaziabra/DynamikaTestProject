package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.example.core.Book;
import org.example.dtos.BookDto;
import org.example.dtos.CreateBookDto;
import org.example.dtos.ExceptionDto;
import org.example.mappers.BookMapper;
import org.example.services.bookservice.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @Operation(
            summary = "Получение списка всех книг",
            description = "Получение списка всех книг"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema =
                    @Schema(implementation = BookDto.class), mediaType = "application/json") })
    })
    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(bookMapper.toDtoList(bookService.getAllBooks()));
    }

    @Operation(
            summary = "Добавление новой книги",
            description = "Добавляет новую книгу в систему"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema =
                    @Schema(implementation = BookDto.class), mediaType = "application/json") })
    })
    @PostMapping
    public ResponseEntity<BookDto> addBook(@Valid @RequestBody CreateBookDto bookDto) {
        Book addedBook = bookService.addBook(bookMapper.toEntity(bookDto));

        return ResponseEntity.status(HttpStatus.CREATED).body(bookMapper.toDto(addedBook));
    }

    @Operation(
            summary = "Изменение существующей книги",
            description = "Изменяет название, автора и норер ISBN на переданные. " +
                    "Если книга не найдена, возвращает ошибку"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema =
                    @Schema(implementation = BookDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = {
                    @Content(schema =
                    @Schema(implementation = ExceptionDto.class), mediaType = "application/json") })
    })
    @PutMapping(path = "/{id}")
    public ResponseEntity<BookDto> updateBook(@Valid @RequestBody CreateBookDto bookDto, @PathVariable long id) {
        Book updatedBook = bookService.updateBook(bookMapper.toEntity(bookDto), id);

        return ResponseEntity.ok(bookMapper.toDto(updatedBook));
    }
}
