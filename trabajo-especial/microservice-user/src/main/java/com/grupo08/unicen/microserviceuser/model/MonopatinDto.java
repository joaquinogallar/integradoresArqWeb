package com.grupo08.unicen.microserviceuser.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

public class MonopatinDto {
    private UUID id;
    private State state;
    private int x;
    private int y;
    private BigDecimal kmTraveled;
    private BigDecimal useTime;
}
