package com.bank.digital.application;

import com.bank.digital.domain.exception.CoreBankingException;
import com.bank.digital.domain.model.Account;
import com.bank.digital.domain.ports.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Caso de uso para consultar saldos y gestionar cuentas.
 * Maneja la lógica de negocio relacionada con cuentas bancarias.
 */
@Service
public class AccountService {
    
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private static final BigDecimal MINIMUM_BALANCE = BigDecimal.ZERO;
    private static final int MAX_ACCOUNTS_PER_CUSTOMER = 20;
    
    private final AccountRepository accountRepository;
    
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    
    /**
     * Consulta el saldo de una cuenta por su ID.
     *
     * @param accountId ID de la cuenta
     * @return el saldo de la cuenta
     * @throws CoreBankingException si la cuenta no existe
     */
    @Transactional(readOnly = true)
    public BigDecimal getBalanceById(UUID accountId) {
        logger.info("Consultando saldo para cuenta: {}", accountId);
        
        Optional<Account> accountOpt = accountRepository.findById(accountId);
        if (accountOpt.isEmpty()) {
            logger.error("Cuenta no encontrada: {}", accountId);
            throw new CoreBankingException(
                "Cuenta no encontrada: " + accountId,
                "ACCOUNT_NOT_FOUND",
                "GET_BALANCE"
            );
        }
        
        Account account = accountOpt.get();
        if (!account.isActive()) {
            logger.warn("Cuenta inactiva: {}", accountId);
            throw new CoreBankingException(
                "La cuenta está inactiva: " + accountId,
                "ACCOUNT_INACTIVE",
                "GET_BALANCE"
            );
        }
        
        logger.info("Saldo consultado exitosamente para cuenta {}: {}", 
            accountId, account.getBalance());
        return account.getBalance();
    }
    
    /**
     * Consulta el saldo de una cuenta por su número.
     *
     * @param accountNumber número de cuenta
     * @return el saldo de la cuenta
     * @throws CoreBankingException si la cuenta no existe
     */
    @Transactional(readOnly = true)
    public BigDecimal getBalanceByAccountNumber(String accountNumber) {
        logger.info("Consultando saldo para número de cuenta: {}", accountNumber);
        
        Optional<Account> accountOpt = accountRepository.findByAccountNumber(accountNumber);
        if (accountOpt.isEmpty()) {
            logger.error("Cuenta no encontrada con número: {}", accountNumber);
            throw new CoreBankingException(
                "Cuenta no encontrada: " + accountNumber,
                "ACCOUNT_NOT_FOUND",
                "GET_BALANCE_BY_NUMBER"
            );
        }
        
        Account account = accountOpt.get();
        logger.info("Saldo consultado para cuenta {}: {}", 
            accountNumber, account.getBalance());
        return account.getBalance();
    }
    
    /**
     * Obtiene los datos completos de una cuenta por su ID.
     *
     * @param accountId ID de la cuenta
     * @return la cuenta encontrada
     * @throws CoreBankingException si la cuenta no existe
     */
    @Transactional(readOnly = true)
    public Account getAccountById(UUID accountId) {
        logger.info("Obteniendo datos de cuenta: {}", accountId);
        
        return accountRepository.findById(accountId)
            .orElseThrow(() -> {
                logger.error("Cuenta no encontrada: {}", accountId);
                return new CoreBankingException(
                    "Cuenta no encontrada: " + accountId,
                    "ACCOUNT_NOT_FOUND",
                    "GET_ACCOUNT"
                );
            });
    }
    
    /**
     * Lista todas las cuentas activas de un cliente.
     *
     * @param customerId ID del cliente
     * @return lista de cuentas activas
     */
    @Transactional(readOnly = true)
    public List<Account> getAccountsByCustomerId(UUID customerId) {
        logger.info("Listando cuentas para cliente: {}", customerId);
        
        List<Account> accounts = accountRepository.findByCustomerId(customerId);
        
        logger.info("Se encontraron {} cuentas para el cliente: {}", 
            accounts.size(), customerId);
        return accounts;
    }
    
    /**
     * Crea una nueva cuenta bancaria.
     *
     * @param customerId ID del cliente propietario
     * @param accountNumber número de cuenta único
     * @param currency moneda de la cuenta
     * @param initialBalance saldo inicial
     * @return la cuenta creada
     * @throws CoreBankingException si el número de cuenta ya existe
     */
    @Transactional
    public Account createAccount(UUID customerId, String accountNumber, 
                                  String currency, BigDecimal initialBalance) {
        logger.info("Creando cuenta para cliente {} con número: {}", customerId, accountNumber);
        
        if (accountRepository.existsByAccountNumber(accountNumber)) {
            logger.error("El número de cuenta ya existe: {}", accountNumber);
            throw new CoreBankingException(
                "El número de cuenta ya existe: " + accountNumber,
                "DUPLICATE_ACCOUNT_NUMBER",
                "CREATE_ACCOUNT"
            );
        }
        
        List<Account> existingAccounts = accountRepository.findByCustomerId(customerId);
        if (existingAccounts.size() >= MAX_ACCOUNTS_PER_CUSTOMER) {
            logger.error("El cliente {} alcanzó el máximo de cuentas permitidas", customerId);
            throw new CoreBankingException(
                "El cliente ha alcanzado el máximo de cuentas permitidas",
                "MAX_ACCOUNTS_REACHED",
                "CREATE_ACCOUNT"
            );
        }
        
        Account account = new Account(customerId, accountNumber, currency);
        if (initialBalance != null && initialBalance.compareTo(MINIMUM_BALANCE) > 0) {
            account.deposit(initialBalance);
        }
        
        Account savedAccount = accountRepository.save(account);
        logger.info("Cuenta creada exitosamente con ID: {}", savedAccount.getId());
        return savedAccount;
    }
    
    /**
     * Realiza un depósito en una cuenta.
     *
     * @param accountId ID de la cuenta
     * @param amount monto a depositar
     * @return la cuenta actualizada
     * @throws CoreBankingException si la cuenta no existe o el monto es inválido
     */
    @Transactional
    public Account deposit(UUID accountId, BigDecimal amount) {
        logger.info("Depósito de {} en cuenta: {}", amount, accountId);
        
        validatePositiveAmount(amount, "DEPOSIT");
        
        Account account = getAccountById(accountId);
        account.deposit(amount);
        
        Account updatedAccount = accountRepository.save(account);
        logger.info("Depósito exitoso. Nuevo saldo: {}", updatedAccount.getBalance());
        return updatedAccount;
    }
    
    /**
     * Realiza un retiro de una cuenta.
     *
     * @param accountId ID de la cuenta
     * @param amount monto a retirar
     * @return la cuenta actualizada
     * @throws CoreBankingException si la cuenta no existe, está inactiva o saldo insuficiente
     */
    @Transactional
    public Account withdraw(UUID accountId, BigDecimal amount) {
        logger.info("Retiro de {} de cuenta: {}", amount, accountId);
        
        validatePositiveAmount(amount, "WITHDRAW");
        
        Account account = getAccountById(accountId);
        
        if (!account.isActive()) {
            logger.error("No se puede retirar de cuenta inactiva: {}", accountId);
            throw new CoreBankingException(
                "La cuenta está inactiva",
                "ACCOUNT_INACTIVE",
                "WITHDRAW"
            );
        }
        
        if (account.getBalance().compareTo(amount) < 0) {
            logger.error("Saldo insuficiente para retiro en cuenta: {}", accountId);
            throw new CoreBankingException(
                "Saldo insuficiente para realizar el retiro",
                "INSUFFICIENT_BALANCE",
                "WITHDRAW"
            );
        }
        
        account.withdraw(amount);
        Account updatedAccount = accountRepository.save(account);
        logger.info("Retiro exitoso. Nuevo saldo: {}", updatedAccount.getBalance());
        return updatedAccount;
    }
    
    /**
     * CIerra (desactiva) una cuenta bancaria.
     *
     * @param accountId ID de la cuenta a cerrar
     * @throws CoreBankingException si la cuenta no existe o tiene saldo
     */
    @Transactional
    public void closeAccount(UUID accountId) {
        logger.info("Cerrando cuenta: {}", accountId);
        
        Account account = getAccountById(accountId);
        
        if (account.getBalance().compareTo(MINIMUM_BALANCE) > 0) {
            logger.error("No se puede cerrar cuenta con saldo: {}", account.getBalance());
            throw new CoreBankingException(
                "No se puede cerrar una cuenta con saldo pendiente",
                "ACCOUNT_HAS_BALANCE",
                "CLOSE_ACCOUNT"
            );
        }
        
        account.close();
        accountRepository.save(account);
        logger.info("Cuenta cerrada exitosamente: {}", accountId);
    }
    
    /**
     * Valida que el monto sea positivo.
     */
    private void validatePositiveAmount(BigDecimal amount, String operation) {
        if (amount == null || amount.compareTo(MINIMUM_BALANCE) <= 0) {
            logger.error("Monto inválido para operación {}: {}", operation, amount);
            throw new CoreBankingException(
                "El monto debe ser mayor a cero",
                "INVALID_AMOUNT",
                operation
            );
        }
    }
}