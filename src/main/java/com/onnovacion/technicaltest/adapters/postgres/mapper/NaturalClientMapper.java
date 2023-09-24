package com.onnovacion.technicaltest.adapters.postgres.mapper;

import com.onnovacion.technicaltest.adapters.postgres.entity.NaturalClientEntity;
import com.onnovacion.technicaltest.domain.model.NaturalClient;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NaturalClientMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "documentType", target = "documentType"),
            @Mapping(source = "documentNumber", target = "documentNumber"),
            @Mapping(source = "rut", target = "rut")
    })
    NaturalClient toNaturalClient(NaturalClientEntity naturalClientEntity);
    List<NaturalClient> toNaturalClients(List<NaturalClientEntity> naturalClientEntityList);

    @InheritInverseConfiguration
    NaturalClientEntity toNaturalClientEntity(NaturalClient naturalClient);
}
