package org.example.services.borrowservice;

import lombok.RequiredArgsConstructor;
import org.example.core.Borrow;
import org.example.dtos.BorrowInfoDto;
import org.example.dtos.CreateBorrowDto;
import org.example.exceptions.BookAlreadyBorrowedException;
import org.example.exceptions.BookWasntBorrowedException;
import org.example.mappers.BorrowMapper;
import org.example.repositories.BorrowRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowServiceImpl implements BorrowService {

    private final BorrowRepository borrowRepository;
    private final BorrowMapper borrowMapper;

    @Override
    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    @Override
    public Borrow startBorrow(CreateBorrowDto borrowDto) {
        if (borrowRepository.existsByBookIdAndClientId(borrowDto.getBookId(), borrowDto.getClientId())) {
            throw new BookAlreadyBorrowedException();
        }
        Borrow borrow = borrowMapper.toEntity(borrowDto);
        borrow.setStartTime(LocalDateTime.now());
        return borrowRepository.saveAndFlush(borrow);
    }

    @Override
    @Transactional
    public void closeBorrow(CreateBorrowDto borrowDto) {
        if (!borrowRepository.existsByBookIdAndClientId(borrowDto.getBookId(), borrowDto.getClientId())) {
            throw new BookWasntBorrowedException();
        }
        borrowRepository.deleteByBookIdAndClientId(borrowDto.getBookId(), borrowDto.getClientId());
    }

    @Override
    public List<BorrowInfoDto> getAllBorrowsInfo() {
        return borrowRepository.getAllBorrowsInfo();
    }
}
