package org.example.services.bookservice;

import org.example.core.Book;
import org.example.exceptions.BookNotFoundException;
import org.example.repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookServiceImplTest {

    @MockBean
    private BookRepository bookRepository;
    @Autowired
    private BookServiceImpl bookService;
    private Book testBook = new Book();
    private String word = "test";

    @BeforeEach
    public void before() {
        testBook.setName(word);
        testBook.setAuthor(word);
        testBook.setIsbn(word);
    }

    @Test
    void updateNonExistingBook() {
        when(bookRepository.existsById(any())).thenReturn(false);
        assertThrows(BookNotFoundException.class, () -> bookService.updateBook(testBook, 1L));
    }

    @Test
    void updateExistingBook() {
        when(bookRepository.existsById(any())).thenReturn(true);
        when(bookRepository.saveAndFlush(any())).thenReturn(testBook);
        assertThat(bookService.updateBook(testBook, 1L)).isEqualTo(testBook);
    }
}