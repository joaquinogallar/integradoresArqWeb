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
    private String numeroLibretaUniversitaria;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private List<CarreraInscripta> carreras;

    public Estudiante(String nombres, String apellido, int edad, String genero, String numeroDocumento, String ciudadResidencia, String numeroLibretaUniversitaria) {
        this.nombres = nombres;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.numeroDocumento = numeroDocumento;
        this.ciudadResidencia = ciudadResidencia;
        this.numeroLibretaUniversitaria = numeroLibretaUniversitaria;
        this.carreras = new ArrayList<>();
    }

    public void agregarCarrera(CarreraInscripta carreraInscripta) {
        if(carreraInscripta != null)
            carreras.add(carreraInscripta);
    }
}