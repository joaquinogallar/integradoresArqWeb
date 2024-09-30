package dtos;

import entities.Carrera;

import java.util.ArrayList;
import java.util.List;

public class ReporteCarreraDTO {
    private Carrera carrera;
    private List<EstudianteDTO> inscriptos;
    private List<EstudianteDTO> egresados;

    public ReporteCarreraDTO(Carrera carrera) {
        this.carrera = carrera;
        inscriptos = new ArrayList<>();
        egresados = new ArrayList<>();
    }
}
