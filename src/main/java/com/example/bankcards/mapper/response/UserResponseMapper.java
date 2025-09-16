package com.example.bankcards.mapper.response;

import com.example.bankcards.dto.response.user.UserResponse;
import com.example.bankcards.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserResponseMapper{
    @Mapping(source = "id", target = "userId")
    @Mapping(source = "cards.size", target = "cardsCount")
    UserResponse toResponse(User user);
}
