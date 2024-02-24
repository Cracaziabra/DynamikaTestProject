package org.example.mappers;

import org.example.core.Book;
import org.example.dtos.BookDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    List<BookDto> toDtoList(List<Book> books);

    Book toEntity(BookDto dto);
    BookDto toDto(Book book);
}
