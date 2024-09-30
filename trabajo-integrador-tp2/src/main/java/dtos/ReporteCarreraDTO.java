package dtos;

import java.util.ArrayList;
import java.util.List;

public class ReporteCarreraDTO {
    private String nombreCarrera;
    private int anio;
    private List<EstudianteDTO> inscriptos;
    private List<EstudianteDTO> egresados;

    public ReporteCarreraDTO(String nombreCarrera, int anio) {
        this.nombreCarrera = nombreCarrera;
        this.anio = anio;
        inscriptos = new ArrayList<>();
        egresados = new ArrayList<>();
    }
}
