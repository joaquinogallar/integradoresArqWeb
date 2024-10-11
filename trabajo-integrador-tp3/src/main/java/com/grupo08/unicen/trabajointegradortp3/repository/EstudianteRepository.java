package com.grupo08.unicen.trabajointegradortp3.repository;

import com.grupo08.unicen.trabajointegradortp3.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    List<Estudiante> findAllByOrderByApellidoAsc();
    Estudiante findEstudianteByNumeroLibretaUniversitaria(String numeroLibreta);
    List<Estudiante> findAllByGenero(String genero);
}
