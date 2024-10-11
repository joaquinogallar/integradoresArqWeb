package com.grupo08.unicen.trabajointegradortp3.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Estudiante implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private int edad;
    private String genero;
    private String numeroDocumento;
    private String ciudadResidencia;
    private String numeroLibretaUniversitaria;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private List<EstudianteCarrera> carreras;

    public Estudiante(String nombre, String apellido, int edad, String genero, String numeroDocumento, String ciudadResidencia, String numeroLibretaUniversitaria) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.numeroDocumento = numeroDocumento;
        this.ciudadResidencia = ciudadResidencia;
        this.numeroLibretaUniversitaria = numeroLibretaUniversitaria;
        this.carreras = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", genero='" + genero + '\'' +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                ", ciudadResidencia='" + ciudadResidencia + '\'' +
                ", numeroLibretaUniversitaria='" + numeroLibretaUniversitaria + '\'' +
                // evitar imprimir la lista completa de carreras para evitar recursividad
                '}';
    }
}