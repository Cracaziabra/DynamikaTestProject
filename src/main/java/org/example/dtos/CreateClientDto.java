package org.example.dtos;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class CreateClientDto {

    @NotNull(message = "Введите имя пользователя")
    @NotBlank(message = "Введите имя пользователя")
    private String firstName;
    private String lastName;
    private String patronymic;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

}
