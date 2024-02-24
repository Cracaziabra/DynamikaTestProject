package org.example.repositories;

import org.example.core.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {

    boolean existsByBookIdAndClientId(long bookId, long clientId);

    Long deleteByBookIdAndClientId(long bookId, long clientId);

}
