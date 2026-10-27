package com.bank.digital.application;

import com.bank.digital.domain.exception.CoreBankingException;
import com.bank.digital.domain.exception.InsufficientBalanceException;
import com.bank.digital.domain.model.Account;
import com.bank.digital.domain.model.Transfer;
import com.bank.digital.domain.model.Transfer.TransferStatus;
import com.bank.digital.domain.ports.AccountRepository;
import com.bank.digital.domain.ports.AuditService;
import com.bank.digital.domain.ports.TransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransferServiceImpl implements TransferService {

    private static final Logger logger = LoggerFactory.getLogger(TransferServiceImpl.class);
    private static final BigDecimal MINIMUM_TRANSFER_AMOUNT = new BigDecimal("0.01");
    private static final int MAX_IDEMPOTENCY_KEYS = 10000;

    private final AccountRepository accountRepository;
    private final AuditService auditService;
    private final java.util.Map<String, Transfer> idempotencyCache = new java.util.ConcurrentHashMap<>();

    @Autowired
    public TransferServiceImpl(AccountRepository accountRepository, AuditService auditService) {
        this.accountRepository = accountRepository;
        this.auditService = auditService;
    }

    @Override
    @Transactional
    public Transfer executeTransfer(UUID sourceAccountId, UUID targetAccountId, BigDecimal amount, 
                                     String currency, String reference, String idempotencyKey) {
        logger.info("Iniciando transferencia: {} -> {} por {} {}", sourceAccountId, targetAccountId, amount, currency);

        if (idempotencyKey != null && idempotencyCache.containsKey(idempotencyKey)) {
            logger.info("Transferencia idempotente detectada, retornando resultado cacheado para key: {}", idempotencyKey);
            return idempotencyCache.get(idempotencyKey);
        }

        validateTransferParameters(sourceAccountId, targetAccountId, amount, currency);

        Optional<Account> sourceAccountOpt = accountRepository.findById(sourceAccountId);
        Optional<Account> targetAccountOpt = accountRepository.findById(targetAccountId);

        if (sourceAccountOpt.isEmpty()) {
            logger.error("Cuenta de origen no encontrada: {}", sourceAccountId);
            throw new CoreBankingException("Cuenta de origen no encontrada: " + sourceAccountId);
        }

        if (targetAccountOpt.isEmpty()) {
            logger.error("Cuenta de destino no encontrada: {}", targetAccountId);
            throw new CoreBankingException("Cuenta de destino no encontrada: " + targetAccountId);
        }

        Account sourceAccount = sourceAccountOpt.get();
        Account targetAccount = targetAccountOpt.get();

        validateBalance(sourceAccount, amount);

        sourceAccount.withdraw(amount);
        targetAccount.deposit(amount);

        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);

        Transfer transfer = new Transfer(sourceAccountId, targetAccountId, amount, currency, 
                reference != null ? reference : UUID.randomUUID().toString());
        transfer.setStatus(TransferStatus.COMPLETED);

        if (idempotencyKey != null) {
            if (idempotencyCache.size() >= MAX_IDEMPOTENCY_KEYS) {
                logger.warn("Cache de idempotencia lleno, limpiando entradas antiguas");
                idempotencyCache.clear();
            }
            idempotencyCache.put(idempotencyKey, transfer);
        }

        auditService.logOperation("TRANSFER", transfer.getId().toString(), 
                "Transferencia completada: " + sourceAccountId + " -> " + targetAccountId + " amount: " + amount);

        logger.info("Transferencia completada exitosamente: {}", transfer.getId());
        return transfer;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transfer> getTransferHistory(UUID accountId) {
        logger.info("Consultando historial de transferencias para cuenta: {}", accountId);
        return List.of();
    }

    private void validateTransferParameters(UUID sourceAccountId, UUID targetAccountId, 
                                             BigDecimal amount, String currency) {
        if (sourceAccountId == null) {
            throw new CoreBankingException("El ID de cuenta de origen es requerido");
        }
        if (targetAccountId == null) {
            throw new CoreBankingException("El ID de cuenta de destino es requerido");
        }
        if (sourceAccountId.equals(targetAccountId)) {
            throw new CoreBankingException("No se puede transferir a la misma cuenta");
        }
        if (amount == null || amount.compareTo(MINIMUM_TRANSFER_AMOUNT) < 0) {
            throw new CoreBankingException("El monto de transferencia debe ser mayor a " + MINIMUM_TRANSFER_AMOUNT);
        }
        if (currency == null || currency.isBlank()) {
            throw new CoreBankingException("La moneda es requerida");
        }
    }

    private void validateBalance(Account account, BigDecimal amount) {
        if (account.getBalance().compareTo(amount) < 0) {
            logger.warn("Saldo insuficiente para cuenta: {}. Saldo actual: {}, monto solicitado: {}", 
                    account.getAccountNumber(), account.getBalance(), amount);
            throw new InsufficientBalanceException("Saldo insuficiente. Saldo actual: " + account.getBalance() + 
                    ", monto solicitado: " + amount);
        }
    }
}