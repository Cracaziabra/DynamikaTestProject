package org.example.controllers.web;

import lombok.RequiredArgsConstructor;
import org.example.dtos.BookDto;
import org.example.exceptions.InvalidDataException;
import org.example.mappers.BookMapper;
import org.example.services.bookservice.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping("/library")
    public String getLibrary() {
        return "library";
    }

    @GetMapping("/books")
    public String getBooks(Model model) {
        model.addAttribute("books", bookMapper.toDtoList(bookService.getAllBooks()));
        return "books";
    }



    @GetMapping(value = {"/books/{id}", "/books/add"})
    public String singleBook(@PathVariable(required = false) Long id, Model model) {
        BookDto book = new BookDto();
        if (id != null) {
            book.setId(id);
        }
        model.addAttribute("selectedBook", book);
        return "change-book";
    }

    @PostMapping("/books/change")
    public String changeBook(@Valid @ModelAttribute("selectedBook") BookDto bookDto, BindingResult result) {
        if (result.hasErrors()) {
            throw new InvalidDataException(
                    result.getAllErrors()
                            .stream()
                            .map(ObjectError::getDefaultMessage)
                            .reduce((s, s2) -> s + " " + s2).orElse("Ошибка в обработке данных, обратитесь в службу поддержки сайта!")
            );
        }
        if (bookDto.getId() == 0L) {
            bookService.addBook(bookMapper.toEntity(bookDto));
        } else {
            bookService.updateBook(bookMapper.toEntity(bookDto), bookDto.getId());
        }
        return "redirect:/library";
    }
}
