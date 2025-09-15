package com.example.bankcards.dto.request.card;

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
@Schema(description = "Request card status change")
public class RequestCardStatusChangeRequest {
    @Schema(description = "Card ID to change status", example = "1")
    @NotNull(message = "Card ID is required")
    private Long cardId;

    @Schema(description = "Requested new status")
    @NotNull(message = "Requested status is required")
    private CardStatus requestedStatus;

    @Schema(description = "Reason for changing status", example = "Lost card")
    @Size(max = 500, message = "Reason cannot exceed 500 characters")
    private String reason;
}
