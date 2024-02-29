package org.example.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class BookDto {

    public long id;
    @NotNull(message = "У книги должно быть название!")
    @NotBlank(message = "У книги должно быть название!")
    public String name;
    @NotNull(message = "У книги должен быть автор!")
    @NotBlank(message = "У книги должен быть автор!")
    public String author;
    @NotNull(message = "У книги должен быть номер ISBN!")
    @NotBlank(message = "У книги должен быть номер ISBN!")
    public String isbn;

}
