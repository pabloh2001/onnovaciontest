package com.onnovacion.technicaltest.application.service;

import com.onnovacion.technicaltest.domain.model.NaturalClient;

import java.util.List;

public interface NaturalClientService {
    List<NaturalClient> getAll();
    NaturalClient getById(String id);
    NaturalClient save(NaturalClient naturalClient);
    NaturalClient update(NaturalClient naturalClient);
    void deleteById(String id);
}
