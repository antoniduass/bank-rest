package com.example.bankcards.mapper.update;

import com.example.bankcards.dto.request.admin.UpdateCardStatusRequest;
import com.example.bankcards.entity.Card;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardUpdateMapper{
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cardNumber", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "expiryDate", ignore = true)
    @Mapping(target = "balance", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "outgoingTransactions", ignore = true)
    @Mapping(target = "incomingTransactions", ignore = true)
    void updateCardStatus(@MappingTarget Card card, UpdateCardStatusRequest request);
}
