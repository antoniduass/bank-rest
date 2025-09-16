package com.example.bankcards.service;

import com.example.bankcards.dto.request.transaction.TransferRequest;
import com.example.bankcards.dto.response.transaction.TransactionResponse;
import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.Transaction;
import com.example.bankcards.entity.User;
import com.example.bankcards.mapper.request.TransactionRequestMapper;
import com.example.bankcards.mapper.response.TransactionResponseMapper;
import com.example.bankcards.repository.CardRepository;
import com.example.bankcards.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionService{
    private final TransactionRepository transactionRepository;
    private final CardRepository cardRepository;
    private final TransactionRequestMapper transactionRequestMapper;
    private final TransactionResponseMapper transactionResponseMapper;

    @Transactional
    public TransactionResponse transferBetweenOwnCards(TransferRequest request, Long userId){
        Card fromCard = cardRepository.findByIdAndOwnerId(request.getFromCard(), userId)
                .orElseThrow(() -> new CardNotFoundException(request.getFromCard()));
        Card toCard = cardRepository.findByIdAndOwnerId(request.getToCard(), userId)
                .orElseThrow(() -> new CardNotFoundException(request.getToCard()));

        if (fromCard.getId().equals(toCard.getId())){
            throw new SameCardTransferException();
        }

        if (fromCard.getBalance().compareTo(request.getAmount()) < 0){
            throw new InsufficientBalanceException();
        }

        fromCard.setBalance(fromCard.getBalance().subtract(request.getAmount()));
        toCard.setBalance(toCard.getBalance().add(request.getAmount()));

        cardRepository.save(fromCard);
        cardRepository.save(toCard);

        Transaction transaction = transactionRequestMapper.toEntity(request);
        transaction.setFromCard(fromCard);
        transaction.setToCard(toCard);

        Transaction savedTransaction = transactionRepository.save(transaction);

        return transactionResponseMapper.toResponse(savedTransaction);
    }

    public Page<TransactionResponse> getCardTransactions(Long cardId, Pageable pageable) {
        return transactionRepository.findByFromCardIdOrToCardId(cardId, cardId, pageable)
                .map(transactionResponseMapper::toResponse);
    }

    public Page<TransactionResponse> getUserTransactions(Long userId, Pageable pageable){
        return transactionRepository.findByUserId(userId, pageable)
                .map(transactionResponseMapper::toResponse);
    }
}
