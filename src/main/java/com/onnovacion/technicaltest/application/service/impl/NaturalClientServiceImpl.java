package com.onnovacion.technicaltest.application.service.impl;

import com.onnovacion.technicaltest.application.exception.ResourceNotFoundException;
import com.onnovacion.technicaltest.application.service.NaturalClientService;
import com.onnovacion.technicaltest.domain.model.Client;
import com.onnovacion.technicaltest.domain.model.NaturalClient;
import com.onnovacion.technicaltest.domain.port.ClientPort;
import com.onnovacion.technicaltest.domain.port.NaturalClientPort;
import com.onnovacion.technicaltest.util.ClientType;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

@Service
public class NaturalClientServiceImpl implements NaturalClientService {
    private final NaturalClientPort naturalClientPort;
    private final ClientPort clientPort;

    public NaturalClientServiceImpl(NaturalClientPort naturalClientPort, ClientPort clientPort) {
        this.naturalClientPort = naturalClientPort;
        this.clientPort = clientPort;
    }

    @Override
    public List<NaturalClient> getAll() {
        return this.naturalClientPort.getAll();
    }

    @Override
    public NaturalClient getById(String id) {
        return this.naturalClientPort.getById(id).orElseThrow(() -> new ResourceNotFoundException("Client by id not found"));
    }

    @Override
    public NaturalClient save(NaturalClient naturalClient) {
        if (naturalClient.getId() != null) throw new IllegalArgumentException("'id' is not expect for this method");

        NaturalClient clientSaved = this.naturalClientPort.save(naturalClient);
        if (clientSaved != null) this.clientPort.save(new Client(clientSaved.getId(), ClientType.NATURAL));

        return clientSaved;
    }

    @Override
    public NaturalClient update(NaturalClient naturalClient) {
        if (!this.naturalClientPort.existsById(naturalClient.getId()))
            throw new ResourceNotFoundException("Client not found");

        return this.naturalClientPort.save(naturalClient);
    }



    @Override
    public void deleteById(String id) {
        if (!this.naturalClientPort.existsById(id))
            throw new ResourceNotFoundException("Client not found");

        this.clientPort.deleteById(id);
        this.naturalClientPort.deleteById(id);
    }
}
