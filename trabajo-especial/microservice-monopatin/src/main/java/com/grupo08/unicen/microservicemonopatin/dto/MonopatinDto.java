package com.grupo08.unicen.microservicemonopatin.dto;

import com.grupo08.unicen.microservicemonopatin.entity.Monopatin;
import com.grupo08.unicen.microservicemonopatin.entity.State;
import com.grupo08.unicen.microservicemonopatin.entity.Stop;
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

    public MonopatinDto(Monopatin monopatin) {
        this.id = monopatin.getId();
        this.state = monopatin.getState();
        this.x = monopatin.getX();
        this.y = monopatin.getY();
        this.kmTraveled = monopatin.getKmTraveled();
        this.useTime = monopatin.getUseTime();
    }

    public MonopatinDto(Stop stop) {
        this.state = State.AVAILABLE;
        this.x = stop.getX();
        this.y = stop.getY();
        this.kmTraveled = BigDecimal.ZERO;
        this.useTime = 0L;
    }}
