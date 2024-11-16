package com.grupo08.unicen.microserviceuser.service;

import com.grupo08.unicen.microserviceuser.dto.AccountDto;
import com.grupo08.unicen.microserviceuser.entity.Account;
import com.grupo08.unicen.microserviceuser.exception.AccountNotFoundException;
import com.grupo08.unicen.microserviceuser.exception.UserNotFoundException;
import com.grupo08.unicen.microserviceuser.repository.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        try {
            List<Account> accounts = accountRepository.findAll();
            List<AccountDto> accountDtos = new ArrayList<>();
            accounts.forEach(a -> accountDtos.add(new AccountDto(a.getId(), a.getName(), a.getCreatedAt(), a.getBalance())));
            return ResponseEntity.ok(accountDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    public ResponseEntity<AccountDto> getAccountById(UUID accountId) {
        Account a = accountRepository.findById(accountId)
                .orElseThrow(() -> new UserNotFoundException(accountId.toString()));
        return ResponseEntity.ok(new AccountDto(a.getId(), a.getName(), a.getCreatedAt(), a.getBalance()));
    }

    public String createAccount(AccountDto newAccount) {
            Account account = new Account(newAccount);
            accountRepository.save(account);
            return "User created successfully";

    }

    public ResponseEntity<AccountDto> deleteAccountById(UUID accountId) {
        Account a = accountRepository.findById(accountId)
                .orElseThrow(() -> new UserNotFoundException(accountId.toString()));
        accountRepository.delete(a);

        return ResponseEntity.ok(new AccountDto(a.getId(), a.getName(), a.getCreatedAt(), a.getBalance()));
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

    public ResponseEntity<AccountDto> disableAccount(UUID accountId) {
        try {
           Account a = accountRepository.findById(accountId).orElse(null);
            if(a!=null){
                a.setEnabled(false);
                accountRepository.save(a);
                return ResponseEntity.ok(new AccountDto(a.getName()));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(null);
        } return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(null);
    }

}
