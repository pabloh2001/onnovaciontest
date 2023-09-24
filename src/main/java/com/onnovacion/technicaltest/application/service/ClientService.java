package com.onnovacion.technicaltest.application.service;

import com.onnovacion.technicaltest.domain.model.Account;
import com.onnovacion.technicaltest.domain.model.Client;

import java.util.List;

public interface ClientService {
    List<Account> getAccountsByClientId(String clientId);
}
