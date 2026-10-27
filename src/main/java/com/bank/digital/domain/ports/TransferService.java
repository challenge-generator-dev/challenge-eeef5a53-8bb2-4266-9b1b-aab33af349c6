package com.bank.digital.domain.ports;

import com.bank.digital.domain.model.Transfer;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Puerto de transferencia que define el contrato para operaciones de transferencia entre cuentas.
 * Esta interfaz vive en la capa de dominio y es implementada por la capa de aplicación.
 */
public interface TransferService {

    /**
     * Ejecuta una transferencia entre dos cuentas.
     * @param sourceAccountId ID de la cuenta de origen
     * @param targetAccountId ID de la cuenta de destino
     * @param amount Monto a transferir
     * @param reference Referencia única de la transferencia para idempotencia
     * @return Objeto Transfer con el resultado de la operación
     * @throws InsufficientBalanceException si el saldo es insuficiente
     */
    Transfer executeTransfer(UUID sourceAccountId, UUID targetAccountId, BigDecimal amount, String reference);

    /**
     * Valida que una cuenta tenga saldo suficiente para una operación.
     * @param accountId ID de la cuenta a validar
     * @param amount Monto requerido
     * @return true si el saldo es suficiente, false en caso contrario
     */
    boolean validateBalance(UUID accountId, BigDecimal amount);

    /**
     * Busca una transferencia por su identificador único.
     * @param transferId ID de la transferencia
     * @return Optional con la transferencia si existe
     */
    Optional<Transfer> findById(UUID transferId);

    /**
     * Busca transferencias por ID de cuenta de origen.
     * @param accountId ID de la cuenta de origen
     * @return Lista de transferencias realizadas desde esa cuenta
     */
    List<Transfer> findBySourceAccountId(UUID accountId);

    /**
     * Busca transferencias por ID de cuenta de destino.
     * @param accountId ID de la cuenta de destino
     * @return Lista de transferencias recibidas en esa cuenta
     */
    List<Transfer> findByTargetAccountId(UUID accountId);

    /**
     * Busca una transferencia por su referencia (para idempotencia).
     * @param reference Referencia única de la transferencia
     * @return Optional con la transferencia si existe
     */
    Optional<Transfer> findByReference(String reference);

    /**
     * Cancela una transferencia pendiente.
     * @param transferId ID de la transferencia a cancelar
     * @return Transfer actualizada con estado CANCELLED
     */
    Transfer cancelTransfer(UUID transferId);

    /**
     * Obtiene el historial de transferencias de una cuenta (origen y destino).
     * @param accountId ID de la cuenta
     * @return Lista combinada de transferencias
     */
    List<Transfer> findAllByAccountId(UUID accountId);

    /**
     * Valida que una transferencia pueda realizarse según las reglas de negocio.
     * @param sourceAccountId ID de cuenta de origen
     * @param targetAccountId ID de cuenta de destino
     * @param amount Monto a transferir
     * @return true si la transferencia es válida
     */
    boolean validateTransfer(UUID sourceAccountId, UUID targetAccountId, BigDecimal amount);
}