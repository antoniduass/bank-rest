package com.example.bankcards.mapper;

import com.example.bankcards.dto.response.admin.CardStatusChangeResponse;
import com.example.bankcards.entity.CardStatusChangeRequest;
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
public interface AdminMapper{
    @Mapping(target = "cardMaskedNumber", expression = "java(maskCardNumber(request.getCard().getNumber()))")
    @Mapping(target = "requestedByUsername", source = "requestedBy.username")
    @Mapping(target = "resolvedByUsername", source = "resolvedBy.username")
    CardStatusChangeResponse toResponse(CardStatusChangeRequest request);

    List<CardStatusChangeResponse> toResponseList(List<CardStatusChangeRequest> requests);

    default String maskCardNumber(String cardNumber){
        return CardNumberMaskerUtil.maskCardNumber(cardNumber);
    }
}
