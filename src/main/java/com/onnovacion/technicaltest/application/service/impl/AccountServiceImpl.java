package com.onnovacion.technicaltest.application.service.impl;

import com.onnovacion.technicaltest.application.exception.InvalidCoinTypeException;
import com.onnovacion.technicaltest.application.exception.ResourceNotFoundException;
import com.onnovacion.technicaltest.application.service.AccountService;
import com.onnovacion.technicaltest.domain.model.Account;
import com.onnovacion.technicaltest.domain.model.Movement;
import com.onnovacion.technicaltest.domain.port.AccountPort;
import com.onnovacion.technicaltest.util.CoinType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountPort accountPort;

    public AccountServiceImpl(AccountPort accountPort) {
        this.accountPort = accountPort;
    }

    @Override
    public List<Account> getAll() {
        return this.accountPort.getAll();
    }

    @Override
    public List<Movement> getMovements(String accountNumber) {
        return this.accountPort.getMovementsByAccountNumber(accountNumber);
    }

    @Override
    public Account getById(String id) {
        return this.accountPort.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
    }

    @Override
    public Account getByAccountNumber(String accountNumber) {
        return this.accountPort.getByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
    }

    @Override
    public Account save(Account account) {
        BigDecimal limit = this.calculateLimit(account.getCoinType());

        account.setBalance(BigDecimal.ZERO);
        account.setLimitBalance(limit);
        return this.accountPort.save(account);
    }

    @Override
    public Account update(Account account) {
        if (!this.accountPort.existsById(account.getId()))
            throw new ResourceNotFoundException("Account not found");
        return this.accountPort.save(account);
    }

    @Override
    public void deleteById(String id) {
        if (!this.accountPort.existsById(id))
            throw new ResourceNotFoundException("Account not found");
        this.accountPort.deleteById(id);
    }

    public BigDecimal calculateLimit(CoinType coinType) {
        BigDecimal result;
        switch (coinType) {
            case COP:
                result = new BigDecimal("1000000");
                break;
            case USD:
                result = new BigDecimal("300");
                break;
            case EUR:
                result = new BigDecimal("150");
                break;
            default:
                throw new InvalidCoinTypeException("Coin type not valid");
        }

        return result;
    }
}
