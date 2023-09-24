package com.onnovacion.technicaltest.domain.port;

import com.onnovacion.technicaltest.domain.model.JuridicalClient;

import java.util.List;
import java.util.Optional;

public interface JuridicalClientPort {
    List<JuridicalClient> getAll();
    Optional<JuridicalClient> getById(String id);
    JuridicalClient save(JuridicalClient juridicalClient);
    void deleteById (String id);
    boolean existsById(String id);
}
