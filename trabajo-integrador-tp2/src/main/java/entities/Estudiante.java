package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estudiante")
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

    public void inscribirseCarrera(Carrera carrera) {
        EstudianteCarrera estudianteCarrera = new EstudianteCarrera(this, carrera);
        carreras.add(estudianteCarrera);
        carrera.getEstudiantes().add(estudianteCarrera);
    }

    public void darDeAlta(Carrera carrera) {
        carreras.forEach(ec -> {
            if(ec.getCarrera().equals(carrera)) {
                ec.setEstudiante(this);
            }
        });
    }
}