package com.grupo08.unicen.trabajointegradortp3.service;

import com.grupo08.unicen.trabajointegradortp3.dtos.EgresadoDTO;
import com.grupo08.unicen.trabajointegradortp3.dtos.EstudianteDTO;
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

import java.util.ArrayList;
import java.util.List;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private CarreraRepository carreraRepository;

    @Autowired
    private EstudianteCarreraRepository estudianteCarreraRepository;

    public List<EstudianteDTO> getEstudiantes() {
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

    public void createEstudiante(List<Estudiante> estudiantes) {
        estudiantes.forEach(estudiante -> estudianteRepository.save(estudiante));
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

    public List<EstudianteDTO> findEstudiantesByIdCarrera(Long idCarrera) {
        List<Estudiante> estudiantes = estudianteRepository.findEstudiantesByIdCarrera(idCarrera);
        List<EstudianteDTO> estudianteDTO = new ArrayList<>();
        estudiantes.forEach(estudiante -> estudianteDTO.add(new EstudianteDTO(estudiante)));

        return estudianteDTO;
    }

    public List<EgresadoDTO> findEgresadosByIdCarrera(Long idCarrera) {
        Carrera carrera = carreraRepository.findById(idCarrera).orElse(null);
        if(carrera == null)
            throw new CarreraNoEncontradaException("No se encontro la carrera con el id: " + idCarrera);

        List<Estudiante> egresados = estudianteRepository.findEgresadosByIdCarrera(idCarrera);
        List<EgresadoDTO> egresadoDTO = new ArrayList<>();

        egresados.forEach(estudiante -> {
            EstudianteCarrera estudianteCarrera = estudianteCarreraRepository.findByEstudianteIdAndCarreraId(estudiante.getId(), idCarrera);
            egresadoDTO.add(new EgresadoDTO(estudianteCarrera));
        });

        return egresadoDTO;
    }

    // METODOS TP
    public String inscribirEstudianteACarrera(Long idEstudiante, Long idCarrera) {
        Carrera carrera = carreraRepository.findById(idCarrera).orElse(null);
        if(carrera == null) throw new CarreraNoEncontradaException("No se encontro la carrera con el id: " + idCarrera);
        Estudiante estudiante = estudianteRepository.findById(idEstudiante).orElse(null);
        if(estudiante == null) throw new EstudianteCarreraNoEncontradoException("No se encontro el estudiante con el id: " + idEstudiante);

        if(estudianteCarreraRepository.findByEstudianteIdAndCarreraId(estudiante.getId(), idCarrera) != null)         return "El estudiante " + idEstudiante + " ya esta inscripto en la carrera " + idCarrera;

        EstudianteCarrera estudianteCarrera = new EstudianteCarrera(estudiante, carrera);
        estudianteCarreraRepository.save(estudianteCarrera);
        return "Estudiante inscripto en la carrera " + idCarrera;
    }

    public String darAltaEstudianteDeCarrera(Long idEstudiante, Long idCarrera) {
        EstudianteCarrera estudianteCarrera = estudianteCarreraRepository.findByEstudianteIdAndCarreraId(idEstudiante, idCarrera);
        if(estudianteCarrera == null) throw new EstudianteCarreraNoEncontradoException("No se encontro la relacion entre el estudiante con el id: " + idEstudiante + " y la carrera con el id: " + idCarrera);

        estudianteCarreraRepository.delete(estudianteCarrera);
        return "El estudiante " + idEstudiante + " ha sido dado de baja de la carrera " + idCarrera;
    }

    public List<EstudianteDTO> getEstudiantesOrderByApellido() {
        List<Estudiante> estudiantes = estudianteRepository.findAllByOrderByApellidoAsc();
        List<EstudianteDTO> estudianteDTO = new ArrayList<>();
        estudiantes.forEach(estudiante -> estudianteDTO.add(new EstudianteDTO(estudiante)));

        return estudianteDTO;
    }

    public EstudianteDTO findEstudianteByNumeroLibretaUniversitaria(String numeroLibreta) {
        Estudiante estudiante = estudianteRepository.findEstudianteByNumeroLibretaUniversitaria(numeroLibreta);
        if(estudiante == null)
            throw new EstudianteNoEncontradoException("No se encontro el estudiante con el numero de libreta: " + numeroLibreta);

        return new EstudianteDTO(estudiante);
    }

    public List<EstudianteDTO> findAllByGenero(String genero) {
        List<Estudiante> estudiantes = estudianteRepository.findAllByGenero(genero);
        List<EstudianteDTO> estudianteDTO = new ArrayList<>();
        estudiantes.forEach(estudiante -> estudianteDTO.add(new EstudianteDTO(estudiante)));

        return estudianteDTO;
    }

}
