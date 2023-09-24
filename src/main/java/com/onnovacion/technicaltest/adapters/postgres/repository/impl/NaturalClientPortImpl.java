package com.onnovacion.technicaltest.adapters.postgres.repository.impl;

import com.onnovacion.technicaltest.adapters.postgres.entity.NaturalClientEntity;
import com.onnovacion.technicaltest.adapters.postgres.mapper.NaturalClientMapper;
import com.onnovacion.technicaltest.adapters.postgres.repository.NaturalClientRepository;
import com.onnovacion.technicaltest.domain.model.NaturalClient;
import com.onnovacion.technicaltest.domain.port.NaturalClientPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class NaturalClientPortImpl implements NaturalClientPort {
    private final NaturalClientRepository naturalClientRepository;
    private final NaturalClientMapper naturalClientMapper;

    public NaturalClientPortImpl(NaturalClientRepository naturalClientRepository, NaturalClientMapper naturalClientMapper) {
        this.naturalClientRepository = naturalClientRepository;
        this.naturalClientMapper = naturalClientMapper;
    }

    @Override
    public List<NaturalClient> getAll() {
        return this.naturalClientMapper.toNaturalClients(this.naturalClientRepository.findAll());
    }

    @Override
    public Optional<NaturalClient> getById(String id) {
        return this.naturalClientRepository.findById(id).map(naturalClientEntity -> this.naturalClientMapper.toNaturalClient(naturalClientEntity));
    }

    @Override
    public NaturalClient save(NaturalClient naturalClient) {
        NaturalClientEntity naturalClientEntity = this.naturalClientMapper.toNaturalClientEntity(naturalClient);
        return naturalClientMapper.toNaturalClient(this.naturalClientRepository.save(naturalClientEntity));
    }

    @Override
    public boolean existsById(String id) {
        return naturalClientRepository.existsById(id);
    }

    @Override
    public void deleteById(String id) {
        this.naturalClientRepository.deleteById(id);
    }
}
