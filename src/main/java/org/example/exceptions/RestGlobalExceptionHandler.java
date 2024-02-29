package org.example.exceptions;

import org.example.dtos.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestGlobalExceptionHandler {

    @ExceptionHandler({
        BookAlreadyBorrowedException.class,
        BookWasntBorrowedException.class,
        BookNotFoundException.class,
        ClientNotFoundException.class
    })
    public ResponseEntity<ExceptionDto> handleRuntimeException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionDto(e.getMessage()));
    }

    @ExceptionHandler({
            MethodArgumentNotValidException.class
    })
    public ResponseEntity<ExceptionDto> validationExceptions(MethodArgumentNotValidException e) {
        String errorMessage = "Ошибка валидации, проверьте введенные данные";
        if (e.hasFieldErrors()) {
            errorMessage = e.getFieldError().getDefaultMessage();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionDto(errorMessage));
    }
}
