package dtos;

import entities.Estudiante;
import lombok.Data;

@Data
public class EstudianteDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String numeroLibretaUniversitaria;

    public EstudianteDTO(Estudiante estudiante) {
        this.id = estudiante.getId();
        this.nombre = estudiante.getNombre();
        this.apellido = estudiante.getApellido();
        this.numeroLibretaUniversitaria = estudiante.getNumeroLibretaUniversitaria();
    }
}