package org.example.services.bookservice;

import lombok.RequiredArgsConstructor;
import org.example.core.Book;
import org.example.dtos.BookDto;
import org.example.mappers.BookMapper;
import org.example.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.saveAndFlush(book);
    }

}
