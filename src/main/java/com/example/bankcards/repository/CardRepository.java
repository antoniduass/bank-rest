package com.example.bankcards.repository;

import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.User;
import com.example.bankcards.entity.enums.CardStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long>{
    List<Card> findByOwner(User owner);
    Page<Card> findByOwner(User owner, Pageable pageable);

    List<Card> findByOwnerAndStatus(User owner, CardStatus status);

    Optional<Card> findByEncryptedNumber(String encryptNumber);
    Optional<Card> findByIdAndOwner(Long cardId, User owner);
    Optional<Card> findByIdAndOwnerId(Long cardId, Long userId);

    Page<Card> findByStatus(CardStatus status, Pageable pageable);
    Page<Card> findAll(Pageable pageable);
}
