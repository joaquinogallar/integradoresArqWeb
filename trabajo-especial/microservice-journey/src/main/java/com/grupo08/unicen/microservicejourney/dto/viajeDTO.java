package com.grupo08.unicen.microservicejourney.dto;

import com.grupo08.unicen.microservicejourney.entity.Viaje;

public class viajeDTO {

    private double kmRecorridos ;

    public viajeDTO(Viaje v) {
        this.kmRecorridos = v.getkmRecorridos();
    }
}
