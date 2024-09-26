package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "carrera")
@Data
@NoArgsConstructor
public class Carrera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreCarrera;

    @OneToMany(mappedBy = "carrera")
    private List<EstudianteCarrera> estudiantes;

    public void inscribirEstudiante(Estudiante estudiante) {
        EstudianteCarrera estudianteCarrera = new EstudianteCarrera(estudiante, this);
        estudiantes.add(estudianteCarrera);
        estudiante.getCarreras().add(estudianteCarrera);
    }
}