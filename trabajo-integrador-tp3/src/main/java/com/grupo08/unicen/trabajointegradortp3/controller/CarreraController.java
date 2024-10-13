package com.grupo08.unicen.trabajointegradortp3.controller;

import com.grupo08.unicen.trabajointegradortp3.dtos.CarreraDTO;
import com.grupo08.unicen.trabajointegradortp3.dtos.ReporteCarreraDTO;
import com.grupo08.unicen.trabajointegradortp3.entity.Carrera;
import com.grupo08.unicen.trabajointegradortp3.service.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrera")
public class CarreraController {

    @Autowired
    private CarreraService carreraService;

    @GetMapping
    public ResponseEntity<List<CarreraDTO>> getAllCarreras() {
        List<CarreraDTO> carreras = carreraService.getAllCarreras();
        return new ResponseEntity<>(carreras, HttpStatus.OK);    }


    @GetMapping("/{id}")
    public ResponseEntity<CarreraDTO> getCarreraById(@PathVariable("id") Long id) {
        CarreraDTO carrera = carreraService.getCarreraById(id);
        return new ResponseEntity<>(carrera, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addCarrera(@RequestBody List<Carrera> carreras) {
        carreraService.createCarrera(carreras);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCarrera(@PathVariable("id") Long id, @RequestBody Carrera carrera) {
        carreraService.updateCarrera(id, carrera);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CarreraDTO> deleteCarrera(@PathVariable("id") Long id) {
        CarreraDTO carreraEliminada = carreraService.deleteCarrera(id);
        return new ResponseEntity<>(carreraEliminada, HttpStatus.OK);
    }
    // METODOS TP
    @GetMapping("/ordenado/inscriptos")
    public ResponseEntity<List<CarreraDTO>> findCarrerasConEstudiantesOrdenadasPorInscritos() {
        List<CarreraDTO> carreras = carreraService.findCarrerasConEstudiantesOrdenadasPorInscritos();
        return new ResponseEntity<>(carreras, HttpStatus.OK);
    }

    @GetMapping("/reporte")
    public ResponseEntity<List<ReporteCarreraDTO>> generarReportes() {
        List<ReporteCarreraDTO> reportes = carreraService.generarReporte();
        return new ResponseEntity<>(reportes, HttpStatus.OK);
    }
}
