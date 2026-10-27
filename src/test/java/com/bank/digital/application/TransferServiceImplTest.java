package com.bank.digital.application;

import com.bank.digital.domain.exception.CoreBankingException;
import com.bank.digital.domain.exception.InsufficientBalanceException;
import com.bank.digital.domain.model.Account;
import com.bank.digital.domain.model.Transfer;
import com.bank.digital.domain.model.Transfer.TransferStatus;
import com.bank.digital.domain.ports.AccountRepository;
import com.bank.digital.domain.ports.AuditService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("TransferServiceImpl - Pruebas Unitarias")
class TransferServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AuditService auditService;

    @InjectMocks
    private TransferServiceImpl transferService;

    private Account sourceAccount;
    private Account targetAccount;
    private UUID sourceAccountId;
    private UUID targetAccountId;

    @BeforeEach
    void setUp() {
        sourceAccountId = UUID.randomUUID();
        targetAccountId = UUID.randomUUID();
        sourceAccount = new Account(sourceAccountId, "ACC001", "USD");
        sourceAccount.deposit(new BigDecimal("1000.00"));
        targetAccount = new Account(targetAccountId, "ACC002", "USD");
        targetAccount.deposit(new BigDecimal("500.00"));
    }

    @Nested
    @DisplayName("Escenarios de Éxito")
    class SuccessScenarios {

        @Test
        @DisplayName("Transferencia exitosa entre dos cuentas")
        void transfer_Success_BetweenAccounts() {
            when(accountRepository.findById(sourceAccountId)).thenReturn(Optional.of(sourceAccount));
            when(accountRepository.findById(targetAccountId)).thenReturn(Optional.of(targetAccount));
            when(accountRepository.save(any(Account.class))).thenAnswer(inv -> inv.getArgument(0));

            BigDecimal amount = new BigDecimal("200.00");
            Transfer result = transferService.transfer(sourceAccountId, targetAccountId, amount, "USD", "REF-001");

            assertNotNull(result);
            assertEquals(TransferStatus.COMPLETED, result.getStatus());
            assertEquals(amount, result.getAmount());
            assertEquals(sourceAccountId, result.getSourceAccountId());
            assertEquals(targetAccountId, result.getTargetAccountId());

            ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
            verify(accountRepository, times(2)).save(accountCaptor.capture());

            Account savedSource = accountCaptor.getAllValues().get(0);
            Account savedTarget = accountCaptor.getAllValues().get(1);
            assertEquals(new BigDecimal("800.00"), savedSource.getBalance());
            assertEquals(new BigDecimal("700.00"), savedTarget.getBalance());

            verify(auditService).logTransfer(any(Transfer.class));
        }

        @Test
        @DisplayName("Transferencia por monto exacto igual al saldo")
        void transfer_Success_ExactBalanceAmount() {
            when(accountRepository.findById(sourceAccountId)).thenReturn(Optional.of(sourceAccount));
            when(accountRepository.findById(targetAccountId)).thenReturn(Optional.of(targetAccount));
            when(accountRepository.save(any(Account.class))).thenAnswer(inv -> inv.getArgument(0));

            BigDecimal amount = new BigDecimal("1000.00");
            Transfer result = transferService.transfer(sourceAccountId, targetAccountId, amount, "USD", "REF-EXACT");

            assertNotNull(result);
            assertEquals(TransferStatus.COMPLETED, result.getStatus());
            assertEquals(BigDecimal.ZERO, sourceAccount.getBalance());

            verify(auditService).logTransfer(any(Transfer.class));
        }

        @Test
        @DisplayName("Transferencia múltiples successful consecutivas")
        void transfer_Success_MultipleTransfers() {
            when(accountRepository.findById(sourceAccountId)).thenReturn(Optional.of(sourceAccount));
            when(accountRepository.findById(targetAccountId)).thenReturn(Optional.of(targetAccount));
            when(accountRepository.save(any(Account.class))).thenAnswer(inv -> inv.getArgument(0));

            BigDecimal firstAmount = new BigDecimal("100.00");
            transferService.transfer(sourceAccountId, targetAccountId, firstAmount, "USD", "REF-1");

            BigDecimal secondAmount = new BigDecimal("150.00");
            transferService.transfer(sourceAccountId, targetAccountId, secondAmount, "USD", "REF-2");

            assertEquals(new BigDecimal("750.00"), sourceAccount.getBalance());
            assertEquals(new BigDecimal("750.00"), targetAccount.getBalance());

            verify(auditService, times(2)).logTransfer(any(Transfer.class));
        }
    }

    @Nested
    @DisplayName("Escenarios de Fallo")
    class FailureScenarios {

        @Test
        @DisplayName("Fallo por saldo insuficiente")
        void transfer_Failure_InsufficientBalance() {
            sourceAccount.withdraw(new BigDecimal("1000.00"));
            when(accountRepository.findById(sourceAccountId)).thenReturn(Optional.of(sourceAccount));
            when(accountRepository.findById(targetAccountId)).thenReturn(Optional.of(targetAccount));

            BigDecimal amount = new BigDecimal("100.00");

            assertThrows(InsufficientBalanceException.class, () -> {
                transferService.transfer(sourceAccountId, targetAccountId, amount, "USD", "REF-FAIL");
            });

            verify(accountRepository, never()).save(any(Account.class));
            verify(auditService, never()).logTransfer(any(Transfer.class));
        }

        @Test
        @DisplayName("Fallo por cuenta de origen inexistente")
        void transfer_Failure_SourceAccountNotFound() {
            when(accountRepository.findById(sourceAccountId)).thenReturn(Optional.empty());
            when(accountRepository.findById(targetAccountId)).thenReturn(Optional.of(targetAccount));

            BigDecimal amount = new BigDecimal("100.00");

            assertThrows(CoreBankingException.class, () -> {
                transferService.transfer(sourceAccountId, targetAccountId, amount, "USD", "REF-NO-SOURCE");
            });

            verify(auditService, never()).logTransfer(any(Transfer.class));
        }

        @Test
        @DisplayName("Fallo por cuenta de destino inexistente")
        void transfer_Failure_TargetAccountNotFound() {
            when(accountRepository.findById(sourceAccountId)).thenReturn(Optional.of(sourceAccount));
            when(accountRepository.findById(targetAccountId)).thenReturn(Optional.empty());

            BigDecimal amount = new BigDecimal("100.00");

            assertThrows(CoreBankingException.class, () -> {
                transferService.transfer(sourceAccountId, targetAccountId, amount, "USD", "REF-NO-TARGET");
            });

            verify(auditService, never()).logTransfer(any(Transfer.class));
        }

        @Test
        @DisplayName("Fallo por monto negativo")
        void transfer_Failure_NegativeAmount() {
            when(accountRepository.findById(sourceAccountId)).thenReturn(Optional.of(sourceAccount));
            when(accountRepository.findById(targetAccountId)).thenReturn(Optional.of(targetAccount));

            BigDecimal negativeAmount = new BigDecimal("-50.00");

            assertThrows(IllegalArgumentException.class, () -> {
                transferService.transfer(sourceAccountId, targetAccountId, negativeAmount, "USD", "REF-NEG");
            });
        }

        @Test
        @DisplayName("Fallo por monto cero")
        void transfer_Failure_ZeroAmount() {
            when(accountRepository.findById(sourceAccountId)).thenReturn(Optional.of(sourceAccount));
            when(accountRepository.findById(targetAccountId)).thenReturn(Optional.of(targetAccount));

            BigDecimal zeroAmount = BigDecimal.ZERO;

            assertThrows(IllegalArgumentException.class, () -> {
                transferService.transfer(sourceAccountId, targetAccountId, zeroAmount, "USD", "REF-ZERO");
            });
        }

        @Test
        @DisplayName("Fallo por moneda diferente")
        void transfer_Failure_CurrencyMismatch() {
            when(accountRepository.findById(sourceAccountId)).thenReturn(Optional.of(sourceAccount));
            when(accountRepository.findById(targetAccountId)).thenReturn(Optional.of(targetAccount));

            BigDecimal amount = new BigDecimal("100.00");

            assertThrows(IllegalArgumentException.class, () -> {
                transferService.transfer(sourceAccountId, targetAccountId, amount, "EUR", "REF-CURRENCY");
            });
        }
    }

    @Nested
    @DisplayName("Validaciones de Comportamiento")
    class BehaviorValidations {

        @Test
        @DisplayName("La transferencia registra la fecha actual")
        void transfer_RecordsCurrentTimestamp() {
            when(accountRepository.findById(sourceAccountId)).thenReturn(Optional.of(sourceAccount));
            when(accountRepository.findById(targetAccountId)).thenReturn(Optional.of(targetAccount));
            when(accountRepository.save(any(Account.class))).thenAnswer(inv -> inv.getArgument(0));

            LocalDateTime beforeTransfer = LocalDateTime.now();
            Transfer result = transferService.transfer(sourceAccountId, targetAccountId, new BigDecimal("50.00"), "USD", "REF-TIME");
            LocalDateTime afterTransfer = LocalDateTime.now();

            assertNotNull(result.getTransferDate());
            assertTrue(result.getTransferDate().isEqual(beforeTransfer) || result.getTransferDate().isAfter(beforeTransfer));
            assertTrue(result.getTransferDate().isEqual(afterTransfer) || result.getTransferDate().isBefore(afterTransfer));
        }

        @Test
        @DisplayName("La transferencia genera un ID único")
        void transfer_GeneratesUniqueId() {
            when(accountRepository.findById(sourceAccountId)).thenReturn(Optional.of(sourceAccount));
            when(accountRepository.findById(targetAccountId)).thenReturn(Optional.of(targetAccount));
            when(accountRepository.save(any(Account.class))).thenAnswer(inv -> inv.getArgument(0));

            Transfer result1 = transferService.transfer(sourceAccountId, targetAccountId, new BigDecimal("10.00"), "USD", "REF-ID-1");
            Transfer result2 = transferService.transfer(sourceAccountId, targetAccountId, new BigDecimal("10.00"), "USD", "REF-ID-2");

            assertNotEquals(result1.getId(), result2.getId());
        }

        @Test
        @DisplayName("La referencia se preserva en la transferencia")
        void transfer_PreservesReference() {
            when(accountRepository.findById(sourceAccountId)).thenReturn(Optional.of(sourceAccount));
            when(accountRepository.findById(targetAccountId)).thenReturn(Optional.of(targetAccount));
            when(accountRepository.save(any(Account.class))).thenAnswer(inv -> inv.getArgument(0));

            String reference = "TXN-2024-001234";
            Transfer result = transferService.transfer(sourceAccountId, targetAccountId, new BigDecimal("100.00"), "USD", reference);

            assertEquals(reference, result.getReference());
        }
    }
}