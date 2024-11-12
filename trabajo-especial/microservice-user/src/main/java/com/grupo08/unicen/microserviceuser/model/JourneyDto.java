package com.grupo08.unicen.microserviceuser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JourneyDto {

    private Long id;
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private Double kmRecorridos;
    private Long id_usuario;
    private Long id_monopatin;
    private int xOrigen ;
    private int yOrigen;
    private FeeDto tarifa;
}
