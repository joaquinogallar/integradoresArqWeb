package com.grupo08.unicen.trabajointegradortp3.dtos;

import entities.Carrera;
import lombok.Data;

@Data
public class CarreraDTO {
    private Long id;
    private String nombreCarrera;

    public CarreraDTO(Carrera carrera) {
        this.id = carrera.getId();
        this.nombreCarrera = carrera.getNombreCarrera();
    }
}
