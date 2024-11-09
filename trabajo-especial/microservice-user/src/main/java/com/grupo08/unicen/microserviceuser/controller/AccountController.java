package com.grupo08.unicen.microserviceuser.controller;

import com.grupo08.unicen.microserviceuser.entity.Account;
import com.grupo08.unicen.microserviceuser.entity.UserEntity;
import com.grupo08.unicen.microserviceuser.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private AccountService accountService;

    // dependency injection
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // basic methods
    @GetMapping
    public ResponseEntity<List<Account>> getAllUsers() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getUserById(@PathVariable UUID accountId) {
        return null;
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody Account newAccount) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Account> deleteUserById(@PathVariable UUID accountId) {
        return null;
    }

    // custom methods
    @PostMapping("/{accountId}/add-money")
    public ResponseEntity<String> addMoney(@PathVariable UUID accountId, @RequestParam Double quantity) {
        return accountService.addMoney(accountId, quantity);
    }
}
