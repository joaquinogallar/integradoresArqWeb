package com.grupo08.unicen.microservicejourney.repository;

import com.grupo08.unicen.microservicejourney.entity.Pausa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public   interface PausaRepository extends JpaRepository<Pausa,Long> {
    @Query("SELECT p FROM Pausa p WHERE p.viaje.id = :id_viaje")
    List<Pausa> findPausasByidviaje(Long id_viaje);
}
