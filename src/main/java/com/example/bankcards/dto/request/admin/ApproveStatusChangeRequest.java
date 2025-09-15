package com.example.bankcards.dto.request.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Approve or reject status change request")
public class ApproveStatusChangeRequest{
    @Schema(description = "Status change request ID", example = "1")
    @NotNull(message = "Request ID is required")
    private Long requestId;

    @Schema(description = "Approved(true) or reject(false)")
    @NotNull(message = "Approval decision is required")
    private Boolean approved;

    @Schema(description = "Admin comment", example = "Request approved, card blocked")
    @Size(max = 500, message = "Comment cannot exceed 500 characters")
    private String comment;
}
