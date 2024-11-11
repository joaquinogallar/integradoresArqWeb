package com.grupo08.unicen.microserviceuser.controller;

import com.grupo08.unicen.microserviceuser.dto.AccountDto;
import com.grupo08.unicen.microserviceuser.entity.Account;
import com.grupo08.unicen.microserviceuser.entity.UserEntity;
import com.grupo08.unicen.microserviceuser.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/accounts")
public class AccountController {

    private AccountService accountService;

    // dependency injection
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // basic methods
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllUsers() {
        return accountService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getUserById(@PathVariable UUID accountId) {
        return accountService.getUserById(accountId);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody AccountDto newAccount) {
        return accountService.createUser(newAccount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AccountDto> deleteUserById(@PathVariable UUID accountId) {
        return accountService.deleteUserById(accountId);
    }

    // custom methods
    @PostMapping("/{accountId}/add-money")
    public ResponseEntity<String> addMoney(@PathVariable UUID accountId, @RequestParam Double quantity) {
        return accountService.addMoney(accountId, quantity);
    }
}
