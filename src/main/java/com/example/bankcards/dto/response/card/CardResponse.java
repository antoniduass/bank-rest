package com.example.bankcards.dto.response.card;

import com.example.bankcards.entity.enums.CardStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Card information")
public class CardResponse{
    @Schema(description = "Card ID", example = "1")
    private Long id;

    @Schema(description = "Masked card number", example = "**** **** **** 1234")
    private String maskedNumber;

    @Schema(description = "Card owner username", example = "dillan_doe")
    private String ownerUsername;

    @Schema(description = "Card owner ID", example = "1")
    private Long ownerId;

    @Schema(description = "Card expiry date", format = "date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expiryDate;

    @Schema(description = "Current status")
    private CardStatus status;

    @Schema(description = "Current balance", example = "1250.000")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal balance;

    @Schema(description = "Number of outgoing transactions", example = "21")
    private Integer outgoingTransactions;

    @Schema(description = "Number of incoming transactions", example = "8")
    private Integer incomingTransactions;
}
