package com.example.bankcards.mapper;

import com.example.bankcards.dto.request.user.CreateUserRequest;
import com.example.bankcards.dto.response.auth.LoginResponse;
import com.example.bankcards.dto.response.user.UserResponse;
import com.example.bankcards.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper{
    @Mapping(target = "cardsCount", expression = "java(user.getCards().size())")
    UserResponse toResponse(User user);
    List<UserResponse> toResponseList(List<User> users);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "cards", ignore = true)
    @Mapping(target = "statusChangeRequests", ignore = true)
    User toEntity(CreateUserRequest request);

    @Mapping(target = "token", source = "token")
    @Mapping(target = "tokenType", constant = "Bearer")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "role", source = "user.role")
    @Mapping(target = "loginTime", expression = "java(java.time.LocalDateTime.now())")
    LoginResponse toLoginResponse(String token, User user);
}
