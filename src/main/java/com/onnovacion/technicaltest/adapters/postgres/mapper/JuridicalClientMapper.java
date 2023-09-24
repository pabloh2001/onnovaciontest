package com.onnovacion.technicaltest.adapters.postgres.mapper;

import com.onnovacion.technicaltest.adapters.postgres.entity.JuridicalClientEntity;
import com.onnovacion.technicaltest.domain.model.JuridicalClient;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JuridicalClientMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "businessName", target = "businessName"),
            @Mapping(source = "foundationYear", target = "foundationYear"),
            @Mapping(source = "rut", target = "rut")
    })
    JuridicalClient toJuridicalClient(JuridicalClientEntity juridicalClientEntity);
    List<JuridicalClient> toJuridicalClients(List<JuridicalClientEntity> juridicalClientEntityList);

    @InheritInverseConfiguration
    JuridicalClientEntity toJuridicalClientEntity(JuridicalClient juridicalClient);
}
