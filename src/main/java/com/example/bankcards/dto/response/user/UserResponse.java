package com.example.bankcards.dto.response.user;

import com.example.bankcards.entity.enums.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "User information")
public class UserResponse{
    @Schema(description = "User ID", example = "1")
    private Long userId;

    @Schema(description = "Username", example = "dillan_doe")
    private String username;

    @Schema(description = "Access level / role", example = "USER")
    private Role role;

    @Schema(description = "Registration date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @Schema(description = "Number of cards owned", example = "3")
    private Integer cardsCount;
}
