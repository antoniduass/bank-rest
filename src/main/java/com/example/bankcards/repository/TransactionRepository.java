package com.example.bankcards.repository;

import com.example.bankcards.entity.Transaction;
import com.example.bankcards.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    Page<Transaction> findByFromCardOwner(User owner, Pageable pageable);
    Page<Transaction> findByFromCardOwnerAndCreatedAtBetween(User owner, LocalDateTime from, LocalDateTime to, Pageable pageable);

    Page<Transaction> findByFromCardIdOrToCardId(Long fromCardId, Long toCardId, Pageable pageable);
    Page<Transaction> findByUserId(Long userId, Pageable pageable);
}
