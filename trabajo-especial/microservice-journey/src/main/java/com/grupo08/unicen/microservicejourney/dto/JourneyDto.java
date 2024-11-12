package com.grupo08.unicen.microservicejourney.dto;

import com.grupo08.unicen.microservicejourney.entity.Journey;

public class JourneyDto {

    private double kmRecorridos ;

    public JourneyDto(Journey v) {
        this.kmRecorridos = v.getkmRecorridos();
    }
}
