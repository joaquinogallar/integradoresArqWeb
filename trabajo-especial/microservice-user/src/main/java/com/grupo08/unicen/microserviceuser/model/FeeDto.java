package com.grupo08.unicen.microserviceuser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeeDto {
    private Long id;
    private Double tarifa;
    private Double tarifaEspecial;
    private Date fecha_inicio;
}
