package com.grupo08.unicen.microservicepayment.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@ToString
public class Transaction {
	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID accountId;  // Relación a Cuenta
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type; // RECARGA, DEBITO, EXTRA_POR_PAUSA
    
    private LocalDateTime date;
}


enum TransactionType {
    RECARGA,
    DEBITO,
    EXTRA_POR_PAUSA
}