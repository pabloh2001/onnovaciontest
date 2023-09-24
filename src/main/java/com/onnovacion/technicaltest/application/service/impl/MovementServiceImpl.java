package com.onnovacion.technicaltest.application.service.impl;

import com.onnovacion.technicaltest.application.exception.InvalidMovementException;
import com.onnovacion.technicaltest.application.service.AccountService;
import com.onnovacion.technicaltest.application.service.MovementService;
import com.onnovacion.technicaltest.domain.model.Account;
import com.onnovacion.technicaltest.domain.model.Movement;
import com.onnovacion.technicaltest.domain.port.MovementPort;
import com.onnovacion.technicaltest.util.OperationType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class MovementServiceImpl implements MovementService {
    private final MovementPort movementPort;
    private final AccountService accountService;

    public MovementServiceImpl(MovementPort movementPort, AccountService accountService) {
        this.movementPort = movementPort;
        this.accountService = accountService;
    }

    @Override
    public boolean save(Movement movement) {
        Account account = accountService.getById(movement.getAccountId());

        if (account.getBalance().compareTo(movement.getValue()) < 0 && movement.getOperationType().equals(OperationType.RETIRO))
            throw new InvalidMovementException("Insufficient balance");

        BigDecimal result = movement.getValue().add(account.getBalance());
        if (result.compareTo(account.getLimitBalance()) > 0 && movement.getOperationType().equals(OperationType.DEPOSITO))
            throw new InvalidMovementException("The value exceeds the account limit");

        movement.setId(UUID.randomUUID().toString());
        movement.setMovementDate(LocalDateTime.now());
        return this.movementPort.save(movement);
    }
}
