package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.example.core.Client;
import org.example.dtos.ClientDto;
import org.example.dtos.CreateClientDto;
import org.example.dtos.ExceptionDto;
import org.example.mappers.ClientMapper;
import org.example.services.clientservice.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @Operation(
            summary = "Получение списка всех пользователей",
            description = "Получение списка всех пользователей"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema =
                    @Schema(implementation = ClientDto.class), mediaType = "application/json") })
    })
    @GetMapping
    public ResponseEntity<List<ClientDto>> getAllClients() {
        return ResponseEntity.ok(clientMapper.toDtoList(clientService.getAllClients()));
    }

    @Operation(
            summary = "Добавление нового пользователя",
            description = "Добавляет нового пользователя в систему"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema =
                    @Schema(implementation = ClientDto.class), mediaType = "application/json") })
    })
    @PostMapping
    public ResponseEntity<ClientDto> addClient(@Valid @RequestBody CreateClientDto clientDto) {
        Client addedClient = clientService.addClient(clientMapper.toEntity(clientDto));

        return ResponseEntity.status(HttpStatus.CREATED).body(clientMapper.toDto(addedClient));
    }

    @Operation(
            summary = "Обновляет данные пользователя",
            description = "Обновляет ФИО и дату рождения пользователя. " +
                    "Если пользователь не найден, возвращает ошибку"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema =
                    @Schema(implementation = ClientDto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = {
                    @Content(schema =
                    @Schema(implementation = ExceptionDto.class), mediaType = "application/json") })
    })
    @PutMapping(path = "/{id}")
    public ResponseEntity<ClientDto> updateClient(@Valid @RequestBody CreateClientDto clientDto, @PathVariable long id) {
        Client updatedClient = clientService.updateClient(clientMapper.toEntity(clientDto), id);

        return ResponseEntity.ok(clientMapper.toDto(updatedClient));
    }

}
