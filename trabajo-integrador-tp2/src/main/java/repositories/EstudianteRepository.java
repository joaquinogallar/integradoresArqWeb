package repositories;

import dtos.EstudianteDTO;
import entities.Carrera;
import entities.Estudiante;
import entities.Genero;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface EstudianteRepository {
    public void createEstudiante(Estudiante estudiante);
    public void darAltaEstudiante(Estudiante estudiante, Carrera carrera);
    public void inscribirEstudiante(Estudiante estudiante, Carrera carrera);

    public List<EstudianteDTO> getEstudiantes();
    public EstudianteDTO getEstudianteByNumeroLibreta(String numeroLibreta);
    public List<EstudianteDTO> getEstudiantesByGenero(Genero genero);
    public List<EstudianteDTO> getEstudiantesPorCarreraYCiudad(Carrera Carrera, String ciudadResidencia);

    public void cargarDatos(String ruta) throws IOException;
}
