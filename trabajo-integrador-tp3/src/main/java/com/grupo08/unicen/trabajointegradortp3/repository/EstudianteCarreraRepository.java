package com.grupo08.unicen.trabajointegradortp3.repository;

import com.grupo08.unicen.trabajointegradortp3.entity.EstudianteCarrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteCarreraRepository extends JpaRepository<EstudianteCarrera, Long> {
}
