package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
<<<<<<< HEAD:trabajo-integrador-tp2/src/main/java/entities/EstudianteCarrera.java
@Table(name = "estudiante_carrera")
=======
@Table(name = "carrera_inscripta")
>>>>>>> ffec451d33b52ddc79a39bd85daed9f178e7aa3e:trabajo-integrador-tp2/src/main/java/entities/CarreraInscripta.java
@Data
@NoArgsConstructor
public class EstudianteCarrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

<<<<<<< HEAD:trabajo-integrador-tp2/src/main/java/entities/EstudianteCarrera.java
    @ManyToOne
    private Estudiante estudiante;
    @ManyToOne
    private Carrera carrera;

    private int antiguedad;
    private boolean graduado;


}
=======
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
>>>>>>> ffec451d33b52ddc79a39bd85daed9f178e7aa3e:trabajo-integrador-tp2/src/main/java/entities/CarreraInscripta.java
