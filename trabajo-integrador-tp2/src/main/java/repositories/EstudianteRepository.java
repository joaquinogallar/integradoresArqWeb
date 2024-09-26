package repositories;

import entities.Estudiante;

import java.util.List;

public interface EstudianteRepository {
    public void darAltaEstudiante(Estudiante estudiante);
    public void inscribirEstudiante(Estudiante estudiante);
    public List<Estudiante> getEstudiantes();
    public Estudiante getEstudianteByNumeroLibreta(String numeroLibreta);
    public List<Estudiante> getEstudiantesByGenero(String genero);
}
