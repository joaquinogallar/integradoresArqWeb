package com.grupo08.unicen.microserviceuser.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@ToString
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDateTime createdAt;

    private Double balance;

    @ManyToMany(mappedBy = "accounts")
    private List<UserEntity> users;

    @ManyToOne
    private MercadoPagoAccount mercadoPagoAccount;

    // assign the date before persist
    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    public Account(MercadoPagoAccount mercadoPagoAccount) {
        this.mercadoPagoAccount = Account.this.mercadoPagoAccount;
        this.balance = Account.this.mercadoPagoAccount.getBalance();
    }
}
