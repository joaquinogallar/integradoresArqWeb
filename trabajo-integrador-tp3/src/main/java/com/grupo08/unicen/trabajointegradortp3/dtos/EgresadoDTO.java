package com.grupo08.unicen.trabajointegradortp3.dtos;

import com.grupo08.unicen.trabajointegradortp3.entity.Estudiante;
import com.grupo08.unicen.trabajointegradortp3.entity.EstudianteCarrera;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EgresadoDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String numeroLibretaUniversitaria;
    private Integer anioInscripcion;
    private Integer anioEgreso;

    public EgresadoDTO(Estudiante estudiante, EstudianteCarrera estudianteCarrera) {
        this.id = estudiante.getId();
        this.nombre = estudiante.getNombre();
        this.apellido = estudiante.getApellido();
        this.numeroLibretaUniversitaria = estudiante.getNumeroLibretaUniversitaria();
        this.anioInscripcion = 2021;
        this.anioEgreso = estudianteCarrera.getAnioGraduado();
    }
}
