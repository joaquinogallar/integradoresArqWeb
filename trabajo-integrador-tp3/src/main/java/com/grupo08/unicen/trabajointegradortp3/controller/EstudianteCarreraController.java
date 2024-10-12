package com.grupo08.unicen.trabajointegradortp3.controller;

import com.grupo08.unicen.trabajointegradortp3.entity.EstudianteCarrera;
import com.grupo08.unicen.trabajointegradortp3.service.EstudianteCarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("estudianteCarrera")
public class EstudianteCarreraController {

    @Autowired
    private EstudianteCarreraService estudianteCarreraService;

    @GetMapping
    public List<EstudianteCarrera> getEstudiantesCarreras() {
        return estudianteCarreraService.getEstudiantesCarreras();
    }

    @GetMapping("/{id}")
    public EstudianteCarrera getEstudianteCarreraById(@PathVariable Long id) {
        return estudianteCarreraService.getEstudianteCarreraById(id);
    }

    @PostMapping
    public void createEstudianteCarrera(@RequestBody EstudianteCarrera estudianteCarrera) {
        estudianteCarreraService.createEstudianteCarrera(estudianteCarrera);
    }

    @PutMapping("/{id}")
    public void updateEstudianteCarrera(@PathVariable Long id, @RequestBody EstudianteCarrera estudianteCarrera) {
        estudianteCarreraService.updateEstudianteCarrera(id, estudianteCarrera);
    }

    @DeleteMapping("/{id}")
    public EstudianteCarrera deleteEstudianteCarrera(@PathVariable Long id) {
        return estudianteCarreraService.deleteEstudianteCarrera(id);
    }

    // METODOS TP
    @PostMapping("/matricular")
    public void inscribirEstudiante(@RequestParam Long estudianteId, @RequestParam Long carreraId) {
        estudianteCarreraService.matricularEstudianteEnCarrera(estudianteId, carreraId);
    }

    @DeleteMapping("/alta")
    public void darAltaEstudiante(@RequestParam Long estudianteId, @RequestParam Long carreraId) {
        estudianteCarreraService.darAltaEstudiante(estudianteId, carreraId);
    }
}
