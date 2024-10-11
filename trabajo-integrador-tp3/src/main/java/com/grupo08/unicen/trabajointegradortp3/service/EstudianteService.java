package com.grupo08.unicen.trabajointegradortp3.service;

import com.grupo08.unicen.trabajointegradortp3.dtos.EstudianteDTO;
import com.grupo08.unicen.trabajointegradortp3.entity.Estudiante;
import com.grupo08.unicen.trabajointegradortp3.exception.EstudianteNoEncontradoException;
import com.grupo08.unicen.trabajointegradortp3.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    public List<EstudianteDTO> getAllEstudiantes() {
        List<Estudiante> estudiantes = estudianteRepository.findAll();
        List<EstudianteDTO> estudianteDTO = new ArrayList<>();
        estudiantes.forEach(estudiante -> estudianteDTO.add(new EstudianteDTO(estudiante)));

        return estudianteDTO;
    }

    public EstudianteDTO getEstudianteById(Long id){
        Estudiante estudiante = estudianteRepository.findById(id).orElse(null);
        if(estudiante == null)
            throw new EstudianteNoEncontradoException("No se encontro el estudiante con el id: " + id);

        return new EstudianteDTO(estudiante);
    }

    public void createEstudiante(Estudiante estudiante) {
        estudianteRepository.save(estudiante);
    }

    public void updateEstudiante(Long id, Estudiante estudianteUpdate) {
        Estudiante estudiante = estudianteRepository.findById(id).orElse(null);
        if(estudiante == null)
            throw new EstudianteNoEncontradoException("No se encontro el estudiante con el id: " + id);

        estudiante.setNombre(estudianteUpdate.getNombre());
        estudiante.setApellido(estudianteUpdate.getApellido());
        estudiante.setEdad(estudianteUpdate.getEdad());
        estudiante.setCiudadResidencia(estudianteUpdate.getCiudadResidencia());
        estudiante.setGenero(estudianteUpdate.getGenero());
        estudiante.setNumeroDocumento(estudianteUpdate.getNumeroDocumento());
        estudiante.setNumeroLibretaUniversitaria(estudianteUpdate.getNumeroLibretaUniversitaria());

        estudianteRepository.save(estudiante);
    }

    public Estudiante deleteEstudiante(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id).orElse(null);
        if(estudiante == null)
            throw new EstudianteNoEncontradoException("No se encontro el estudiante con el id: " + id);

        estudianteRepository.delete(estudiante);
        return estudiante;
    }
}
