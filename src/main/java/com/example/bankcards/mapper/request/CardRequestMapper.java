package com.example.bankcards.mapper.request;

import com.example.bankcards.dto.request.card.CreateCardRequest;
import com.example.bankcards.dto.request.card.RequestCardStatusChangeRequest;
import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.CardStatusChangeRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardRequestMapper{
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cardNumber", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "balance", source = "initialBalance")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "outgoingTransactions", ignore = true)
    @Mapping(target = "incomingTransactions", ignore = true)
    Card toEntity(CreateCardRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "card", ignore = true)
    @Mapping(target = "requestedBy", ignore = true)
    @Mapping(target = "currentStatus", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "resolvedBy", ignore = true)
    @Mapping(target = "resolvedAt", ignore = true)
    @Mapping(target = "adminComment", ignore = true)
    CardStatusChangeRequest toEntity(RequestCardStatusChangeRequest request);
}
