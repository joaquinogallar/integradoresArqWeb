package com.grupo08.unicen.microservicemonopatin.dto;

import com.grupo08.unicen.microservicemonopatin.entity.State;
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


    public MonopatinDto(UUID id, State s, BigDecimal km, Long useTime, int x,int y){
        this.id = id;
        this.state = s;
        this.kmTraveled=km;
        this.useTime = useTime ;
        this.x = x;
        this.y = y ;
    }
}
