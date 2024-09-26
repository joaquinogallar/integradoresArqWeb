package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
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
    }

    public void inscribirEstudiante(Estudiante estudiante) {
        EstudianteCarrera estudianteCarrera = new EstudianteCarrera(estudiante, this);
        estudiantes.add(estudianteCarrera);
        estudiante.getCarreras().add(estudianteCarrera);
    }
}