package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dtos.BorrowDto;
import org.example.mappers.BorrowMapper;
import org.example.services.borrowservice.BorrowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/borrows")
@RequiredArgsConstructor
public class BorrowController {

    private final BorrowService borrowService;
    private final BorrowMapper borrowMapper;

    @GetMapping
    public ResponseEntity<List<BorrowDto>> getAllBorrows() {
        return ResponseEntity.ok(borrowMapper.toDtoList(borrowService.getAllBorrows()));
    }

}
