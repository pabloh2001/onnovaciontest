package com.onnovacion.technicaltest.adapters.postgres.repository.impl;

import com.onnovacion.technicaltest.adapters.postgres.entity.ClientEntity;
import com.onnovacion.technicaltest.adapters.postgres.mapper.AccountMapper;
import com.onnovacion.technicaltest.adapters.postgres.mapper.ClientMapper;
import com.onnovacion.technicaltest.adapters.postgres.repository.ClientRepository;
import com.onnovacion.technicaltest.application.exception.ResourceNotFoundException;
import com.onnovacion.technicaltest.domain.model.Account;
import com.onnovacion.technicaltest.domain.model.Client;
import com.onnovacion.technicaltest.domain.port.ClientPort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientPortImpl implements ClientPort {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final AccountMapper accountMapper;

    public ClientPortImpl(ClientRepository clientRepository, ClientMapper clientMapper, AccountMapper accountMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
        this.accountMapper = accountMapper;
    }

    @Override
    public List<Account> accountsByClientId(String clientId) {
        ClientEntity client = this.clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("'clientId' not found"));
        return accountMapper.toAccounts(client.getAccounts());
    }

    @Override
    public void save(Client client) {
        ClientEntity clientEntity = this.clientMapper.toClientEntity(client);
        this.clientRepository.save(clientEntity);
    }

    @Override
    public void deleteById(String id) {
        this.clientRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return this.clientRepository.existsById(id);
    }
}
