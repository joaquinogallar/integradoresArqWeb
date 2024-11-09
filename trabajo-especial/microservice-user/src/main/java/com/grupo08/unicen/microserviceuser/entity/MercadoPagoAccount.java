package com.grupo08.unicen.microserviceuser.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@ToString
public class MercadoPagoAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "accountId", nullable = false)
    private Account account;

    private String mercadoPagoId;
}
