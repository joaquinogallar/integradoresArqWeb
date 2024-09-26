package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "estudiante_carrera")
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

    private int antiguedad;
    private boolean graduado;

    public EstudianteCarrera(Estudiante estudiante, Carrera carrera) {
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.antiguedad = 0;
        this.graduado = false;
    }
}
