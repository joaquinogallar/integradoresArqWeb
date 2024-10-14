package com.grupo08.unicen.trabajointegradortp3.controller;

import com.grupo08.unicen.trabajointegradortp3.dtos.EgresadoDTO;
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

    @GetMapping("/carrera/{idCarrera}")
    public List<EstudianteDTO> findEstudiantesByIdCarrera(@PathVariable Long idCarrera) {
       return estudianteService.findEstudiantesByIdCarrera(idCarrera);
    }

    @GetMapping("/carrera/egresado/{idCarrera}")
    public List<EgresadoDTO> findEgresadosByIdCarrera(@PathVariable Long idCarrera) {
        return estudianteService.findEgresadosByIdCarrera(idCarrera);
    }

    // METODOS TP
    @PostMapping("/inscribir")
    public String inscribirEstudianteACarrera(@RequestParam Long idEstudiante, @RequestParam Long idCarrera) {
        return estudianteService.inscribirEstudianteACarrera(idEstudiante, idCarrera);
    }

    @DeleteMapping("/alta")
    public String darAltaEstudianteDeCarrera(@RequestParam Long idEstudiante, @RequestParam Long idCarrera) {
        return estudianteService.darAltaEstudianteDeCarrera(idEstudiante, idCarrera);
    }

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
