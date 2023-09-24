package com.onnovacion.technicaltest.domain.port;

import com.onnovacion.technicaltest.domain.model.Account;
import com.onnovacion.technicaltest.domain.model.Movement;

import java.util.List;
import java.util.Optional;

public interface AccountPort {
    List<Account> getAll();
    List<Movement> getMovementsByAccountNumber(String accountNumber);
    Optional<Account> getById(String id);
    Optional<Account> getByAccountNumber(String accountNumber);
    Account save(Account account);
    void deleteById(String id);
    boolean existsById(String id);
}
