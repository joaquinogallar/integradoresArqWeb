package repositories;

import dtos.CarreraDTO;
import dtos.EgresadoDTO;
import dtos.EstudianteDTO;
import dtos.ReporteCarreraDTO;
import entities.Carrera;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface CarreraRepository {
    public List<CarreraDTO> getCarreras();
    public void createCarrera(Carrera carrera);
    public List<CarreraDTO> getCarrerasConEstudiantes(Carrera carrera);
    public List<CarreraDTO> getCarrerasOrdenadasPorInscripciones();
    public List<ReporteCarreraDTO> generarReporteCarreras();
    public List<EstudianteDTO> getInscriptosPorCarrera(Carrera carrera);
    public List<EgresadoDTO> getEgresadosPorCarrera(Carrera carrera);
    public void cargarDatos(String ruta) throws IOException;
}
