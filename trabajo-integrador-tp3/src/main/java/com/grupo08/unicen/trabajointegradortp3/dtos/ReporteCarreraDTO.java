package com.grupo08.unicen.trabajointegradortp3.dtos;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReporteCarreraDTO {
    private CarreraDTO carrera;
    private List<EstudianteDTO> inscriptos;
    private List<EgresadoDTO> egresados;

    public ReporteCarreraDTO(CarreraDTO carrera) {
        this.carrera = carrera;
        inscriptos = new ArrayList<>();
        egresados = new ArrayList<>();
    }
}
