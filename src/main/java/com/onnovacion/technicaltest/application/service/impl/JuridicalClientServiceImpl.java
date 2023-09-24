package com.onnovacion.technicaltest.application.service.impl;

import com.onnovacion.technicaltest.application.exception.ResourceNotFoundException;
import com.onnovacion.technicaltest.application.service.JuridicalClientService;
import com.onnovacion.technicaltest.domain.model.Client;
import com.onnovacion.technicaltest.domain.model.JuridicalClient;
import com.onnovacion.technicaltest.domain.port.ClientPort;
import com.onnovacion.technicaltest.domain.port.JuridicalClientPort;
import com.onnovacion.technicaltest.util.ClientType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JuridicalClientServiceImpl implements JuridicalClientService {
    private final JuridicalClientPort juridicalClientPort;
    private final ClientPort clientPort;

    public JuridicalClientServiceImpl(JuridicalClientPort juridicalClientPort, ClientPort clientPort) {
        this.juridicalClientPort = juridicalClientPort;
        this.clientPort = clientPort;
    }

    @Override
    public List<JuridicalClient> getAll() {
        return this.juridicalClientPort.getAll();
    }

    @Override
    public JuridicalClient getById(String id) {
        return this.juridicalClientPort.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
    }

    @Override
    public JuridicalClient save(JuridicalClient juridicalClient) {
        JuridicalClient clientSaved = this.juridicalClientPort.save(juridicalClient);
        if (clientSaved != null) this.clientPort.save(new Client(clientSaved.getId(), ClientType.JURIDICA));

        return clientSaved;
    }

    @Override
    public JuridicalClient update(JuridicalClient juridicalClient) {
        if (!this.juridicalClientPort.existsById(juridicalClient.getId()))
            throw new ResourceNotFoundException("Client not found");

        return this.juridicalClientPort.save(juridicalClient);
    }

    @Override
    public void deleteById(String id) {
        if (!this.juridicalClientPort.existsById(id))
            throw new ResourceNotFoundException("Client not found");
        this.clientPort.deleteById(id);
        this.juridicalClientPort.deleteById(id);
    }
}
