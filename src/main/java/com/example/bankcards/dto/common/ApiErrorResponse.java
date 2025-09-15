package com.example.bankcards.dto.common;

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
@Schema(description = "API error response")
public class ApiErrorResponse {
    @Schema(description = "Error message", example = "Card not found")
    private String message;

    @Schema(description = "Error code", example = "CARD_NOT_FOUND")
    private String code;

    @Schema(description = "Timestamp when error ocured")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    @Schema(description = "Request path", example = "api/cards/123")
    private String path;
}
