package repositories;

import dtos.CarreraDTO;
import dtos.EstudianteDTO;
import dtos.ReporteCarreraDTO;
import entities.Carrera;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface CarreraRepository {
    public List<CarreraDTO> getCarreras();
    public void createCarrera(Carrera carrera);
    public List<CarreraDTO> getCarrerasConEstudiantes(Long id);
    public List<CarreraDTO> getCarrerasOrdenadasPorInscripciones();
    public List<ReporteCarreraDTO> generarReporteCarreras();
    public List<EstudianteDTO> obtenerInscriptosPorCarrera(Long id);
    public List<EstudianteDTO> obtenerEgresadosPorCarrera(Long id);
    public void cargarDatos(String ruta) throws IOException;
}
