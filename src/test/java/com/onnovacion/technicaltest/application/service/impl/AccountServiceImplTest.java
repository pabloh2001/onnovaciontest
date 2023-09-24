package com.onnovacion.technicaltest.application.service.impl;

import com.onnovacion.technicaltest.application.exception.InvalidCoinTypeException;
import com.onnovacion.technicaltest.application.exception.ResourceNotFoundException;
import com.onnovacion.technicaltest.domain.model.Account;
import com.onnovacion.technicaltest.domain.model.Movement;
import com.onnovacion.technicaltest.domain.port.AccountPort;
import com.onnovacion.technicaltest.util.CoinType;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class AccountServiceImplTest {

    @Mock
    private AccountPort accountPort;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    public void shouldReturnAllAccounts() {
        List<Account> expectedAccounts = new ArrayList<>();
        when(accountPort.getAll()).thenReturn(expectedAccounts);

        List<Account> result = accountService.getAll();

        assertNotNull(result);
        assertSame(expectedAccounts, result);
    }

    @Test
    public void shouldReturnAllMovementsByAccount() {
        String id = "accoundId";
        List<Movement> expectedMovements = new ArrayList<>();
        when(accountPort.getMovementsByAccountNumber(id)).thenReturn(expectedMovements);

        List<Movement> result = accountService.getMovements(id);

        assertNotNull(result);
        assertSame(expectedMovements, result);
    }

    @Test
    public void shouldReturnAccountById() {
        String accountId = "accountId";
        Account expectedAccount = new Account();
        when(accountPort.getById(accountId)).thenReturn(Optional.of(expectedAccount));

        Account actualAccount = accountService.getById(accountId);

        assertNotNull(actualAccount);
        assertSame(expectedAccount, actualAccount);
        verify(accountPort, times(1)).getById(accountId);
    }

    @Test
    public void shouldReturnAccountByNumber() {
        String accountNumber = "123456";
        Account expectedAccount = new Account();
        when(accountPort.getByAccountNumber(accountNumber)).thenReturn(Optional.of(expectedAccount));

        Account actualAccount = accountService.getByAccountNumber(accountNumber);

        assertNotNull(actualAccount);
        assertSame(expectedAccount, actualAccount);
    }

    @Test
    public void shouldThrowResourceNotFoundExceptionWhenIdAndNumberNotFound() {
        String accountId = "accountId";
        String accountNumber = "123456";
        when(accountPort.getById(accountId)).thenReturn(Optional.empty());
        when(accountPort.getByAccountNumber(accountNumber)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> accountService.getById(accountId));
        assertThrows(ResourceNotFoundException.class, () -> accountService.getByAccountNumber(accountNumber));
    }

    @Test
    public void shouldResourceNotFoundExceptionWhenIdNotExists() {
        String accountId = "accountId";
        when(accountPort.existsById(accountId)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> accountService.update(new Account()));
        assertThrows(ResourceNotFoundException.class, () -> accountService.deleteById(accountId));
    }

    @Test
    public void shouldSavedAccount() {
        Account accountToSave = new Account();
        accountToSave.setCoinType(CoinType.COP);
        when(accountPort.save(accountToSave)).thenReturn(accountToSave);

        Account savedAccount = accountService.save(accountToSave);

        assertNotNull(savedAccount);
        assertEquals(BigDecimal.ZERO, savedAccount.getBalance());
        assertEquals(new BigDecimal("1000000"), savedAccount.getLimitBalance());
    }

    @Test
    public void shouldUpdateAccount() {
        String accountId = "accountId";
        Account accountToUpdate = new Account();
        accountToUpdate.setId(accountId);
        when(accountPort.existsById(accountId)).thenReturn(true);
        when(accountPort.save(accountToUpdate)).thenReturn(accountToUpdate);

        Account updatedAccount = accountService.update(accountToUpdate);

        assertNotNull(updatedAccount);
        assertSame(accountToUpdate, updatedAccount);
    }

    @Test
    public void shouldCalculateLimitForCoinType() {
        CoinType coinCOP = CoinType.COP;
        CoinType coinUSD = CoinType.USD;
        CoinType coinEUR = CoinType.EUR;

        BigDecimal limitCOP = accountService.calculateLimit(coinCOP);
        BigDecimal limitUSD = accountService.calculateLimit(coinUSD);
        BigDecimal limitEUR = accountService.calculateLimit(coinEUR);

        assertEquals(new BigDecimal("1000000"), limitCOP);
        assertEquals(new BigDecimal("300"), limitUSD);
        assertEquals(new BigDecimal("150"), limitEUR);
    }

    @Test
    public void shouldDeleteAccountById() {
        String accountId = "accountId";
        when(accountPort.existsById(accountId)).thenReturn(true);

        accountService.deleteById(accountId);

        verify(accountPort, times(1)).deleteById(accountId);
    }
}