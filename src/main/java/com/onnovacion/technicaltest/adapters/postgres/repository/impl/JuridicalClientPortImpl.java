package com.onnovacion.technicaltest.adapters.postgres.repository.impl;

import com.onnovacion.technicaltest.adapters.postgres.entity.JuridicalClientEntity;
import com.onnovacion.technicaltest.adapters.postgres.mapper.JuridicalClientMapper;
import com.onnovacion.technicaltest.adapters.postgres.repository.JuridicalClientRepository;
import com.onnovacion.technicaltest.domain.model.JuridicalClient;
import com.onnovacion.technicaltest.domain.port.JuridicalClientPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JuridicalClientPortImpl implements JuridicalClientPort {
    private final JuridicalClientRepository juridicalClientRepository;
    private final JuridicalClientMapper juridicalClientMapper;

    public JuridicalClientPortImpl(JuridicalClientRepository juridicalClientRepository, JuridicalClientMapper juridicalClientMapper) {
        this.juridicalClientRepository = juridicalClientRepository;
        this.juridicalClientMapper = juridicalClientMapper;
    }

    @Override
    public List<JuridicalClient> getAll() {
        return juridicalClientMapper.toJuridicalClients(this.juridicalClientRepository.findAll());
    }

    @Override
    public Optional<JuridicalClient> getById(String id) {
        return this.juridicalClientRepository.findById(id)
                .map(juridicalClientEntity -> this.juridicalClientMapper.toJuridicalClient(juridicalClientEntity));
    }

    @Override
    public JuridicalClient save(JuridicalClient juridicalClient) {
        JuridicalClientEntity juridicalClientEntity = this.juridicalClientMapper.toJuridicalClientEntity(juridicalClient);
        return juridicalClientMapper.toJuridicalClient(this.juridicalClientRepository.save(juridicalClientEntity));
    }

    @Override
    public void deleteById(String id) {
        this.juridicalClientRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return juridicalClientRepository.existsById(id);
    }
}
