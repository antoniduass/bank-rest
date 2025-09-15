package com.example.bankcards.dto.request.card;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Create new card request (admin only)")
public class CreateCardRequest {
    @Schema(description = "Owner user ID", example = "1")
    @NotNull(message = "Owner ID is required")
    private Long userId;

    @Schema(description = "Card expiry date", example = "2033-12-31")
    @NotNull(message = "Expiry date is required")
    @Future(message = "Expiry date must be in future")
    private LocalDate expiryDate;

    @Schema(description = "Initial balance", example = "0.00")
    @DecimalMin(value = "0.00", message = "Initial balance cannot be negative")
    @Digits(integer = 12, fraction = 2, message = "Invalid amount format")
    private BigDecimal InitialBalance = BigDecimal.ZERO;
}
