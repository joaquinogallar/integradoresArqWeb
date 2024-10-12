package com.grupo08.unicen.trabajointegradortp3.service;

import com.grupo08.unicen.trabajointegradortp3.entity.EstudianteCarrera;
import com.grupo08.unicen.trabajointegradortp3.exception.EstudianteNoEncontradoException;
import com.grupo08.unicen.trabajointegradortp3.repository.EstudianteCarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteCarreraService {

    @Autowired
    private EstudianteCarreraRepository estudianteCarreraRepository;

    public List<EstudianteCarrera> getEstudiantesCarreras() {
        return estudianteCarreraRepository.findAll();
    }

    public EstudianteCarrera getEstudianteCarreraById(Long id) {
        EstudianteCarrera estudianteCarrera = estudianteCarreraRepository.findById(id).orElse(null);
        if(estudianteCarrera == null)
            throw new EstudianteNoEncontradoException("No se encontro la relación de estudiante y materia con el id: " + id);

        return estudianteCarrera;
    }

    public void createEstudianteCarrera(EstudianteCarrera estudianteCarrera) {
        estudianteCarreraRepository.save(estudianteCarrera);
    }

    public void updateEstudianteCarrera(Long id, EstudianteCarrera estudianteCarreraUpdate) {
        EstudianteCarrera estudianteCarrera = estudianteCarreraRepository.findById(id).orElse(null);
        if(estudianteCarrera == null)
            throw new EstudianteNoEncontradoException("No se encontro la relación de estudiante y materia con el id: " + id);

        estudianteCarrera.setEstudiante(estudianteCarreraUpdate.getEstudiante());
        estudianteCarrera.setCarrera(estudianteCarreraUpdate.getCarrera());
        estudianteCarrera.setGraduado(estudianteCarreraUpdate.isGraduado());
        estudianteCarrera.setAnioGraduado(estudianteCarreraUpdate.getAnioGraduado());

        estudianteCarreraRepository.save(estudianteCarrera);
    }

    public EstudianteCarrera deleteEstudianteCarrera(Long id) {
        EstudianteCarrera estudianteCarrera = estudianteCarreraRepository.findById(id).orElse(null);
        if(estudianteCarrera == null)
            throw new EstudianteNoEncontradoException("No se encontro la relación de estudiante y materia con el id: " + id);

        estudianteCarreraRepository.delete(estudianteCarrera);
        return estudianteCarrera;
    }
}
