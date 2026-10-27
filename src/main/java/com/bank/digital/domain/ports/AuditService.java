package com.bank.digital.domain.ports;

import com.bank.digital.domain.model.Transfer;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Puerto de auditoría que define el contrato para registrar operaciones.
 * Esta interfaz vive en la capa de dominio y es implementada por la capa de infraestructura.
 */
public interface AuditService {

    /**
     * Registra una consulta de saldo de cuenta.
     * @param accountId Identificador único de la cuenta
     * @param accountNumber Número de cuenta consultada
     */
    void logAccountQuery(UUID accountId, String accountNumber);

    /**
     * Registra el inicio de una transferencia.
     * @param transfer Objeto Transfer con los detalles de la operación
     */
    void logTransferInitiated(Transfer transfer);

    /**
     * Registra la finalización exitosa de una transferencia.
     * @param transfer Objeto Transfer con los detalles de la operación completada
     */
    void logTransferCompleted(Transfer transfer);

    /**
     * Registra una transferencia fallida.
     * @param transfer Objeto Transfer con los detalles de la operación fallida
     * @param reason Razón del fallo
     */
    void logTransferFailed(Transfer transfer, String reason);

    /**
     * Registra una operación genérica en el sistema.
     * @param operation Nombre de la operación realizada
     * @param entityId Identificador de la entidad afectada
     * @param details Detalles adicionales de la operación
     */
    void logOperation(String operation, UUID entityId, String details);

    /**
     * Registra una operación con marca de tiempo específica.
     * @param operation Nombre de la operación
     * @param entityId Identificador de la entidad
     * @param details Detalles adicionales
     * @param timestamp Marca de tiempo de la operación
     */
    void logOperationWithTimestamp(String operation, UUID entityId, String details, LocalDateTime timestamp);

    /**
     * Registra un evento de validación de cuenta.
     * @param accountId Identificador de la cuenta
     * @param isValid Resultado de la validación
     * @param reason Razón si la validación falló
     */
    void logAccountValidation(UUID accountId, boolean isValid, String reason);

    /**
     * Registra una operación de depósito.
     * @param accountId Identificador de la cuenta
     * @param amount Monto depositado
     * @param newBalance Nuevo saldo de la cuenta
     */
    void logDeposit(UUID accountId, java.math.BigDecimal amount, java.math.BigDecimal newBalance);

    /**
     * Registra una operación de retiro.
     * @param accountId Identificador de la cuenta
     * @param amount Monto retirado
     * @param newBalance Nuevo saldo de la cuenta
     */
    void logWithdrawal(UUID accountId, java.math.BigDecimal amount, java.math.BigDecimal newBalance);
}