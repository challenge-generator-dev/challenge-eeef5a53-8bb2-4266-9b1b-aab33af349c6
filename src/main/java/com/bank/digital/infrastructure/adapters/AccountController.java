package com.bank.digital.infrastructure.adapters;

import com.bank.digital.application.AccountService;
import com.bank.digital.application.TransferServiceImpl;
import com.bank.digital.domain.model.Account;
import com.bank.digital.domain.model.Transfer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/accounts")
@Tag(name = "Cuentas", description = "Operaciones de consulta de saldo y gestión de cuentas")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private final AccountService accountService;
    private final TransferServiceImpl transferService;

    @Autowired
    public AccountController(AccountService accountService, TransferServiceImpl transferService) {
        this.accountService = accountService;
        this.transferService = transferService;
    }

    @GetMapping("/{accountId}/balance")
    @Operation(summary = "Consultar saldo de cuenta", 
               description = "Retorna el saldo actual de una cuenta específica")
    public ResponseEntity<BalanceResponse> getBalance(
            @Parameter(description = "ID de la cuenta", required = true) 
            @PathVariable UUID accountId) {
        logger.info("Solicitud de saldo para cuenta: {}", accountId);
        long startTime = System.currentTimeMillis();

        try {
            Account account = accountService.getAccountById(accountId);
            BalanceResponse response = new BalanceResponse(
                    account.getId(),
                    account.getAccountNumber(),
                    account.getBalance(),
                    account.getCurrency(),
                    account.isActive()
            );

            long duration = System.currentTimeMillis() - startTime;
            logger.info("Consulta de saldo completada en {}ms para cuenta: {}", duration, accountId);

            if (duration > 200) {
                logger.warn("Latencia de consulta de saldo excedida: {}ms > 200ms", duration);
            }

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error al consultar saldo para cuenta {}: {}", accountId, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BalanceResponse(null, null, null, null, false));
        }
    }

    @PostMapping("/transfer")
    @Operation(summary = "Realizar transferencia", 
               description = "Ejecuta una transferencia entre dos cuentas")
    public ResponseEntity<TransferResponse> executeTransfer(@RequestBody TransferRequest request) {
        logger.info("Solicitud de transferencia: {} -> {} por {}", 
                request.sourceAccountId(), request.targetAccountId(), request.amount());
        long startTime = System.currentTimeMillis();

        try {
            Transfer transfer = transferService.executeTransfer(
                    request.sourceAccountId(),
                    request.targetAccountId(),
                    request.amount(),
                    request.currency(),
                    request.reference(),
                    request.idempotencyKey()
            );

            long duration = System.currentTimeMillis() - startTime;
            logger.info("Transferencia completada en {}ms: {}", duration, transfer.getId());

            if (duration > 500) {
                logger.warn("Latencia de transferencia excedida: {}ms > 500ms", duration);
            }

            TransferResponse response = new TransferResponse(
                    transfer.getId(),
                    transfer.getSourceAccountId(),
                    transfer.getTargetAccountId(),
                    transfer.getAmount(),
                    transfer.getCurrency(),
                    transfer.getStatus().name(),
                    transfer.getTransferDate()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            logger.error("Error al ejecutar transferencia: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new TransferResponse(null, null, null, null, null, "FAILED", null));
        }
    }

    @GetMapping("/{accountId}/history")
    @Operation(summary = "Historial de transferencias", 
               description = "Retorna el historial de transferencias de una cuenta")
    public ResponseEntity<List<TransferResponse>> getTransferHistory(
            @Parameter(description = "ID de la cuenta", required = true) 
            @PathVariable UUID accountId) {
        logger.info("Solicitando historial de transferencias para cuenta: {}", accountId);

        try {
            List<Transfer> transfers = transferService.getTransferHistory(accountId);
            List<TransferResponse> response = transfers.stream()
                    .map(t -> new TransferResponse(
                            t.getId(),
                            t.getSourceAccountId(),
                            t.getTargetAccountId(),
                            t.getAmount(),
                            t.getCurrency(),
                            t.getStatus().name(),
                            t.getTransferDate()))
                    .toList();

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error al obtener historial de transferencias: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of());
        }
    }

    public record BalanceResponse(UUID id, String accountNumber, BigDecimal balance, 
                                   String currency, boolean active) {}

    public record TransferResponse(UUID id, UUID sourceAccountId, UUID targetAccountId, 
                                    BigDecimal amount, String currency, String status, 
                                    java.time.LocalDateTime transferDate) {}

    public record TransferRequest(UUID sourceAccountId, UUID targetAccountId, BigDecimal amount, 
                                   String currency, String reference, String idempotencyKey) {}
}