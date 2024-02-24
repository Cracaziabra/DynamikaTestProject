package org.example.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientDto {

    private long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private LocalDate birthday;

}
