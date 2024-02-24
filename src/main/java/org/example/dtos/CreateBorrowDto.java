package org.example.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateBorrowDto {

    @NotNull(message = "Введите id книги, которую хотите взять")
    @NotBlank(message = "Введите id книги, которую хотите взять")
    private long bookId;
    @NotNull(message = "Введите ваш id")
    @NotBlank(message = "Введите ваш id")
    private long clientId;

}
