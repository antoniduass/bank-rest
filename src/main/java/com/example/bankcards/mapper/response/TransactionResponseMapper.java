package com.example.bankcards.mapper.response;

import com.example.bankcards.dto.response.transaction.TransactionResponse;
import com.example.bankcards.entity.Transaction;
import com.example.bankcards.util.CardNumberMaskerUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionResponseMapper{
    @Mapping(source = "fromCard.cardNumber", target = "fromCardMasked", qualifiedByName = "maskCardNumber")
    @Mapping(source = "toCard.cardNumber", target = "toCardMasked", qualifiedByName = "maskCardNumber")
    TransactionResponse toResponse(Transaction transaction);

    default String maskCardNumber(String cardNumber){
        return CardNumberMaskerUtil.maskCardNumber(cardNumber);
    }
}
