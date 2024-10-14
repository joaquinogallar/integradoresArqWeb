package com.grupo08.unicen.trabajointegradortp3.controller;

import com.grupo08.unicen.trabajointegradortp3.dtos.EstudianteCarreraDTO;
import com.grupo08.unicen.trabajointegradortp3.entity.EstudianteCarrera;
import com.grupo08.unicen.trabajointegradortp3.service.EstudianteCarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("estudianteCarrera")
public class EstudianteCarreraController {

    @Autowired
    private EstudianteCarreraService estudianteCarreraService;

    @GetMapping
    public List<EstudianteCarreraDTO> getEstudiantesCarreras() {
        List<EstudianteCarrera> estudianteCarreras = estudianteCarreraService.getEstudiantesCarreras();
        List<EstudianteCarreraDTO> estudianteCarreraDTO = new ArrayList<>();
        estudianteCarreras.forEach(estudianteCarrera -> estudianteCarreraDTO.add(new EstudianteCarreraDTO(estudianteCarrera)));

        return estudianteCarreraDTO;
    }

    @GetMapping("/{id}")
    public EstudianteCarreraDTO getEstudianteCarreraById(@PathVariable Long id) {
        EstudianteCarreraDTO estudianteCarreraDTO = new EstudianteCarreraDTO(estudianteCarreraService.getEstudianteCarreraById(id));
        return estudianteCarreraDTO;
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
    public EstudianteCarreraDTO deleteEstudianteCarrera(@PathVariable Long id) {
        EstudianteCarreraDTO estudianteCarreraDTO = new EstudianteCarreraDTO(estudianteCarreraService.deleteEstudianteCarrera(id));
        return estudianteCarreraDTO;
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
