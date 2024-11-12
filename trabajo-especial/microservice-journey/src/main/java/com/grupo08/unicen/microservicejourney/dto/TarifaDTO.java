package com.grupo08.unicen.microservicejourney.dto;

import com.grupo08.unicen.microservicejourney.entity.Tarifa;

import java.util.Date;

public class TarifaDTO {
    private Double tarifa;
    private String tipo_tarifa;
    private Date fecha_inicio;

    public TarifaDTO(Tarifa t) {
        this.tarifa = t.getTarifa();
      

    }

}
