package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "carrera_inscripta")
@Data
@NoArgsConstructor
public class CarreraInscripta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreCarrera;
    private int antiguedad;
    private boolean graduado;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

    public CarreraInscripta(String nombreCarrera, Estudiante estudiante) {
        this.nombreCarrera = nombreCarrera;
        this.estudiante = estudiante;
        antiguedad = 0;
        graduado = false;
    }
}