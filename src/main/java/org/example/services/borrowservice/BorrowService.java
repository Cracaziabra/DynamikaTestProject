package org.example.services.borrowservice;

import org.example.core.Borrow;
import org.example.dtos.CreateBorrowDto;

import java.util.List;

public interface BorrowService {

    List<Borrow> getAllBorrows();

    Borrow startBorrow(CreateBorrowDto borrowDto);

    void closeBorrow(CreateBorrowDto borrowDto);

}
