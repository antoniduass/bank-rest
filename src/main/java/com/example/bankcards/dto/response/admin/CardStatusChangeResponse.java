package com.example.bankcards.dto.response.admin;

import com.example.bankcards.entity.enums.CardStatus;
import com.example.bankcards.entity.enums.RequestStatus;
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
@Schema(description = "Card status change request information")
public class CardStatusChangeResponse {
    @Schema(description = "Request ID", example = "1")
    private Long id;

    @Schema(description = "Masked card number", example = "**** **** **** 1234")
    private String cardMaskedNumber;

    @Schema(description = "User who requests change")
    private String requestedByUsername;

    @Schema(description = "Current card status at time of request")
    private CardStatus currentStatus;

    @Schema(description = "New requested status")
    private CardStatus requestedStatus;

    @Schema(description = "Request status")
    private RequestStatus status;

    @Schema(description = "Request creation time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @Schema(description = "Admin who resolved request", example = "admin")
    private String resolvedByUsername;

    @Schema(description = "Request resolution time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime resolvedAt;
}
