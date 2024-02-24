package org.example.exceptions;

public class BookAlreadyBorrowedException extends RuntimeException {

    public BookAlreadyBorrowedException() {
        super("Вы уже взяли эту книгу");
    }

}
