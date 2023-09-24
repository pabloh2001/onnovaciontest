package com.onnovacion.technicaltest.adapters.postgres.repository.impl;

import com.onnovacion.technicaltest.adapters.postgres.entity.AccountEntity;
import com.onnovacion.technicaltest.adapters.postgres.mapper.AccountMapper;
import com.onnovacion.technicaltest.adapters.postgres.mapper.MovementMapper;
import com.onnovacion.technicaltest.adapters.postgres.repository.AccountRepository;
import com.onnovacion.technicaltest.application.exception.DeleteAccountException;
import com.onnovacion.technicaltest.application.exception.ResourceNotFoundException;
import com.onnovacion.technicaltest.domain.model.Account;
import com.onnovacion.technicaltest.domain.model.Movement;
import com.onnovacion.technicaltest.domain.port.AccountPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class AccountPortImpl implements AccountPort {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final MovementMapper movementMapper;

    public AccountPortImpl(AccountRepository accountRepository, AccountMapper accountMapper, MovementMapper movementMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.movementMapper = movementMapper;
    }

    @Override
    public List<Account> getAll() {
        return this.accountMapper.toAccounts(this.accountRepository.findAll());
    }

    @Override
    public List<Movement> getMovementsByAccountNumber(String accountNumber) {
        AccountEntity accountEntity = this.accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException("'accountNumber' not found"));

        return this.movementMapper.toMovements(accountEntity.getMovements());
    }

    @Override
    public Optional<Account> getById(String id) {
        return this.accountRepository.findById(id)
                .map(accountEntity -> this.accountMapper.toAccount(accountEntity));
    }

    @Override
    public Optional<Account> getByAccountNumber(String accountNumber) {
        return this.accountRepository.findByAccountNumber(accountNumber)
                .map(accountEntity -> this.accountMapper.toAccount(accountEntity));
    }

    @Override
    public Account save(Account account) {
        AccountEntity accountEntity = this.accountMapper.toAccountEntity(account);
        log.info("account -> " + accountEntity);
        return this.accountMapper.toAccount(this.accountRepository.save(accountEntity));
    }

    @Override
    public void deleteById(String id) {
        AccountEntity accountEntity = this.accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        if (accountEntity.getMovements().size() > 0) throw new DeleteAccountException("La cuenta tiene movimientos");

        this.accountRepository.deleteById(id);

    }

    @Override
    public boolean existsById(String id) {
        return this.accountRepository.existsById(id);
    }
}
