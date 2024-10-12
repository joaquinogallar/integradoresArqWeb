package com.grupo08.unicen.trabajointegradortp3.repository;

import com.grupo08.unicen.trabajointegradortp3.entity.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Long> {
    @Query("SELECT c FROM Carrera c JOIN c.estudiantes ec GROUP BY c ORDER BY COUNT(ec.estudiante) DESC")
    List<Carrera> findCarrerasConEstudiantesOrdenadasPorInscritos();
}
