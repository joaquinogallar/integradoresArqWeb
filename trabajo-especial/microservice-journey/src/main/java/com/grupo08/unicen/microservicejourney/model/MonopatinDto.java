package com.grupo08.unicen.microservicejourney.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonopatinDto {
    private UUID id;
    private State state;
    private int x;
    private int y;
    private BigDecimal kmTraveled;
    private Long useTime;
}
