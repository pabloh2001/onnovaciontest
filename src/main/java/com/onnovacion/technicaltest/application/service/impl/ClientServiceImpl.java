package com.onnovacion.technicaltest.application.service.impl;

import com.onnovacion.technicaltest.application.service.ClientService;
import com.onnovacion.technicaltest.domain.model.Account;
import com.onnovacion.technicaltest.domain.port.ClientPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientPort clientPort;

    public ClientServiceImpl(ClientPort clientPort) {
        this.clientPort = clientPort;
    }

    @Override
    public List<Account> getAccountsByClientId(String clientId) {
        return this.clientPort.accountsByClientId(clientId);
    }
}
