package com.onnovacion.technicaltest.application.service.impl;

import com.onnovacion.technicaltest.application.exception.ResourceNotFoundException;
import com.onnovacion.technicaltest.domain.model.Client;
import com.onnovacion.technicaltest.domain.model.JuridicalClient;
import com.onnovacion.technicaltest.domain.port.ClientPort;
import com.onnovacion.technicaltest.domain.port.JuridicalClientPort;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class JuridicalClientServiceImplTest {

    @Mock
    private JuridicalClientPort juridicalClientPort;

    @Mock
    private ClientPort clientPort;

    @InjectMocks
    private JuridicalClientServiceImpl juridicalClientService;

    @Test
    void shouldReturnAllJuridicalClients() {
        List<JuridicalClient> expectedClients = new ArrayList<>();
        when(juridicalClientPort.getAll()).thenReturn(expectedClients);

        List<JuridicalClient> resultClients = juridicalClientService.getAll();

        assertNotNull(resultClients);
        assertSame(expectedClients, resultClients);
    }

    @Test
    void shouldReturnJuridicalClientById() {
        JuridicalClient expectedClient = new JuridicalClient();
        String id = UUID.randomUUID().toString();
        when(juridicalClientPort.getById(id)).thenReturn(Optional.of(expectedClient));

        JuridicalClient juridicalClient = juridicalClientService.getById(id);

        assertNotNull(juridicalClient);
        assertSame(expectedClient, juridicalClient);
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenIdNotFound() {
        String clientId = "clientId";
        when(juridicalClientPort.getById(clientId)).thenReturn(Optional.empty());

        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class,
                () -> juridicalClientService.getById(clientId));

        assertEquals("Client not found", ex.getMessage());
    }

    @Test
    void shouldReturnNewJuridicalClient() {
        JuridicalClient clientToSave = new JuridicalClient();

        when(juridicalClientPort.save(any(JuridicalClient.class))).thenReturn(clientToSave);

        JuridicalClient savedClient = juridicalClientService.save(clientToSave);

        assertSame(clientToSave, savedClient);

        verify(clientPort, times(1)).save(any(Client.class));
    }

    @Test
    void shouldUpdateJuridicalClient() {
        String id = "clientId";
        JuridicalClient clientToUpdate = new JuridicalClient();
        clientToUpdate.setId(id);

        when(juridicalClientPort.existsById(id)).thenReturn(true);
        when(juridicalClientPort.save(clientToUpdate)).thenReturn(clientToUpdate);

        JuridicalClient updatedClient = juridicalClientService.update(clientToUpdate);

        assertNotNull(updatedClient);
        assertEquals(id, updatedClient.getId());
        assertSame(updatedClient, updatedClient);
        verify(juridicalClientPort, times(1)).save(updatedClient);
    }

    @Test
    public void shouldThrowResourceNotFoundExceptionWhenExistsByIdIsFalse() {
        String clientId = "clientId";
        JuridicalClient updatedClient = new JuridicalClient();
        updatedClient.setId(clientId);
        when(juridicalClientPort.existsById(clientId)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> juridicalClientService.update(updatedClient));
        assertThrows(ResourceNotFoundException.class, () -> juridicalClientService.deleteById(clientId));
    }

    @Test
    public void shouldDeleteJuridicalClientById() {
        String clientId = "clientId";
        when(juridicalClientPort.existsById(clientId)).thenReturn(true);
        Mockito.doNothing().when(juridicalClientPort).deleteById(clientId);

        juridicalClientService.deleteById(clientId);

        verify(juridicalClientPort, times(1)).deleteById(clientId);

        verify(clientPort, times(1)).deleteById(clientId);
    }
}