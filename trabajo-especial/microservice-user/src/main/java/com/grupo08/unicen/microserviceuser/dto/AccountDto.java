package com.grupo08.unicen.microserviceuser.dto;

import com.grupo08.unicen.microserviceuser.entity.Account;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountDto {
    private String name;
    private LocalDateTime createdAt;
    private Double balance;

    public AccountDto(Account account) {
        this.name = account.getName();
        this.createdAt = account.getCreatedAt();
        this.balance = account.getBalance();
    }

    public AccountDto(String name) {
        this.name = name;
        this.createdAt = LocalDateTime.now();
        this.balance = 0.0;
    }
}
