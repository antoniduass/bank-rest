package com.example.bankcards.mapper;

import com.example.bankcards.dto.request.card.CreateCardRequest;
import com.example.bankcards.dto.response.card.CardResponse;
import com.example.bankcards.dto.response.card.CardSummaryResponse;
import com.example.bankcards.entity.Card;
import com.example.bankcards.util.CardNumberMaskerUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardMapper{
    @Mapping(target = "maskedNumber", expression = "java(maskCardNumber(card.getNumber()))")
    @Mapping(target = "ownerUsername", source = "owner.username")
    @Mapping(target = "ownerId", source = "owner.id")
    @Mapping(target = "outgoingTransactionsCount", expression = "java(card.getOutgoingTransactions().size())")
    @Mapping(target = "incomingTransactionsCount", expression = "java(card.getIncomingTransactions().size())")
    CardResponse toResponse(Card card);

    @Mapping(target = "maskedNumber", expression = "java(maskCardNumber(card.getNumber()))")
    CardSummaryResponse toSummaryResponse(Card card);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "number", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "balance", source = "initialBalance")
    @Mapping(target = "outgoingTransactions", ignore = true)
    @Mapping(target = "incomingTransactions", ignore = true)
    @Mapping(target = "statusChangeRequests", ignore = true)
    Card toEntity(CreateCardRequest request);

    List<CardResponse> toResponseList(List<Card> cards);
    List<CardSummaryResponse> toSummaryResponseList(List<Card> cards);

    default String maskCardNumber(String cardNumber){
        return CardNumberMaskerUtil.maskCardNumber(cardNumber);
    }
}
