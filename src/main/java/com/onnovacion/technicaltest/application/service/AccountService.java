package com.onnovacion.technicaltest.application.service;

import com.onnovacion.technicaltest.domain.model.Account;
import com.onnovacion.technicaltest.domain.model.Movement;

import java.util.List;

public interface AccountService {
    List<Account> getAll();
    List<Movement> getMovements(String accountNumber);
    Account getById(String id);
    Account getByAccountNumber(String accountNumber);
    Account save(Account account);
    Account update(Account account);
    void deleteById(String id);
}
