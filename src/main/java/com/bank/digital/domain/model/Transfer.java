package com.bank.digital.domain.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transfer {
    @NotNull(message = "Transfer ID cannot be null")
    private UUID id;

    @NotNull(message = "Source account ID cannot be null")
    private UUID sourceAccountId;

    @NotNull(message = "Target account ID cannot be null")
    private UUID targetAccountId;

    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be positive")
    private BigDecimal amount;

    @NotNull(message = "Currency cannot be null")
    private String currency;

    @NotNull(message = "Transfer date cannot be null")
    private LocalDateTime transferDate;

    private String reference;
    private TransferStatus status;

    public Transfer(UUID sourceAccountId, UUID targetAccountId, BigDecimal amount, String currency, String reference) {
        this.id = UUID.randomUUID();
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.amount = amount;
        this.currency = currency;
        this.transferDate = LocalDateTime.now();
        this.reference = reference;
        this.status = TransferStatus.PENDING;
    }

    public enum TransferStatus {
        PENDING,
        COMPLETED,
        FAILED,
        REJECTED
    }
}