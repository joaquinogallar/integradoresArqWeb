package repositories;

import dtos.CarreraDTO;
import dtos.ReporteCarreraDTO;
import entities.Carrera;

import java.util.List;

public interface CarreraRepository {
    public List<CarreraDTO> getCarreras();
    public void createCarrera(Carrera carrera);
    public List<CarreraDTO> getCarrerasConEstudiantes(Long id);
    public List<CarreraDTO> getCarrerasOrdenadasPorInscripciones();
    public List<ReporteCarreraDTO> generarReporteCarreras();
}
