package com.grupo08.unicen.trabajointegradortp3.dtos;

import com.grupo08.unicen.trabajointegradortp3.entity.Estudiante;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EstudianteDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String numeroLibretaUniversitaria;

    public EstudianteDTO(Estudiante estudiante) {
        this.id = estudiante.getId();
        this.nombre = estudiante.getNombre();
        this.apellido = estudiante.getApellido();
        this.numeroLibretaUniversitaria = estudiante.getNumeroLibretaUniversitaria();
    }
}
