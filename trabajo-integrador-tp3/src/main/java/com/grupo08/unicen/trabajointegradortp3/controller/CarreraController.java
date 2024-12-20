package com.grupo08.unicen.trabajointegradortp3.controller;

import com.grupo08.unicen.trabajointegradortp3.dtos.CarreraDTO;
import com.grupo08.unicen.trabajointegradortp3.dtos.ReporteCarreraDTO;
import com.grupo08.unicen.trabajointegradortp3.entity.Carrera;
import com.grupo08.unicen.trabajointegradortp3.service.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrera")
public class CarreraController {

    @Autowired
    private CarreraService carreraService;

    @GetMapping
    public List<CarreraDTO> getAllCarreras() {
        return carreraService.getAllCarreras();
    }

    @GetMapping("/{id}")
    public CarreraDTO getCarreraById(@PathVariable("id") Long id) {
        return carreraService.getCarreraById(id);
    }

    @PostMapping
    public void addCarrera(@RequestBody List<Carrera> carreras) {
        carreraService.createCarrera(carreras);
    }

    @PutMapping("/{id}")
    public void updateCarrera(@PathVariable("id") Long id, @RequestBody Carrera carrera) {
        carreraService.updateCarrera(id, carrera);
    }

    @DeleteMapping("/{id}")
    public CarreraDTO deleteCarrera(@PathVariable("id") Long id) {
        return carreraService.deleteCarrera(id);
    }

    // METODOS TP
    @GetMapping("/ordenado/inscriptos")
    public List<CarreraDTO> findCarrerasConEstudiantesOrdenadasPorInscritos() {
        return carreraService.findCarrerasConEstudiantesOrdenadasPorInscritos();
    }

    @GetMapping("/reporte")
    public List<ReporteCarreraDTO> generarReportes() {
        return carreraService.generarReporte();
    }
}
