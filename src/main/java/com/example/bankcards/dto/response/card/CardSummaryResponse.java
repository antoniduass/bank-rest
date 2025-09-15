package com.example.bankcards.dto.response.card;

import com.example.bankcards.entity.enums.CardStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Card summary information")
public class CardSummaryResponse {
    @Schema(description = "Card ID", example = "1")
    private Long id;

    @Schema(description = "Masked card number", example = "**** **** **** 1234")
    private String maskedNumber;

    @Schema(description = "Current card status")
    private CardStatus status;

    @Schema(description = "Current card balance", example = "1250.00")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal balance;
}
