package org.example.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class BookDto {

    private long id;
    private String name;
    private String author;
    private String isbn;

}
