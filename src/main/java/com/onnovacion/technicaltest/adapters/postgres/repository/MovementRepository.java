package com.onnovacion.technicaltest.adapters.postgres.repository;

import com.onnovacion.technicaltest.adapters.postgres.entity.MovementEntity;
import com.onnovacion.technicaltest.util.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface MovementRepository extends JpaRepository<MovementEntity, String> {
    @Transactional
    @Modifying
    @Query(value = "call record_movement_and_update_balance(:p_id, :p_account_id, :p_value, :p_operation_type, :p_movement_date)", nativeQuery = true)
    void saveMovement (
            @Param(value = "p_id") String id,
            @Param(value = "p_account_id") String accountId,
            @Param(value = "p_value")BigDecimal value,
            @Param(value = "p_operation_type")String operationType,
            @Param(value = "p_movement_date")LocalDateTime movementDate
    );
}
