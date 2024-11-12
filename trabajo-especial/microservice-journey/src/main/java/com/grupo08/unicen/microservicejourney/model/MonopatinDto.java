package com.grupo08.unicen.microservicejourney.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class MonopatinDto {

    private UUID id;

    // ubication
    private int x ;
    private int y ;

    private BigDecimal kmTraveled;
    private Long useTime;
}
