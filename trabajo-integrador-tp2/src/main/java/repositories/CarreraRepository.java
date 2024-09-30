package repositories;

import dtos.ReporteCarreraDTO;
import entities.Carrera;

import java.util.List;

public interface CarreraRepository {
    public void createCarrera(Carrera carrera);
    public List<Carrera> getCarrerasConEstudiantes(Carrera carrera);
    public List<Carrera> getCarrerasOrdenadasPorInscripciones();
    public List<ReporteCarreraDTO> generarReporteCarreras();
}
