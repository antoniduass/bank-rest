package com.example.bankcards.mapper;

import com.example.bankcards.dto.request.transaction.TransferRequest;
import com.example.bankcards.dto.response.transaction.TransactionResponse;
import com.example.bankcards.entity.Transaction;
import com.example.bankcards.entity.User;
import com.example.bankcards.util.CardNumberMaskerUtil;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper{
    @Mapping(target = "fromCardMasked", expression = "java(maskCardNumber(transaction.getFromCard().getNumber()))")
    @Mapping(target = "toCardMasked", expression = "java(maskCardNumber(transaction.getToCard().getNumber()))")
    TransactionResponse toResponse(Transaction transaction, @Context User currentUser);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fromCard", ignore = true)
    @Mapping(target = "toCard", ignore = true)
    @Mapping(target = "timestamp", ignore = true)
    Transaction toEntity(TransferRequest request);

    List<TransactionResponse> toResponseList(List<Transaction> transactions, @Context User currentUser);

    default String maskCardNumber(String cardNumber){
        return CardNumberMaskerUtil.maskCardNumber(cardNumber);
    }
}
