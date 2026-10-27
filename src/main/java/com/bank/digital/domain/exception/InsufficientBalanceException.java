package com.bank.digital.domain.exception;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Excepción personalizada que se lanza cuando una cuenta no tiene saldo suficiente
 * para realizar una operación de retiro o transferencia.
 * Esta excepción pertenece a la capa de dominio y representa una violación de las
 * reglas de negocio del sistema bancario.
 */
public class InsufficientBalanceException extends RuntimeException {

    private final UUID accountId;
    private final BigDecimal currentBalance;
    private final BigDecimal requestedAmount;
    private final String accountNumber;

    /**
     * Constructor principal con todos los detalles de la excepción.
     * @param accountId Identificador único de la cuenta
     * @param accountNumber Número de cuenta legible
     * @param currentBalance Saldo actual de la cuenta
     * @param requestedAmount Monto solicitado para la operación
     */
    public InsufficientBalanceException(UUID accountId, String accountNumber, 
                                         BigDecimal currentBalance, BigDecimal requestedAmount) {
        super(buildMessage(accountId, accountNumber, currentBalance, requestedAmount));
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.currentBalance = currentBalance;
        this.requestedAmount = requestedAmount;
    }

    /**
     * Constructor alternativo que deduce el número de cuenta.
     * @param accountId Identificador único de la cuenta
     * @param currentBalance Saldo actual
     * @param requestedAmount Monto solicitado
     */
    public InsufficientBalanceException(UUID accountId, BigDecimal currentBalance, 
                                         BigDecimal requestedAmount) {
        super(buildMessage(accountId, null, currentBalance, requestedAmount));
        this.accountId = accountId;
        this.accountNumber = null;
        this.currentBalance = currentBalance;
        this.requestedAmount = requestedAmount;
    }

    /**
     * Construye el mensaje de error descriptivo.
     */
    private static String buildMessage(UUID accountId, String accountNumber, 
                                        BigDecimal currentBalance, BigDecimal requestedAmount) {
        StringBuilder sb = new StringBuilder();
        sb.append("Saldo insuficiente para realizar la operación. ");
        sb.append("Cuenta: ");
        sb.append(accountNumber != null ? accountNumber : accountId.toString());
        sb.append(" | Saldo actual: ");
        sb.append(currentBalance != null ? currentBalance.toPlainString() : "0.00");
        sb.append(" | Monto solicitado: ");
        sb.append(requestedAmount != null ? requestedAmount.toPlainString() : "0.00");
        sb.append(" | Diferencia: ");
        if (currentBalance != null && requestedAmount != null) {
            sb.append(currentBalance.subtract(requestedAmount).toPlainString());
        } else {
            sb.append("0.00");
        }
        return sb.toString();
    }

    /**
     * Obtiene el identificador de la cuenta.
     * @return UUID de la cuenta
     */
    public UUID getAccountId() {
        return accountId;
    }

    /**
     * Obtiene el número de cuenta.
     * @return Número de cuenta o null si no está disponible
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Obtiene el saldo actual de la cuenta.
     * @return Saldo actual
     */
    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    /**
     * Obtiene el monto solicitado para la operación.
     * @return Monto solicitado
     */
    public BigDecimal getRequestedAmount() {
        return requestedAmount;
    }

    /**
     * Calcula la diferencia entre el saldo actual y el monto solicitado.
     * @return Diferencia (puede ser negativa)
     */
    public BigDecimal getDeficit() {
        if (currentBalance == null || requestedAmount == null) {
            return BigDecimal.ZERO;
        }
        return currentBalance.subtract(requestedAmount);
    }

    /**
     * Verifica si el déficit es crítico (más del 50% del saldo solicitado).
     * @return true si el déficit es crítico
     */
    public boolean isCriticalDeficit() {
        if (currentBalance == null || requestedAmount == null || requestedAmount.compareTo(BigDecimal.ZERO) == 0) {
            return false;
        }
        BigDecimal deficit = requestedAmount.subtract(currentBalance);
        return deficit.divide(requestedAmount, 4, java.math.RoundingMode.HALF_UP)
                      .compareTo(new BigDecimal("0.5")) > 0;
    }

    @Override
    public String toString() {
        return "InsufficientBalanceException{" +
                "accountId=" + accountId +
                ", accountNumber='" + accountNumber + '\'' +
                ", currentBalance=" + currentBalance +
                ", requestedAmount=" + requestedAmount +
                ", deficit=" + getDeficit() +
                '}';
    }
}