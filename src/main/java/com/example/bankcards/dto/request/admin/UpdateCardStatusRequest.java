package com.example.bankcards.dto.request.admin;

import com.example.bankcards.entity.enums.CardStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Directs card status update (admin only)")
public class UpdateCardStatusRequest {
    @Schema(description = "Card ID", example = "1")
    @NotNull(message = "Card ID is required")
    private Long cardId;

    @Schema(description = "New card status")
    @NotNull(message = "New card status is required")
    private CardStatus newStatus;

    @Schema(description = "Reason for status change", example = "Manual admin intervention")
    @Size(max = 500, message = "Reason cannot exceed 500 characters")
    private String reason;
}
