package org.example.services.borrowservice;

import org.example.dtos.CreateBorrowDto;
import org.example.exceptions.BookAlreadyBorrowedException;
import org.example.exceptions.BookWasntBorrowedException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class BorrowServiceImplTest {

    private static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:14-alpine");

    @Autowired
    private BorrowServiceImpl borrowService;
    private long testId = 2L;

    @DynamicPropertySource
    static void setProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

    @BeforeAll
    static void beforeAll() {
        container.start();
    }

    @Test
    void startExistedBorrow() {
        CreateBorrowDto existedDto = new CreateBorrowDto();
        existedDto.setBookId(1L);
        existedDto.setClientId(2L);
        assertThrows(BookAlreadyBorrowedException.class, () -> borrowService.startBorrow(existedDto));
    }

    @Test
    void startNewBorrow() {
        CreateBorrowDto testDto = new CreateBorrowDto();
        testDto.setClientId(testId);
        testDto.setBookId(testId);
        assertThat(borrowService.startBorrow(testDto)).hasFieldOrPropertyWithValue("bookId", testId);
    }

    @Test
    void closeNonExistingBorrow() {
        CreateBorrowDto nonExistedDto = new CreateBorrowDto();
        nonExistedDto.setBookId(100L);
        nonExistedDto.setClientId(100L);
        assertThrows(BookWasntBorrowedException.class, () -> borrowService.closeBorrow(nonExistedDto));
    }

    @Test
    @Order(3)
    void closeBorrow() {
        CreateBorrowDto testDto = new CreateBorrowDto();
        testDto.setClientId(testId);
        testDto.setBookId(testId);
        assertDoesNotThrow(() -> borrowService.closeBorrow(testDto));
    }
}