package com.grupo08.unicen.microservicemonopatin.entity;

import com.grupo08.unicen.microservicemonopatin.dto.MonopatinDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private Long useTime;

    public Monopatin(Stop stop) {
        this.state = State.AVAILABLE;
        this.x = stop.getX();
        this.y = stop.getY();
        this.kmTraveled = BigDecimal.ZERO;
        this.useTime = 0L;
    }

    public Monopatin(MonopatinDto monopatinDto) {
        this.state = monopatinDto.getState();
        this.x = monopatinDto.getX();
        this.y = monopatinDto.getY();
        this.kmTraveled = BigDecimal.ZERO;
        this.useTime = 0L;

    }
}
