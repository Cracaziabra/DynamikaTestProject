package org.example.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BorrowInfoDto {

    private String firstName;
    private String lastName;
    private String patronymic;
    private LocalDate birthday;
    private String bookName;
    private String author;
    private String isbn;
    private LocalDateTime startTime;

}
