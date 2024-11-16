package com.grupo08.unicen.microservicejourney.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private UUID id;
    private String name;
    private LocalDateTime createdAt;
    private Double balance;

    public AccountDto(String name) {
        this.name = name;
        this.createdAt = LocalDateTime.now();
        this.balance = 0.0;
    }

    public AccountDto(String name, LocalDateTime createdAt) {
        this.name = name;
        this.createdAt = createdAt;
        this.balance = 0.0;
    }
}
