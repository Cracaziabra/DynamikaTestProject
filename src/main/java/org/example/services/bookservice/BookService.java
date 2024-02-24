package org.example.services.bookservice;

import org.example.core.Book;
import org.example.dtos.BookDto;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();

    Book addBook(Book book);

}
