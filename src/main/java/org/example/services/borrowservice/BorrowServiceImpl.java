package org.example.services.borrowservice;

import lombok.RequiredArgsConstructor;
import org.example.core.Borrow;
import org.example.repositories.BorrowRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowServiceImpl implements BorrowService {

    private final BorrowRepository borrowRepository;

    @Override
    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }
}
