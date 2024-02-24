package org.example.services.bookservice;

import org.example.core.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();

    Book addBook(Book book);

    Book updateBook(Book book, long id);
}
