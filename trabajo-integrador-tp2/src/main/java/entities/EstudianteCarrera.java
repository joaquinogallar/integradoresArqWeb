package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "estudiante_carrera")
@Data
@NoArgsConstructor
public class EstudianteCarrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Estudiante estudiante;
    @ManyToOne
    private Carrera carrera;

    private int antiguedad;
    private boolean graduado;


}
