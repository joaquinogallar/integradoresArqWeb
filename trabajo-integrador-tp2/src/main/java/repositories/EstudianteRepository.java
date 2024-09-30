package repositories;

import dtos.EstudianteDTO;
import entities.Estudiante;
import entities.Genero;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface EstudianteRepository {
    public void createEstudiante(Estudiante estudiante);
    public void darAltaEstudiante(Long idEstudiante, Long idCarrera);
    public void inscribirEstudiante(Long idEstudiante, Long idCarrera);

    public List<Estudiante> getEstudiantes();
    public Estudiante getEstudianteByNumeroLibreta(String numeroLibreta);
    public List<Estudiante> getEstudiantesByGenero(Genero genero);
    public List<Estudiante> getEstudiantesPorCarreraYCiudad(Long idCarrera, String ciudadResidencia);

    public void cargarDatos(String ruta) throws IOException;
}
