package org.example.mappers;

import org.example.core.Book;
import org.example.dtos.BookDto;
import org.example.dtos.CreateBookDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    List<BookDto> toDtoList(List<Book> books);

    Book toEntity(CreateBookDto dto);

    Book toEntity(BookDto dto);
    BookDto toDto(Book book);
}
