package com.onnovacion.technicaltest.application.service.impl;

import com.onnovacion.technicaltest.application.exception.InvalidMovementException;
import com.onnovacion.technicaltest.application.service.AccountService;
import com.onnovacion.technicaltest.domain.model.Account;
import com.onnovacion.technicaltest.domain.model.Movement;
import com.onnovacion.technicaltest.domain.port.MovementPort;
import com.onnovacion.technicaltest.util.OperationType;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class MovementServiceImplTest {
    @Mock
    private MovementPort movementPort;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private MovementServiceImpl movementService;

    @Test
    void shouldSaveValidMovement() {
        Movement movement = new Movement();
        movement.setAccountId("1");
        movement.setValue(new BigDecimal("100"));
        movement.setOperationType(OperationType.DEPOSITO);

        Account account = new Account();
        account.setId("1");
        account.setBalance(new BigDecimal("500"));
        account.setLimitBalance(new BigDecimal("1000"));

        when(accountService.getById(movement.getAccountId())).thenReturn(account);
        when(movementPort.save(movement)).thenReturn(true);

        boolean saved = movementService.save(movement);

        assertTrue(saved);
        assertNotNull(movement.getId());
        assertNotNull(movement.getMovementDate());
        verify(accountService, times(1)).getById(movement.getAccountId());
        verify(movementPort, times(1)).save(movement);
    }

    @Test
    public void shouldValidateWhenInsufficientBalance() {
        Movement movement = new Movement();
        movement.setAccountId("1");
        movement.setValue(new BigDecimal("1000"));
        movement.setOperationType(OperationType.RETIRO);

        Account account = new Account();
        account.setId("1");
        account.setBalance(new BigDecimal("500"));

        when(accountService.getById(movement.getAccountId())).thenReturn(account);

        assertThrows(InvalidMovementException.class, () -> movementService.save(movement));
        verify(accountService, times(1)).getById(movement.getAccountId());
        verify(movementPort, never()).save(any(Movement.class));
    }

    @Test
    public void shouldValidateWhenExceedsLimit() {
        Movement movement = new Movement();
        movement.setAccountId("1");
        movement.setValue(new BigDecimal("1000"));
        movement.setOperationType(OperationType.DEPOSITO);

        Account account = new Account();
        account.setId("1");
        account.setBalance(new BigDecimal("500"));
        account.setLimitBalance(new BigDecimal("1000"));

        when(accountService.getById(movement.getAccountId())).thenReturn(account);

        assertThrows(InvalidMovementException.class, () -> movementService.save(movement));
        verify(accountService, times(1)).getById(movement.getAccountId());
        verify(movementPort, never()).save(any(Movement.class));
    }
}