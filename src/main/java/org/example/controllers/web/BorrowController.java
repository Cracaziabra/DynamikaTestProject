package org.example.controllers.web;

import lombok.RequiredArgsConstructor;
import org.example.dtos.CreateBorrowDto;
import org.example.mappers.BookMapper;
import org.example.mappers.ClientMapper;
import org.example.services.bookservice.BookService;
import org.example.services.borrowservice.BorrowService;
import org.example.services.clientservice.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class BorrowController {

    private final BookService bookService;
    private final BookMapper bookMapper;
    private final ClientService clientService;
    private final ClientMapper clientMapper;
    private final BorrowService borrowService;

    @GetMapping("/borrow/{id}/books")
    public String getBorrowBooks(Model model, @PathVariable Long id) {
        model.addAttribute("clientId", id);
        model.addAttribute("books", bookMapper.toDtoList(bookService.getAllBooks()));
        return "borrow-books";
    }

    @GetMapping("/borrow/clients")
    public String getBorrowClients(Model model) {
        model.addAttribute("clients", clientMapper.toDtoList(clientService.getAllClients()));
        return "borrow-clients";
    }

    @GetMapping("/borrow/{clientId}/book/{bookId}")
    public String createBorrow(@PathVariable Long clientId, @PathVariable Long bookId) {
        borrowService.startBorrow(new CreateBorrowDto(bookId, clientId));
        return "borrow-created";
    }
}
