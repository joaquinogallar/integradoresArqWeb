package com.grupo08.unicen.trabajointegradortp3.controller;

import com.grupo08.unicen.trabajointegradortp3.dtos.EstudianteDTO;
import com.grupo08.unicen.trabajointegradortp3.entity.Estudiante;
import com.grupo08.unicen.trabajointegradortp3.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiante")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;


    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> getEstudiantes() {
        List<EstudianteDTO> estudiantes = estudianteService.getEstudiantes();
        return new ResponseEntity<>(estudiantes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> getEstudianteById(@PathVariable Long id) {
        EstudianteDTO estudiante = estudianteService.getEstudianteById(id);
        return new ResponseEntity<>(estudiante, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createEstudiante(@RequestBody List<Estudiante> estudiantes) {
        estudianteService.createEstudiante(estudiantes);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEstudiante(@PathVariable Long id, @RequestBody Estudiante estudiante) {
        estudianteService.updateEstudiante(id, estudiante);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Estudiante> deleteEstudiante(@PathVariable Long id) {
        Estudiante estudianteEliminado = estudianteService.deleteEstudiante(id);
        return new ResponseEntity<>(estudianteEliminado, HttpStatus.OK);
    }


    // METODOS TP
    @PostMapping("/inscribir")
    public ResponseEntity<Void> inscribirEstudianteACarrera(@RequestParam Long idEstudiante, @RequestParam Long idCarrera) {
        estudianteService.inscribirEstudianteACarrera(idEstudiante, idCarrera);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/alta")
    public ResponseEntity<String> darAltaEstudianteDeCarrera(@RequestParam Long idEstudiante, @RequestParam Long idCarrera) {
        String resultado = estudianteService.darAltaEstudianteDeCarrera(idEstudiante, idCarrera);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @GetMapping("/ordenado")
    public ResponseEntity<List<EstudianteDTO>> getEstudiantesOrderByApellido() {
        List<EstudianteDTO> estudiantes = estudianteService.getEstudiantesOrderByApellido();
        return new ResponseEntity<>(estudiantes, HttpStatus.OK);
    }

    @GetMapping("/libreta/{numeroLibreta}")
    public ResponseEntity<EstudianteDTO> getEstudianteByNumeroLibretaUniversitaria(@PathVariable String numeroLibreta) {
        EstudianteDTO estudiante = estudianteService.findEstudianteByNumeroLibretaUniversitaria(numeroLibreta);
        return new ResponseEntity<>(estudiante, HttpStatus.OK);
    }

    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<EstudianteDTO>> getEstudiantesByGenero(@PathVariable String genero) {
        List<EstudianteDTO> estudiantes = estudianteService.findAllByGenero(genero);
        return new ResponseEntity<>(estudiantes, HttpStatus.OK);
    }

}
