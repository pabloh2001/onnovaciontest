package com.onnovacion.technicaltest.application.service;

import com.onnovacion.technicaltest.domain.model.JuridicalClient;

import java.util.List;

public interface JuridicalClientService {
    List<JuridicalClient> getAll();
    JuridicalClient getById(String id);
    JuridicalClient save(JuridicalClient juridicalClient);
    JuridicalClient update(JuridicalClient juridicalClient);
    void deleteById(String id);
}
