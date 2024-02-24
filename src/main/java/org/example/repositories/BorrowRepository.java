package org.example.repositories;

import org.example.core.Borrow;
import org.example.dtos.BorrowInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {

    boolean existsByBookIdAndClientId(long bookId, long clientId);

    Long deleteByBookIdAndClientId(long bookId, long clientId);

    @Query("select new org.example.dtos.BorrowInfoDto(c.firstName, c.lastName, c.patronymic, c.birthday, " +
            "b.name, b.author, b.isbn, br.startTime) " +
            "from Client as c " +
            "join fetch Borrow as br " +
            "on c.id = br.clientId " +
            "join fetch Book as b " +
            "on b.id = br.bookId")
    List<BorrowInfoDto> getAllBorrowsInfo();
}
