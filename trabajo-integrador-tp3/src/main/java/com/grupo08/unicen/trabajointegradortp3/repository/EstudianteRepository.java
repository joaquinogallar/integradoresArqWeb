package com.grupo08.unicen.trabajointegradortp3.repository;

import com.grupo08.unicen.trabajointegradortp3.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    List<Estudiante> findAllByOrderByApellidoAsc();
    Estudiante findEstudianteByNumeroLibretaUniversitaria(String numeroLibreta);
    List<Estudiante> findAllByGenero(String genero);
    @Query("SELECT ec.estudiante FROM EstudianteCarrera ec WHERE ec.carrera.id = :idCarrera")
    List<Estudiante> findEstudiantesByIdCarrera(Long idCarrera);
    @Query("SELECT ec.estudiante FROM EstudianteCarrera ec WHERE ec.carrera.id = :idCarrera AND ec.graduado = true ORDER BY ec.anioGraduado")
    List<Estudiante> findEgresadosByIdCarrera(Long idCarrera);
}
