package com.grupo08.unicen.trabajointegradortp3.controller;

import com.grupo08.unicen.trabajointegradortp3.dtos.EstudianteDTO;
import com.grupo08.unicen.trabajointegradortp3.entity.Estudiante;
import com.grupo08.unicen.trabajointegradortp3.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiante")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    public List<EstudianteDTO> getEstudiantes() {
        return estudianteService.getEstudiantes();
    }

    @GetMapping("/{id}")
    public EstudianteDTO getEstudianteById(@PathVariable Long id) {
        return estudianteService.getEstudianteById(id);
    }

    @PostMapping
    public void createEstudiante(@RequestBody List<Estudiante> estudiante) {
        estudianteService.createEstudiante(estudiante);
    }

    @PutMapping("/{id}")
    public void updateEstudiante(@PathVariable Long id, @RequestBody Estudiante estudiante) {
        estudianteService.updateEstudiante(id, estudiante);
    }

    @DeleteMapping("/{id}")
    public Estudiante deleteEstudiante(@PathVariable Long id) {
        return estudianteService.deleteEstudiante(id);
    }

    // METODOS TP
    @GetMapping("/ordenado")
    public List<EstudianteDTO> getEstudiantesOrderByApellido() {
        return estudianteService.getEstudiantesOrderByApellido();
    }

    @GetMapping("/libreta/{numeroLibreta}")
    public EstudianteDTO getEstudianteByNumeroLibretaUniversitaria(@PathVariable String numeroLibreta) {
        return estudianteService.findEstudianteByNumeroLibretaUniversitaria(numeroLibreta);
    }

    @GetMapping("/genero/{genero}")
    public List<EstudianteDTO> getEstudiantesByGenero(@PathVariable String genero) {
        return estudianteService.findAllByGenero(genero);
    }

}
