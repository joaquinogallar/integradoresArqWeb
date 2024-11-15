package com.grupo08.unicen.microserviceuser.controller;

import com.grupo08.unicen.microserviceuser.dto.AccountDto;
import com.grupo08.unicen.microserviceuser.entity.Account;
import com.grupo08.unicen.microserviceuser.entity.UserEntity;
import com.grupo08.unicen.microserviceuser.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    // basic methods
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable UUID accountId) {
        return accountService.getAccountById(accountId);
    }

    @PostMapping
    public ResponseEntity<String> createAccount(@RequestBody AccountDto newAccount) {
        return accountService.createAccount(newAccount);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<AccountDto> deleteUserById(@PathVariable UUID accountId) {
        return accountService.deleteAccountById(accountId);
    }

    // custom methods
    @PutMapping("/{accountId}/add-money")
    public ResponseEntity<String> addMoney(@PathVariable UUID accountId, @RequestParam Double quantity) {
        return accountService.addMoney(accountId, quantity);
    }

    @PutMapping("/disable/{accountId}")
    public ResponseEntity<AccountDto>disbableAccount(@PathVariable UUID accountId){
        return accountService.disableAccount(accountId);
    }
}
