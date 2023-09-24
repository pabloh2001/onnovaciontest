package com.onnovacion.technicaltest.domain.port;

import com.onnovacion.technicaltest.domain.model.NaturalClient;

import java.util.List;
import java.util.Optional;

public interface NaturalClientPort {
    List<NaturalClient> getAll();
    Optional<NaturalClient> getById(String id);
    NaturalClient save(NaturalClient naturalClient);
    boolean existsById(String id);
    void deleteById(String id);
}
