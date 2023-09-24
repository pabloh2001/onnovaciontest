package com.onnovacion.technicaltest.adapters.web.controller;

import com.onnovacion.technicaltest.application.service.AccountService;
import com.onnovacion.technicaltest.domain.model.Account;
import com.onnovacion.technicaltest.domain.model.Movement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<Account>> allAccounts() {
        return new ResponseEntity<>(this.accountService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{number}")
    public ResponseEntity<Account> accountByNumber(@PathVariable String number) {
        return new ResponseEntity<>(this.accountService.getByAccountNumber(number), HttpStatus.OK);
    }

    @GetMapping("/{number}/movements")
    public ResponseEntity<List<Movement>> movementsByAccount(@PathVariable String number) {
        return new ResponseEntity<>(this.accountService.getMovements(number), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Account> saveAccount(@RequestBody Account account) {
        return new ResponseEntity<>(this.accountService.save(account), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Account> updateAccount(@RequestBody Account account) {
        return new ResponseEntity<>(this.accountService.update(account), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAccountById(@PathVariable String id) {
        this.accountService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
