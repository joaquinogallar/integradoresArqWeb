package com.grupo08.unicen.microserviceuser.service;

import com.grupo08.unicen.microserviceuser.entity.Account;
import com.grupo08.unicen.microserviceuser.exception.AccountNotFoundException;
import com.grupo08.unicen.microserviceuser.exception.UserNotFoundException;
import com.grupo08.unicen.microserviceuser.repository.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    // dependency injection
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // basic methods
    public ResponseEntity<List<Account>> getAllUsers() {
        try {
            List<Account> users = accountRepository.findAll();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    public ResponseEntity<Account> getUserById(UUID accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new UserNotFoundException(accountId.toString()));
        return ResponseEntity.ok(account);
    }

    public ResponseEntity<String> createUser(Account newUser) {
        try {
            accountRepository.save(newUser);
            return ResponseEntity.ok("User created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("Error: " + e);
        }
    }

    public ResponseEntity<Account> deleteUserById(UUID accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new UserNotFoundException(accountId.toString()));
        accountRepository.delete(account);

        return ResponseEntity.ok(account);
    }

    /**
     * Adds a specified quantity of money to the balance.
     *
     * @param accountId account id
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
