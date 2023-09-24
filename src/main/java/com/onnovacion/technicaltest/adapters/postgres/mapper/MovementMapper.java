package com.onnovacion.technicaltest.adapters.postgres.mapper;

import com.onnovacion.technicaltest.adapters.postgres.entity.MovementEntity;
import com.onnovacion.technicaltest.domain.model.Movement;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovementMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "accountId", target = "accountId"),
            @Mapping(source = "value", target = "value"),
            @Mapping(source = "operationType", target = "operationType"),
            @Mapping(source = "movementDate", target = "movementDate")
    })
    Movement toMovement(MovementEntity movementEntity);
    List<Movement> toMovements(List<MovementEntity> movementEntityList);
    @InheritInverseConfiguration
    @Mapping(target = "account", ignore = true)
    MovementEntity toMovementEntity(Movement movement);
}
