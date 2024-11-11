package com.grupo08.unicen.microserviceuser.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@ToString
public class MercadoPagoAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany(mappedBy = "mercadoPagoAccount")
    private List<Account> accounts;

    private Double balance;

    private String mercadoPagoId;
}
