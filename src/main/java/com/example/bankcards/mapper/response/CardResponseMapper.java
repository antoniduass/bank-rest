package com.example.bankcards.mapper.response;

import com.example.bankcards.dto.response.card.CardResponse;
import com.example.bankcards.dto.response.card.CardSummaryResponse;
import com.example.bankcards.entity.Card;
import com.example.bankcards.util.CardNumberMaskerUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardResponseMapper{
    @Mapping(source = "user.username", target = "ownerUsername")
    @Mapping(source = "user.id", target = "ownerId")
    @Mapping(source = "cardNumber", target = "maskedNumber", qualifiedByName = "maskCardNumber")
    CardResponse toResponse(Card card);

    @Mapping(source = "cardNumber", target = "maskedNumber", qualifiedByName = "maskCardNumber")
    CardSummaryResponse toSummaryResponse(Card card);

    default String maskCardNumber(String cardNumber){
        return CardNumberMaskerUtil.maskCardNumber(cardNumber);
    }
}
