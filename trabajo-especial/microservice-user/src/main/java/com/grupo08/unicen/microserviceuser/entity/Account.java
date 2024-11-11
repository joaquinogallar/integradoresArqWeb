package com.grupo08.unicen.microserviceuser.entity;

import com.grupo08.unicen.microserviceuser.dto.AccountDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@ToString
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDateTime createdAt;

    private Double balance;

    @ManyToMany(mappedBy = "accounts")
    private List<UserEntity> users;

    public Account() {
        this.createdAt = LocalDateTime.now();
        this.balance = 0.0;
        this.users = new ArrayList<>();
    }

    public Account(AccountDto accountDto) {
        this.createdAt = accountDto.getCreatedAt();
        this.balance = accountDto.getBalance();
        this.users = new ArrayList<>();
    }
}
