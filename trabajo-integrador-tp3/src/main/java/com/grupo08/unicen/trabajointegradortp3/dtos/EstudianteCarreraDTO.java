package com.grupo08.unicen.trabajointegradortp3.dtos;

import com.grupo08.unicen.trabajointegradortp3.entity.EstudianteCarrera;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EstudianteCarreraDTO {
    private Long id;
    private EstudianteDTO estudiante;
    private CarreraDTO carrera;
    private int anioIngreso;
    private boolean graduado;
    private Integer anioGraduado;

    public EstudianteCarreraDTO(EstudianteCarrera estudianteCarrera) {
        this.id = estudianteCarrera.getId();
        this.estudiante = new EstudianteDTO(estudianteCarrera.getEstudiante());
        this.carrera = new CarreraDTO(estudianteCarrera.getCarrera());
        this.anioIngreso = estudianteCarrera.getAnioIngreso();
        this.graduado = estudianteCarrera.isGraduado();
        this.anioGraduado = estudianteCarrera.getAnioGraduado();
    }
}
