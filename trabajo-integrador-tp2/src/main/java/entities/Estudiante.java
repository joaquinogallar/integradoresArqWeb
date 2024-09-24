package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "estudiante")
@Data
@NoArgsConstructor
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombres;
    private String apellido;
    private int edad;
    private String genero;
    private String numeroDocumento;
    private String ciudadResidencia;
    private int numeroLibretaUniversitaria;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private List<EstudianteCarrera> carreras;

    public Estudiante(String nombre, String apellido, int edad, String genero, String numeroDocumento, String ciudadResidencia, int numeroLibretaUniversitaria) {
        this.nombres = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.numeroDocumento = numeroDocumento;
        this.ciudadResidencia = ciudadResidencia;
        this.numeroLibretaUniversitaria = numeroLibretaUniversitaria;
    }

}