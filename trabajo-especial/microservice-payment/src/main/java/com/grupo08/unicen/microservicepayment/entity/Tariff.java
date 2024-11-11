package com.grupo08.unicen.microservicepayment.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@ToString
public class Tariff {
	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private BigDecimal normalRate;
    private BigDecimal extraRate;
    private LocalDateTime validFrom;
}
