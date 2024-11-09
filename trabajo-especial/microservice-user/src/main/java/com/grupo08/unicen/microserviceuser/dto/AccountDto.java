package com.grupo08.unicen.microserviceuser.dto;

import com.grupo08.unicen.microserviceuser.entity.Account;

import java.time.LocalDateTime;

public class AccountDto {
    private LocalDateTime createdAt;
    private Double balance;

    public AccountDto(Account account) {
        this.createdAt = account.getCreatedAt();
        this.balance = account.getBalance();
    }
}
