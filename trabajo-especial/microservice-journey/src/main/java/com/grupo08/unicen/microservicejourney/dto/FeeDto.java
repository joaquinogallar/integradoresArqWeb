package com.grupo08.unicen.microservicejourney.dto;

import com.grupo08.unicen.microservicejourney.entity.Fee;

import java.util.Date;

public class FeeDto {
    private Double fee;
    private Date fecha_inicio;
    private double specialFee;

    public FeeDto(Fee t) {
        this.fee = t.getFee();
        this.specialFee = t.getSpecialFee();

    }

    

}
