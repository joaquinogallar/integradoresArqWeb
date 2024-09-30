package repositories;

import entities.Estudiante;
import entities.Genero;

import java.util.List;

public interface EstudianteRepository {
    public void createEstudiante(Estudiante estudiante);
    public void darAltaEstudiante(Long idEstudiante, Long idCarrera);
    public void inscribirEstudiante(Long idEstudiante, Long idCarrera);

    public List<Estudiante> getEstudiantes();
    public Estudiante getEstudianteByNumeroLibreta(String numeroLibreta);
    public List<Estudiante> getEstudiantesByGenero(Genero genero);
}
