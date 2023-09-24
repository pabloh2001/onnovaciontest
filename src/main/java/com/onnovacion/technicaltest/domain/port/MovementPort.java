package com.onnovacion.technicaltest.domain.port;

import com.onnovacion.technicaltest.domain.model.Movement;

public interface MovementPort {
    boolean save(Movement movement);
}
