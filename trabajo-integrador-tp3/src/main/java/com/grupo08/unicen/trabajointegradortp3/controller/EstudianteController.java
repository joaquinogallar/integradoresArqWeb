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
    public List<EstudianteDTO> getAllEstudiantes() {
        return estudianteService.getAllEstudiantes();
    }

    @GetMapping("/{id}")
    public EstudianteDTO getEstudianteById(@PathVariable Long id) {
        return estudianteService.getEstudianteById(id);
    }

    @PostMapping
    public void createEstudiante(@RequestBody Estudiante estudiante) {
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

}
