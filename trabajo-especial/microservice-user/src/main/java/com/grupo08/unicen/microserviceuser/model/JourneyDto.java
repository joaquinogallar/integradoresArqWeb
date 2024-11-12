package com.grupo08.unicen.microserviceuser.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
