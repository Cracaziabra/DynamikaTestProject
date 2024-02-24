package org.example.mappers;

import org.example.core.Client;
import org.example.dtos.ClientDto;
import org.example.dtos.CreateClientDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")

public interface ClientMapper {

    List<ClientDto> toDtoList(List<Client> clients);

    Client toEntity(CreateClientDto dto);

    ClientDto toDto(Client client);

}
