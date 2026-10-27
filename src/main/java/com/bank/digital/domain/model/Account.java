package com.bank.digital.domain.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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
public class Account {
    @NotNull(message = "Account ID cannot be null")
    private UUID id;

    @NotNull(message = "Customer ID cannot be null")
    private UUID customerId;

    @NotNull(message = "Account number cannot be null")
    private String accountNumber;

    @NotNull(message = "Balance cannot be null")
    @PositiveOrZero(message = "Balance cannot be negative")
    private BigDecimal balance;

    @NotNull(message = "Currency cannot be null")
    private String currency;

    @NotNull(message = "Created date cannot be null")
    private LocalDateTime createdAt;

    @NotNull(message = "Last updated date cannot be null")
    private LocalDateTime lastUpdated;

    private boolean active;

    public Account(UUID customerId, String accountNumber, String currency) {
        this.id = UUID.randomUUID();
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.balance = BigDecimal.ZERO;
        this.currency = currency;
        this.createdAt = LocalDateTime.now();
        this.lastUpdated = LocalDateTime.now();
        this.active = true;
    }

    public void deposit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        this.balance = this.balance.add(amount);
        this.lastUpdated = LocalDateTime.now();
    }

    public void withdraw(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (this.balance.compareTo(amount) < 0) {
            throw new IllegalStateException("Insufficient balance");
        }
        this.balance = this.balance.subtract(amount);
        this.lastUpdated = LocalDateTime.now();
    }

    public void close() {
        this.active = false;
        this.lastUpdated = LocalDateTime.now();
    }
}