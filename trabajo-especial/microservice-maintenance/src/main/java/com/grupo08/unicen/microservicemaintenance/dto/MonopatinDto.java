package com.grupo08.unicen.microservicemaintenance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class MonopatinDto {
    private String status;
    private int x;
    private int y;
    private BigDecimal kmTraveled;
    private Long useTime;

    public MonopatinDto(MonopatinDto monopatinDto) {
        this.status = monopatinDto.getStatus();
        this.x = monopatinDto.getX();
        this.y = monopatinDto.getY();
        this.kmTraveled = monopatinDto.getKmTraveled();
        this.useTime = monopatinDto.getUseTime();
    }
    // Constructor para crear cuando se obtiene desde el microservicio
    public MonopatinDto(String state, int x, int y, BigDecimal kmTraveled, Long useTime) {
        this.status = state;
        this.x = x;
        this.y = y;
        this.kmTraveled = kmTraveled;
        this.useTime = useTime;
    }

}
