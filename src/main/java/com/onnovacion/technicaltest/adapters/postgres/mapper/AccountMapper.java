package com.onnovacion.technicaltest.adapters.postgres.mapper;

import com.onnovacion.technicaltest.adapters.postgres.entity.AccountEntity;
import com.onnovacion.technicaltest.domain.model.Account;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "accountNumber", target = "accountNumber"),
            @Mapping(source = "coinType", target = "coinType"),
            @Mapping(source = "balance", target = "balance"),
            @Mapping(source = "limitBalance", target = "limitBalance"),
            @Mapping(source = "clientId", target = "clientId")
    })
    Account toAccount(AccountEntity accountEntity);
    List<Account> toAccounts(List<AccountEntity> accountEntityList);

    @InheritInverseConfiguration
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "movements", ignore = true)
    AccountEntity toAccountEntity(Account account);
}
