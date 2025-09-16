package com.example.bankcards.service;

import com.example.bankcards.dto.request.card.CreateCardRequest;
import com.example.bankcards.dto.request.card.RequestCardStatusChangeRequest;
import com.example.bankcards.dto.response.card.CardResponse;
import com.example.bankcards.dto.response.card.CardSummaryResponse;
import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.CardStatusChangeRequest;
import com.example.bankcards.entity.User;
import com.example.bankcards.entity.enums.CardStatus;
import com.example.bankcards.entity.enums.RequestStatus;
import com.example.bankcards.exception.CardNotFoundException;
import com.example.bankcards.exception.UserNotFoundException;
import com.example.bankcards.mapper.request.CardRequestMapper;
import com.example.bankcards.mapper.response.CardResponseMapper;
import com.example.bankcards.repository.CardRepository;
import com.example.bankcards.repository.CardStatusChangeRequestRepository;
import com.example.bankcards.repository.UserRepository;
import com.example.bankcards.util.CardNumberGeneratorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService{
    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final CardStatusChangeRequestRepository cardStatusChangeRequestRepository;
    private final CardRequestMapper cardRequestMapper;
    private final CardResponseMapper cardResponseMapper;

    public CardResponse getCardById(Long cardId){
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new CardNotFoundException(cardId));
        return cardResponseMapper.toResponse(card);
    }

    public List<CardSummaryResponse> getUserCards(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        List<Card> cards = cardRepository.findByOwner(user);
        return cards.stream()
                .map(cardResponseMapper::toSummaryResponse)
                .toList();
    }

    @Transactional
    public CardResponse createCard(CreateCardRequest request){
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException(request.getUserId()));

        Card card = cardRequestMapper.toEntity(request);
        card.setOwner(user);
        card.setStatus(CardStatus.ACTIVE);
        card.setNumber(CardNumberGeneratorUtil.generateCardNumber());

        Card savedCard = cardRepository.save(card);
        return cardResponseMapper.toResponse(savedCard);
    }

    @Transactional
    public CardStatusChangeRequest requestStatusChange(RequestCardStatusChangeRequest request, User requestedBy){
        Card card = cardRepository.findById(request.getCardId())
                .orElseThrow(() -> new CardNotFoundException(request.getCardId()));

        CardStatusChangeRequest statusChangeRequest = cardRequestMapper.toEntity(request);
        statusChangeRequest.setCard(card);
        statusChangeRequest.setRequestedBy(requestedBy);
        statusChangeRequest.setCurrentStatus(card.getStatus());
        statusChangeRequest.setStatus(RequestStatus.PENDING);

        return cardStatusChangeRequestRepository.save(statusChangeRequest);
    }

    public Page<CardResponse> getAllCards(Pageable pageable){
        return cardRepository.findAll(pageable)
                .map(cardResponseMapper::toResponse);
    }
}
