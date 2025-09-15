package com.example.bankcards.dto.request.transaction;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Transfer money between own cards")
public class TransferRequest {
    @Schema(description = "Source card ID", example = "1")
    @NotNull(message = "Source card ID is required")
    private Long fromCard;

    @Schema(description = "Destination card ID", example = "2")
    @NotNull(message = "Destination card ID is required")
    private Long toCard;

    @Schema(description = "Transfer amount", example = "350.00")
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "10", message = "Amount must be minimal equals 10")
    @Digits(integer = 12, fraction = 2, message = "Invalid amount format")
    private BigDecimal amount;

    @Schema(description = "Transfer description", example = "Top up main card")
    @Size(max = 200, message = "Description cannot exceed 200 characters")
    private String description = "Transfer between own cards";
}
