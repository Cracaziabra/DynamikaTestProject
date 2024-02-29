package org.example.dtos;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class ClientDto {

    public long id;
    @NotNull(message = "Введите имя пользователя")
    @NotBlank(message = "Введите имя пользователя")
    public String firstName;
    public String lastName;
    public String patronymic;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate birthday;

}
