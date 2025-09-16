package com.example.bankcards.repository;

import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.CardStatusChangeRequest;
import com.example.bankcards.entity.User;
import com.example.bankcards.entity.enums.RequestStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardStatusChangeRequestRepository extends JpaRepository<CardStatusChangeRequest, Long>{
    List<CardStatusChangeRequest> findByStatus(RequestStatus status);
    Page<CardStatusChangeRequest> findByStatus(RequestStatus status, Pageable pageable);

    List<CardStatusChangeRequest> findByRequestedBy(User user);
    Page<CardStatusChangeRequest> findByRequestedBy(User user, Pageable pageable);

    Optional<CardStatusChangeRequest> findByCardAndStatusNot(Card card, RequestStatus status);

    Page<CardStatusChangeRequest> findAllByOrderByCreatedAtDesc(Pageable pageable);

    Page<CardStatusChangeRequest> findByStatusIn(List<RequestStatus> statuses, Pageable pageable);
}
