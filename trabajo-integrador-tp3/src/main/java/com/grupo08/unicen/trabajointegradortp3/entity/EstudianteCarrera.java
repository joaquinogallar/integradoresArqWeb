package com.grupo08.unicen.trabajointegradortp3.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class EstudianteCarrera implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Estudiante estudiante;
    @ManyToOne(fetch = FetchType.LAZY)
    private Carrera carrera;

    private int anioIngreso;
    private boolean graduado;
    private Integer anioGraduado; // es de tipo Integer para que pueda ser null en un inicio

    public EstudianteCarrera(Estudiante estudiante, Carrera carrera) {
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.anioIngreso = 2021;
        this.graduado = false;
    }
}
