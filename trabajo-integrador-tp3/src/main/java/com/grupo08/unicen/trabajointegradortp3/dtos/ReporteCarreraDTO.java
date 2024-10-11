package com.grupo08.unicen.trabajointegradortp3.dtos;


import com.grupo08.unicen.trabajointegradortp3.entity.Carrera;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReporteCarreraDTO {
    private Carrera carrera;
    private List<EstudianteDTO> inscriptos;
    private List<EgresadoDTO> egresados;

    public ReporteCarreraDTO(Carrera carrera) {
        this.carrera = carrera;
        inscriptos = new ArrayList<>();
        egresados = new ArrayList<>();
    }
}