package repositories;

import entities.Estudiante;
import entities.Genero;

import java.util.List;

public interface EstudianteRepository {
    public void createEstudiante(Estudiante estudiante);
    public void darAltaEstudiante(Long id);
    public void inscribirEstudiante(Long id);

    public List<Estudiante> getEstudiantes();
    public Estudiante getEstudianteByNumeroLibreta(String numeroLibreta);
    public List<Estudiante> getEstudiantesByGenero(Genero genero);
}
