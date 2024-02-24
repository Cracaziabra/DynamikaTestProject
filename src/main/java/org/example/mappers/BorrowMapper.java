package org.example.mappers;

import org.example.core.Borrow;
import org.example.dtos.BorrowDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BorrowMapper {

    List<BorrowDto> toDtoList(List<Borrow> borrows);

}
