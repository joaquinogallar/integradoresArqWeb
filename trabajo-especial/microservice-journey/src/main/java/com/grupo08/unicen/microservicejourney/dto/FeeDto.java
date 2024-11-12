package com.grupo08.unicen.microservicejourney.dto;

import com.grupo08.unicen.microservicejourney.entity.Fee;

import java.util.Date;

public class FeeDto {
    private Double tarifa;
    private String tipo_tarifa;
    private Date fecha_inicio;

    public FeeDto(Fee t) {
        this.tarifa = t.getTarifa();
      

    }

    

}
