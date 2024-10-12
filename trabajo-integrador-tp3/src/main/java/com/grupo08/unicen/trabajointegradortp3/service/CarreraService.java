package com.grupo08.unicen.trabajointegradortp3.service;

import com.grupo08.unicen.trabajointegradortp3.dtos.CarreraDTO;
import com.grupo08.unicen.trabajointegradortp3.dtos.ReporteCarreraDTO;
import com.grupo08.unicen.trabajointegradortp3.entity.Carrera;
import com.grupo08.unicen.trabajointegradortp3.exception.CarreraNoEncontradaException;
import com.grupo08.unicen.trabajointegradortp3.repository.CarreraRepository;
import com.grupo08.unicen.trabajointegradortp3.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;

    @Autowired
    private EstudianteService estudianteService;

    public List<CarreraDTO> getAllCarreras() {
        List<Carrera> carreras = carreraRepository.findAll();
        List<CarreraDTO> carrerasDTO = new ArrayList<>();
        carreras.forEach(carrera -> carrerasDTO.add(new CarreraDTO(carrera)));

        return carrerasDTO;
    }

    public CarreraDTO getCarreraById(Long id) {
        Carrera carrera = carreraRepository.findById(id).orElse(null);
        if(carrera == null)
            throw new CarreraNoEncontradaException("No se encontro la carrera con el id: " + id);

        return new CarreraDTO(carrera);
    }

    public void createCarrera(Carrera carrera) {
        carreraRepository.save(carrera);
    }

    public void updateCarrera(Long id, Carrera carreraUpdate) {
        Carrera carrera = carreraRepository.findById(id).orElse(null);
        if(carrera == null)
            throw new CarreraNoEncontradaException("No se encontro la carrera con el id: " + id);

        carrera.setNombreCarrera(carreraUpdate.getNombreCarrera());
        carrera.setNombreCarrera(carrera.getNombreCarrera());

        carreraRepository.save(carrera);
    }

    public CarreraDTO deleteCarrera(Long id) {
        Carrera carrera = carreraRepository.findById(id).orElse(null);
        if(carrera == null)
            throw new CarreraNoEncontradaException("No se encontro la carrera con el id: " + id);

        CarreraDTO carreraDTO = new CarreraDTO(carrera);
        carreraRepository.delete(carrera);

        return carreraDTO;
    }

    // METODOS TP
    public List<CarreraDTO> findCarrerasConEstudiantesOrdenadasPorInscritos() {
        List<Carrera> carreras = carreraRepository.findCarrerasConEstudiantesOrdenadasPorInscritos();
        List<CarreraDTO> carrerasDTO = new ArrayList<>();
        carreras.forEach(carrera -> carrerasDTO.add(new CarreraDTO(carrera)));

        return carrerasDTO;
    }

    public List<ReporteCarreraDTO> generarReporte() {
        List<Carrera> carreras = carreraRepository.findCarrerasByOrderByNombreCarrera();
        List<ReporteCarreraDTO> reporteDTO = new ArrayList<>();
        carreras.forEach(carrera -> {
            ReporteCarreraDTO reporte = new ReporteCarreraDTO(carrera);

            estudianteService.findEstudiantesByIdCarrera(carrera.getId())
                    .forEach(estudiante -> reporte.getInscriptos().add(estudiante));

            estudianteService.findEgresadosByIdCarrera(carrera.getId())
                    .forEach(egresado -> reporte.getEgresados().add(egresado));

        });
        return reporteDTO;
    }

}
