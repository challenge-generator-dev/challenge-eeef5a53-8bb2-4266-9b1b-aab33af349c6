package com.bank.digital.domain.ports;

import com.bank.digital.domain.model.Account;
import java.util.Optional;
import java.util.UUID;
import java.util.List;

public interface AccountRepository {
    Account save(Account account);

    Optional<Account> findById(UUID id);

    Optional<Account> findByAccountNumber(String accountNumber);

    List<Account> findByCustomerId(UUID customerId);

    void deleteById(UUID id);

    boolean existsByAccountNumber(String accountNumber);
}