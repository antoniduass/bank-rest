package com.example.bankcards.mapper.response;

import com.example.bankcards.dto.response.admin.CardStatusChangeResponse;
import com.example.bankcards.entity.CardStatusChangeRequest;
import com.example.bankcards.util.CardNumberMaskerUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardStatusChangeResponseMapper {
    @Mapping(source = "card.cardNumber", target = "cardMaskedNumber", qualifiedByName = "maskCardNumber")
    @Mapping(source = "requestedBy.username", target = "requestedByUsername")
    @Mapping(source = "resolvedBy.username", target = "resolvedByUsername")
    CardStatusChangeResponse toResponse(CardStatusChangeRequest request);

    default String maskCardNumber(String cardNumber){
        return CardNumberMaskerUtil.maskCardNumber(cardNumber);
    }
}
