package com.bank.digital.infrastructure.adapters;


import com.bank.digital.domain.exception.InsufficientBalanceException;
import com.bank.digital.application.AccountService;
import com.bank.digital.application.TransferServiceImpl;
import com.bank.digital.domain.model.Account;
import com.bank.digital.domain.model.Transfer;
import com.bank.digital.domain.model.Transfer.TransferStatus;
import com.bank.digital.infrastructure.config.SwaggerConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountController.class)
@Import(SwaggerConfig.class)
@DisplayName("AccountController - Pruebas de Integración")
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @MockBean
    private TransferServiceImpl transferService;

    private Account testAccount;
    private UUID accountId;
    private String accountJson;
    private String transferJson;

    @BeforeEach
    void setUp() {
        accountId = UUID.randomUUID();
        testAccount = new Account(accountId, "ACC-TEST-001", "USD");
        testAccount.deposit(new BigDecimal("1500.00"));

        accountJson = """
            {
                "customerId": "%s",
                "accountNumber": "ACC-TEST-002",
                "currency": "USD"
            }
            """.formatted(UUID.randomUUID());

        transferJson = """
            {
                "sourceAccountId": "%s",
                "targetAccountId": "%s",
                "amount": 250.00,
                "currency": "USD",
                "reference": "TEST-REF-001"
            }
            """.formatted(accountId, UUID.randomUUID());
    }

    @Nested
    @DisplayName("GET /api/accounts/{id} - Consultar Cuenta")
    class GetAccountTests {

        @Test
        @DisplayName("Consulta exitosa de cuenta por ID")
        void getAccount_Success_ReturnsAccount() throws Exception {
            when(accountService.getAccountById(accountId)).thenReturn(testAccount);

            mockMvc.perform(get("/api/accounts/{id}", accountId))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.id").value(accountId.toString()))
                    .andExpect(jsonPath("$.accountNumber").value("ACC-TEST-001"))
                    .andExpect(jsonPath("$.balance").value(1500.00))
                    .andExpect(jsonPath("$.currency").value("USD"))
                    .andExpect(jsonPath("$.active").value(true));
        }

        @Test
        @DisplayName("Consulta falla cuando la cuenta no existe")
        void getAccount_NotFound_Returns404() throws Exception {
            UUID nonExistentId = UUID.randomUUID();
            when(accountService.getAccountById(nonExistentId)).thenReturn(null);

            mockMvc.perform(get("/api/accounts/{id}", nonExistentId))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("GET /api/accounts/{id}/balance - Consultar Saldo")
    class GetBalanceTests {

        @Test
        @DisplayName("Consulta exitosa de saldo")
        void getBalance_Success_ReturnsBalance() throws Exception {
            when(accountService.getAccountById(accountId)).thenReturn(testAccount);

            mockMvc.perform(get("/api/accounts/{id}/balance", accountId))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.accountId").value(accountId.toString()))
                    .andExpect(jsonPath("$.balance").value(1500.00))
                    .andExpect(jsonPath("$.currency").value("USD"));
        }

        @Test
        @DisplayName("Consulta de saldo falla para cuenta inexistente")
        void getBalance_NotFound_Returns404() throws Exception {
            UUID nonExistentId = UUID.randomUUID();
            when(accountService.getAccountById(nonExistentId)).thenReturn(null);

            mockMvc.perform(get("/api/accounts/{id}/balance", nonExistentId))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("POST /api/accounts - Crear Cuenta")
    class CreateAccountTests {

        @Test
        @DisplayName("Creación exitosa de cuenta")
        void createAccount_Success_ReturnsCreated() throws Exception {
            when(accountService.createAccount(any(Account.class))).thenReturn(testAccount);

            mockMvc.perform(post("/api/accounts")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(accountJson))
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.accountNumber").value("ACC-TEST-001"))
                    .andExpect(jsonPath("$.balance").value(1500.00));
        }

        @Test
        @DisplayName("Creación falla con datos inválidos")
        void createAccount_InvalidData_Returns400() throws Exception {
            String invalidJson = """
                {
                    "accountNumber": "",
                    "currency": ""
                }
                """;

            mockMvc.perform(post("/api/accounts")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(invalidJson))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("POST /api/transfers - Realizar Transferencia")
    class TransferTests {

        @Test
        @DisplayName("Transferencia exitosa")
        void transfer_Success_ReturnsAccepted() throws Exception {
            UUID targetAccountId = UUID.randomUUID();
            Transfer successfulTransfer = new Transfer(
                    this.accountId,
                    targetAccountId,
                    new BigDecimal("250.00"),
                    "USD",
                    "TEST-REF-001"
            );

            when(transferService.transfer(eq(this.accountId), eq(targetAccountId), any(), eq("USD"), eq("TEST-REF-001")))
                    .thenReturn(successfulTransfer);

            String transferPayload = """
                {
                    "sourceAccountId": "%s",
                    "targetAccountId": "%s",
                    "amount": 250.00,
                    "currency": "USD",
                    "reference": "TEST-REF-001"
                }
                """.formatted(this.accountId, targetAccountId);

            mockMvc.perform(post("/api/transfers")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(transferPayload))
                    .andExpect(status().isAccepted())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.sourceAccountId").value(this.accountId.toString()))
                    .andExpect(jsonPath("$.targetAccountId").value(targetAccountId.toString()))
                    .andExpect(jsonPath("$.amount").value(250.00))
                    .andExpect(jsonPath("$.status").value("COMPLETED"));
        }

        @Test
        @DisplayName("Transferencia falla por saldo insuficiente")
        void transfer_InsufficientBalance_Returns400() throws Exception {
            UUID targetAccountId = UUID.randomUUID();

            when(transferService.transfer(eq(this.accountId), eq(targetAccountId), any(), eq("USD"), any()))
                    .thenThrow(new com.bank.digital.domain.exception.InsufficientBalanceException("Saldo insuficiente"));

            String transferPayload = """
                {
                    "sourceAccountId": "%s",
                    "targetAccountId": "%s",
                    "amount": 5000.00,
                    "currency": "USD",
                    "reference": "TEST-REF-FAIL"
                }
                """.formatted(this.accountId, targetAccountId);

            mockMvc.perform(post("/api/transfers")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(transferPayload))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.message").exists());
        }

        @Test
        @DisplayName("Transferencia falla con monto inválido")
        void transfer_InvalidAmount_Returns400() throws Exception {
            UUID targetAccountId = UUID.randomUUID();

            String transferPayload = """
                {
                    "sourceAccountId": "%s",
                    "targetAccountId": "%s",
                    "amount": -100.00,
                    "currency": "USD",
                    "reference": "TEST-REF-INVALID"
                }
                """.formatted(this.accountId, targetAccountId);

            mockMvc.perform(post("/api/transfers")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(transferPayload))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Transferencia falla sin referencia")
        void transfer_MissingReference_Returns400() throws Exception {
            UUID targetAccountId = UUID.randomUUID();

            String transferPayload = """
                {
                    "sourceAccountId": "%s",
                    "targetAccountId": "%s",
                    "amount": 100.00,
                    "currency": "USD"
                }
                """.formatted(this.accountId, targetAccountId);

            mockMvc.perform(post("/api/transfers")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(transferPayload))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("DELETE /api/accounts/{id} - Eliminar Cuenta")
    class DeleteAccountTests {

        @Test
        @DisplayName("Eliminación exitosa de cuenta")
        void deleteAccount_Success_ReturnsNoContent() throws Exception {
            when(accountService.getAccountById(accountId)).thenReturn(testAccount);

            mockMvc.perform(delete("/api/accounts/{id}", accountId))
                    .andExpect(status().isNoContent());
        }

        @Test
        @DisplayName("Eliminación falla para cuenta inexistente")
        void deleteAccount_NotFound_Returns404() throws Exception {
            UUID nonExistentId = UUID.randomUUID();
            when(accountService.getAccountById(nonExistentId)).thenReturn(null);

            mockMvc.perform(delete("/api/accounts/{id}", nonExistentId))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("Validaciones de Formato JSON")
    class JsonFormatTests {

        @Test
        @DisplayName("Respuesta con campos nulos no incluye claves nulas")
        void response_ExcludesNullFields() throws Exception {
            Account accountWithNulls = new Account(accountId, "ACC-NULL", "USD");
            when(accountService.getAccountById(accountId)).thenReturn(accountWithNulls);

            mockMvc.perform(get("/api/accounts/{id}", accountId))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(accountId.toString()))
                    .andExpect(jsonPath("$.balance").doesNotExist());
        }

        @Test
        @DisplayName("Content-Type correcto en respuestas")
        void response_HasCorrectContentType() throws Exception {
            when(accountService.getAccountById(accountId)).thenReturn(testAccount);

            mockMvc.perform(get("/api/accounts/{id}", accountId))
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        }
    }
}