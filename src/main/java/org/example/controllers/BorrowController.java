package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.example.dtos.BorrowDto;
import org.example.dtos.CreateBorrowDto;
import org.example.dtos.ExceptionDto;
import org.example.mappers.BorrowMapper;
import org.example.services.borrowservice.BorrowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/borrows")
@RequiredArgsConstructor
public class BorrowController {

    private final BorrowService borrowService;
    private final BorrowMapper borrowMapper;

    @Operation(
            summary = "Получение списка всех кто взял книги",
            description = "Получение списка всех кто взял книги"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema =
                    @Schema(implementation = BorrowDto.class), mediaType = "application/json") })
    })
    @GetMapping
    public ResponseEntity<List<BorrowDto>> getAllBorrows() {
        return ResponseEntity.ok(borrowMapper.toDtoList(borrowService.getAllBorrows()));
    }

    @Operation(
            summary = "Выдача книги пользователю",
            description = "Фиксирует время, id пользователя и книги. " +
                    "Не дает одному пользователю взять больше одной одинаковой книги"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema =
                    @Schema(implementation = BorrowDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = {
                    @Content(schema =
                    @Schema(implementation = ExceptionDto.class), mediaType = "application/json") })
    })
    @PostMapping
    public ResponseEntity<BorrowDto> startBorrow(@Valid @RequestBody CreateBorrowDto createBorrowDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(borrowMapper.toDto(borrowService.startBorrow(createBorrowDto)));
    }

    @Operation(
            summary = "Возврат книги в библиотеку",
            description = "Удаляет связующую запись книги и пользователя после возврата. " +
                    "Если пользователь не брал такую книгу, выдает ошибку"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema =
                    @Schema(implementation = BorrowDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = {
                    @Content(schema =
                    @Schema(implementation = ExceptionDto.class), mediaType = "application/json") })
    })
    @DeleteMapping
    public ResponseEntity<ExceptionDto> closeBorrow(@Valid @RequestBody CreateBorrowDto createBorrowDto) {
        borrowService.closeBorrow(createBorrowDto);

        return ResponseEntity.noContent().build();
    }

}
