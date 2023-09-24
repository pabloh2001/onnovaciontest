package com.onnovacion.technicaltest.adapters.web.controller;

import com.onnovacion.technicaltest.application.service.ClientService;
import com.onnovacion.technicaltest.application.service.JuridicalClientService;
import com.onnovacion.technicaltest.application.service.NaturalClientService;
import com.onnovacion.technicaltest.domain.model.Account;
import com.onnovacion.technicaltest.domain.model.JuridicalClient;
import com.onnovacion.technicaltest.domain.model.NaturalClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/clients")
public class ClientController {
    private final NaturalClientService naturalClientService;
    private final JuridicalClientService juridicalClientService;
    private final ClientService clientService;

    public ClientController(NaturalClientService naturalClientService, JuridicalClientService juridicalClientService, ClientService clientService) {
        this.naturalClientService = naturalClientService;
        this.juridicalClientService = juridicalClientService;
        this.clientService = clientService;
    }

    @GetMapping("/{clientId}/accounts")
    public ResponseEntity<List<Account>> allAccountsByClientId(@PathVariable String clientId) {
        return new ResponseEntity<>(this.clientService.getAccountsByClientId(clientId), HttpStatus.OK);
    }

    @GetMapping("/natural")
    public ResponseEntity<List<NaturalClient>> allNaturalClients(){
        return new ResponseEntity<>(this.naturalClientService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/natural/{id}")
    public ResponseEntity<NaturalClient> clientById(@PathVariable String id){
        return new ResponseEntity<>(this.naturalClientService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/natural")
    public ResponseEntity<NaturalClient> saveNaturalClient(@RequestBody NaturalClient naturalClient) {
        return new ResponseEntity<>(this.naturalClientService.save(naturalClient), HttpStatus.CREATED);
    }

    @PutMapping("/natural")
    public ResponseEntity<NaturalClient> updateNaturalClient(@RequestBody NaturalClient naturalClient) {
        return new ResponseEntity<>(this.naturalClientService.update(naturalClient), HttpStatus.OK);
    }

    @DeleteMapping("/natural/{id}")
    public ResponseEntity deleteNatClientById(@PathVariable String id) {
        this.naturalClientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/juridical")
    public ResponseEntity<List<JuridicalClient>> allJuridicalClients(){
        return new ResponseEntity<>(this.juridicalClientService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/juridical/{id}")
    public ResponseEntity<JuridicalClient> juridicalClientById(@PathVariable String id){
        return new ResponseEntity<>(this.juridicalClientService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/juridical")
    public ResponseEntity<JuridicalClient> saveJuridicalClient(@RequestBody JuridicalClient juridicalClient) {
        return new ResponseEntity<>(this.juridicalClientService.save(juridicalClient), HttpStatus.CREATED);
    }

    @PutMapping("/juridical")
    public ResponseEntity<JuridicalClient> updateJuridicalClient(@RequestBody JuridicalClient juridicalClient) {
        return new ResponseEntity<>(this.juridicalClientService.update(juridicalClient), HttpStatus.OK);
    }

    @DeleteMapping("/juridical/{id}")
    public ResponseEntity deleteJurClientById(@PathVariable String id) {
        this.juridicalClientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
