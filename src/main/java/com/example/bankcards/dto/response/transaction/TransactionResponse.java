package com.example.bankcards.dto.response.transaction;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Transaction information")
public class TransactionResponse {
    @Schema(description = "Transaction ID", example = "1")
    private Long id;

    @Schema(description = "Masked source card number", example = "**** **** **** 1234")
    private String fromCardMasked;

    @Schema(description = "Masked destination card number", example = "**** **** **** 5678")
    private String toCardMasked;

    @Schema(description = "Transfer amount", example = "350.00")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal amount;

    @Schema(description = "Transaction timestamp")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    @Schema(description = "Transaction description", example = "Transfer between own cards")
    private String description;
}
