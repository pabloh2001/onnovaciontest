package com.onnovacion.technicaltest.adapters.postgres.repository.impl;

import com.onnovacion.technicaltest.adapters.postgres.mapper.MovementMapper;
import com.onnovacion.technicaltest.adapters.postgres.repository.MovementRepository;
import com.onnovacion.technicaltest.domain.model.Movement;
import com.onnovacion.technicaltest.domain.port.MovementPort;
import org.springframework.stereotype.Repository;

@Repository
public class MovementPortImpl implements MovementPort {
    private final MovementRepository movementRepository;
    private final MovementMapper movementMapper;

    public MovementPortImpl(MovementRepository movementRepository, MovementMapper movementMapper) {
        this.movementRepository = movementRepository;
        this.movementMapper = movementMapper;
    }

    @Override
    public boolean save(Movement movement) {
        try {
            this.movementRepository.saveMovement(movement.getId(), movement.getAccountId(), movement.getValue(), movement.getOperationType().toString(), movement.getMovementDate());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
