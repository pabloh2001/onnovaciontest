package com.onnovacion.technicaltest.domain.port;

import com.onnovacion.technicaltest.domain.model.Account;
import com.onnovacion.technicaltest.domain.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientPort {
    List<Account> accountsByClientId(String clientId);
    Client save(Client client);
    void deleteById(String id);
    boolean existsById(String id);
}
