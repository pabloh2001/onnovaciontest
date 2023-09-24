package com.onnovacion.technicaltest.adapters.postgres.mapper;

import com.onnovacion.technicaltest.adapters.postgres.entity.ClientEntity;
import com.onnovacion.technicaltest.domain.model.Client;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mappings({
            @Mapping(source = "clientId", target = "clientId"),
            @Mapping(source = "clientType", target = "clientType"),
            @Mapping(source = "naturalClientDetail", target = "naturalClientDetail"),
            @Mapping(source = "juridicalClientDetail", target = "juridicalClientDetail")
    })
    Client toClient(ClientEntity clientEntity);

    @InheritInverseConfiguration
    @Mapping(target = "naturalClient", ignore = true)
    @Mapping(target = "juridicalClient", ignore = true)
    @Mapping(target = "accounts", ignore = true)
    ClientEntity toClientEntity(Client client);
}
