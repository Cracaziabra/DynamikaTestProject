package org.example.exceptions;

public class BookWasntBorrowedException extends RuntimeException{

    public BookWasntBorrowedException() {
        super("Вы не брали эту книгу");
    }

}
