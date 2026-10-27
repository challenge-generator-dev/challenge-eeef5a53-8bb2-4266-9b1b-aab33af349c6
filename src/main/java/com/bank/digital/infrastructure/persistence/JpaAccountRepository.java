package com.bank.digital.infrastructure.persistence;

import com.bank.digital.domain.model.Account;
import com.bank.digital.domain.ports.AccountRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementación del repositorio de cuentas utilizando Spring Data JPA.
 * Persiste y recupera cuentas desde la base de datos PostgreSQL.
 */
@Repository
@Transactional
public class JpaAccountRepository implements AccountRepository {
    
    private static final Logger logger = LoggerFactory.getLogger(JpaAccountRepository.class);
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public Account save(Account account) {
        if (account.getId() == null) {
            account.setId(UUID.randomUUID());
            account.setCreatedAt(LocalDateTime.now());
            entityManager.persist(account);
            logger.info("Cuenta creada exitosamente: {} para cliente: {}", 
                account.getAccountNumber(), account.getCustomerId());
        } else {
            account.setLastUpdated(LocalDateTime.now());
            account = entityManager.merge(account);
            logger.info("Cuenta actualizada exitosamente: {}", account.getAccountNumber());
        }
        return account;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Account> findById(UUID id) {
        logger.debug("Buscando cuenta por ID: {}", id);
        Account account = entityManager.find(Account.class, id);
        return Optional.ofNullable(account);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Account> findByAccountNumber(String accountNumber) {
        logger.debug("Buscando cuenta por número: {}", accountNumber);
        Query query = entityManager.createQuery(
            "SELECT a FROM Account a WHERE a.accountNumber = :accountNumber AND a.active = true",
            Account.class
        );
        query.setParameter("accountNumber", accountNumber);
        
        try {
            Account account = (Account) query.getSingleResult();
            return Optional.of(account);
        } catch (jakarta.persistence.NoResultException e) {
            logger.warn("No se encontró cuenta activa con número: {}", accountNumber);
            return Optional.empty();
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Account> findByCustomerId(UUID customerId) {
        logger.debug("Buscando cuentas para cliente: {}", customerId);
        Query query = entityManager.createQuery(
            "SELECT a FROM Account a WHERE a.customerId = :customerId AND a.active = true ORDER BY a.createdAt DESC",
            Account.class
        );
        query.setParameter("customerId", customerId);
        
        List<Account> accounts = query.getResultList();
        logger.info("Se encontraron {} cuentas activas para el cliente: {}", accounts.size(), customerId);
        return accounts;
    }
    
    @Override
    public void deleteById(UUID id) {
        logger.info("Marcando cuenta como inactiva: {}", id);
        Account account = entityManager.find(Account.class, id);
        if (account != null) {
            account.setActive(false);
            account.setLastUpdated(LocalDateTime.now());
            entityManager.merge(account);
            logger.info("Cuenta marcada como inactiva: {}", id);
        } else {
            logger.warn("No se encontró cuenta para eliminar: {}", id);
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsByAccountNumber(String accountNumber) {
        logger.debug("Verificando existencia de cuenta: {}", accountNumber);
        Query query = entityManager.createQuery(
            "SELECT COUNT(a) FROM Account a WHERE a.accountNumber = :accountNumber AND a.active = true",
            Long.class
        );
        query.setParameter("accountNumber", accountNumber);
        
        Long count = (Long) query.getSingleResult();
        boolean exists = count > 0;
        logger.debug("La cuenta {} existe: {}", accountNumber, exists);
        return exists;
    }
    
    /**
     * Actualiza el saldo de una cuenta de forma atómica.
     * Utiliza una consulta de actualización para garantizar consistencia.
     *
     * @param accountId ID de la cuenta
     * @param newBalance nuevo saldo
     * @return número de filas afectadas
     */
    @Transactional
    public int updateBalance(UUID accountId, BigDecimal newBalance) {
        logger.info("Actualizando saldo de cuenta {} a {}", accountId, newBalance);
        Query query = entityManager.createQuery(
            "UPDATE Account a SET a.balance = :balance, a.lastUpdated = :lastUpdated " +
            "WHERE a.id = :id AND a.active = true"
        );
        query.setParameter("balance", newBalance);
        query.setParameter("lastUpdated", LocalDateTime.now());
        query.setParameter("id", accountId);
        
        int updated = query.executeUpdate();
        logger.info("Filas actualizadas: {}", updated);
        return updated;
    }
    
    /**
     * Realiza una operación atómica de débito en la cuenta.
     * Verifica que el saldo sea suficiente antes de debitar.
     *
     * @param accountId ID de la cuenta
     * @param amount monto a debitar
     * @return true si la operación fue exitosa, false si no hay saldo suficiente
     */
    @Transactional
    public boolean debitAccount(UUID accountId, BigDecimal amount) {
        logger.info("Debitando {} de la cuenta {}", amount, accountId);
        
        Account account = entityManager.find(Account.class, accountId);
        if (account == null || !account.isActive()) {
            logger.error("Cuenta no encontrada o inactiva: {}", accountId);
            return false;
        }
        
        if (account.getBalance().compareTo(amount) < 0) {
            logger.warn("Saldo insuficiente para cuenta {}: saldo={}, solicitado={}", 
                accountId, account.getBalance(), amount);
            return false;
        }
        
        account.withdraw(amount);
        account.setLastUpdated(LocalDateTime.now());
        entityManager.merge(account);
        
        logger.info("Débito exitoso. Nuevo saldo: {}", account.getBalance());
        return true;
    }
    
    /**
     * Realiza una operación atómica de crédito en la cuenta.
     *
     * @param accountId ID de la cuenta
     * @param amount monto a acreditar
     * @return true si la operación fue exitosa
     */
    @Transactional
    public boolean creditAccount(UUID accountId, BigDecimal amount) {
        logger.info("Acreditando {} a la cuenta {}", amount, accountId);
        
        Account account = entityManager.find(Account.class, accountId);
        if (account == null || !account.isActive()) {
            logger.error("Cuenta no encontrada o inactiva: {}", accountId);
            return false;
        }
        
        account.deposit(amount);
        account.setLastUpdated(LocalDateTime.now());
        entityManager.merge(account);
        
        logger.info("Crédito exitoso. Nuevo saldo: {}", account.getBalance());
        return true;
    }
}