package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carrera")
@Data
@NoArgsConstructor
public class Carrera implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreCarrera;

    @OneToMany(mappedBy = "carrera")
    private List<EstudianteCarrera> estudiantes;

    public Carrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
        estudiantes = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Carrera{" +
                "id=" + id +
                ", nombreCarrera='" + nombreCarrera + '\'' +
                // evitar imprimir la lista completa de estudiantes para evitar recursividad
                '}';
    }

}