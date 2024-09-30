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

    public List<Estudiante> getEstudiantes();
    public Estudiante getEstudianteByNumeroLibreta(String numeroLibreta);
    public List<Estudiante> getEstudiantesByGenero(Genero genero);
    public List<Estudiante> getEstudiantesPorCarreraYCiudad(Carrera Carrera, String ciudadResidencia);

    public void cargarDatos(String ruta) throws IOException;
}
