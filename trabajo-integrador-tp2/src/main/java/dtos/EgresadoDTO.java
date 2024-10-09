package dtos;

import entities.Estudiante;
import entities.EstudianteCarrera;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EgresadoDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String numeroLibretaUniversitaria;
    private Integer anioEgreso;

    public EgresadoDTO(Estudiante estudiante, EstudianteCarrera estudianteCarrera) {
        this.id = estudiante.getId();
        this.nombre = estudiante.getNombre();
        this.apellido = estudiante.getApellido();
        this.numeroLibretaUniversitaria = estudiante.getNumeroLibretaUniversitaria();
        this.anioEgreso = estudianteCarrera.getAnioGraduado();
    }
}
