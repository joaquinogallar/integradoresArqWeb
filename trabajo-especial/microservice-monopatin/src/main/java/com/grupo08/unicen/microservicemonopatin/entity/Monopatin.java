package com.grupo08.unicen.microservicemonopatin.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@ToString
public class Monopatin {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private State state;
    private int x;
    private int y;
    private BigDecimal kmTraveled;
    private BigDecimal useTime;
}
