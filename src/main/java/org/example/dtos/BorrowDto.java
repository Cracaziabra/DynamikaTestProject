package org.example.dtos;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
public class BorrowDto {

    private long id;
    private long bookId;
    private long clientId;
    private LocalDateTime startTime;

}
