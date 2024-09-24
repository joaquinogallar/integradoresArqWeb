package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
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
<<<<<<< HEAD
    private List<EstudianteCarrera> carreras;

    public Estudiante(String nombre, String apellido, int edad, String genero, String numeroDocumento, String ciudadResidencia, int numeroLibretaUniversitaria) {
        this.nombres = nombre;
=======
    private List<CarreraInscripta> carreras;

    public Estudiante(String nombres, String apellido, int edad, String genero, String numeroDocumento, String ciudadResidencia, String numeroLibretaUniversitaria) {
        this.nombres = nombres;
>>>>>>> ffec451d33b52ddc79a39bd85daed9f178e7aa3e
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.numeroDocumento = numeroDocumento;
        this.ciudadResidencia = ciudadResidencia;
        this.numeroLibretaUniversitaria = numeroLibretaUniversitaria;
<<<<<<< HEAD
    }

=======
        this.carreras = new ArrayList<>();
    }

    public void agregarCarrera(CarreraInscripta carreraInscripta) {
        if(carreraInscripta != null)
            carreras.add(carreraInscripta);
    }
>>>>>>> ffec451d33b52ddc79a39bd85daed9f178e7aa3e
}