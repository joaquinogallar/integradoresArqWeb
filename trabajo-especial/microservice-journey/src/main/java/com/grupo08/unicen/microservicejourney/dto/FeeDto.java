package com.grupo08.unicen.microservicejourney.dto;

import com.grupo08.unicen.microservicejourney.entity.Fee;

import java.util.Date;

public class FeeDto {
    private double fee;
    private Date fecha_inicio;
    private double specialFee;

    public FeeDto(double fee, double specialFee , Date fecha_inicio) {
        this.fee = fee;
        this.specialFee = specialFee;
        this.fecha_inicio=fecha_inicio;

    }

    

}
