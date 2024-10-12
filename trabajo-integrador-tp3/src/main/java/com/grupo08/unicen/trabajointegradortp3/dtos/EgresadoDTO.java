package com.grupo08.unicen.trabajointegradortp3.dtos;

import com.grupo08.unicen.trabajointegradortp3.entity.Carrera;
import com.grupo08.unicen.trabajointegradortp3.entity.Estudiante;
import com.grupo08.unicen.trabajointegradortp3.entity.EstudianteCarrera;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    private Carrera carrera;

    public EgresadoDTO(EstudianteCarrera estudianteCarrera) {
        this.id = estudianteCarrera.getEstudiante().getId();
        this.nombre = estudianteCarrera.getEstudiante().getNombre();
        this.apellido = estudianteCarrera.getEstudiante().getApellido();
        this.numeroLibretaUniversitaria = estudianteCarrera.getEstudiante().getNumeroLibretaUniversitaria();
        this.anioInscripcion = estudianteCarrera.getAnioIngreso();
        this.anioEgreso = estudianteCarrera.getAnioGraduado();
    }
}
