package org.example.dtos;

import lombok.Data;

@Data
public class BookDto {

    private long id;
    private String name;
    private String author;
    private String isbn;

}
