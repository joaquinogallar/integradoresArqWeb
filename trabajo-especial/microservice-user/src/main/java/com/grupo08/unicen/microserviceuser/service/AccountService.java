package com.grupo08.unicen.microserviceuser.service;

import com.grupo08.unicen.microserviceuser.entity.Account;
import com.grupo08.unicen.microserviceuser.exception.AccountNotFoundException;
import com.grupo08.unicen.microserviceuser.repository.AccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    // dependency injection
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Adds a specified quantity of money to the balance.
     *
     * @param accountId user id
     * @param quantity the amount of money to add; must be positive
     * @return a message indicating a successful transaction
     * @throws IllegalArgumentException if the quantity is negative
     */
    public ResponseEntity<String> addMoney(UUID accountId, Double quantity) {
        if(quantity <= 0) throw new IllegalArgumentException("The quantity can't be negative");
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException(accountId.toString()));

        account.setBalance(account.getBalance() + quantity);
        accountRepository.save(account);

        return ResponseEntity.ok("Successful transaction. $" + quantity + " were added to account " + accountId);
    }
}
