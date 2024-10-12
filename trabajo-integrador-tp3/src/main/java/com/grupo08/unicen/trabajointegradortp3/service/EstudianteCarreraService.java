package com.grupo08.unicen.trabajointegradortp3.service;

import com.grupo08.unicen.trabajointegradortp3.entity.Carrera;
import com.grupo08.unicen.trabajointegradortp3.entity.Estudiante;
import com.grupo08.unicen.trabajointegradortp3.entity.EstudianteCarrera;
import com.grupo08.unicen.trabajointegradortp3.exception.CarreraNoEncontradaException;
import com.grupo08.unicen.trabajointegradortp3.exception.EstudianteCarreraNoEncontradoException;
import com.grupo08.unicen.trabajointegradortp3.exception.EstudianteNoEncontradoException;
import com.grupo08.unicen.trabajointegradortp3.repository.CarreraRepository;
import com.grupo08.unicen.trabajointegradortp3.repository.EstudianteCarreraRepository;
import com.grupo08.unicen.trabajointegradortp3.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteCarreraService {

    @Autowired
    private EstudianteCarreraRepository estudianteCarreraRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private CarreraRepository carreraRepository;

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

    // METODOS TP
    public void matricularEstudianteEnCarrera(Long estudianteId, Long carreraId) {
        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new EstudianteNoEncontradoException("Estudiante no encontrado"));
        Carrera carrera = carreraRepository.findById(carreraId)
                .orElseThrow(() -> new CarreraNoEncontradaException("Carrera no encontrada"));

        EstudianteCarrera estudianteCarrera = new EstudianteCarrera(estudiante, carrera);

        estudianteCarreraRepository.save(estudianteCarrera);
    }

    public void darAltaEstudiante(Long estudianteId, Long carreraId) {
        EstudianteCarrera estudianteCarrera = estudianteCarreraRepository.findByEstudianteIdAndCarreraId(estudianteId, carreraId);
        if(estudianteCarrera == null)
            throw new EstudianteCarreraNoEncontradoException("No se encontro la relacion entra el estudiante con el id: " + estudianteId + " y la carrera con el id: " + carreraId);

        estudianteCarreraRepository.delete(estudianteCarrera);
    }
}
