package com.onnovacion.technicaltest.application.service;

import com.onnovacion.technicaltest.application.dto.RespJuridicalClientDTO;
import com.onnovacion.technicaltest.domain.model.JuridicalClient;

import java.util.List;

public interface JuridicalClientService {
    List<JuridicalClient> getAll();
    JuridicalClient getById(String id);
    RespJuridicalClientDTO save(JuridicalClient juridicalClient);
    JuridicalClient update(JuridicalClient juridicalClient);
    void deleteById(String id);
}
