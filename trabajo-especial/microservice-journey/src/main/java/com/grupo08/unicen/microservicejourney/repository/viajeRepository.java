package com.grupo08.unicen.microservicejourney.repository;

import com.grupo08.unicen.microservicejourney.entity.Pausa;
import com.grupo08.unicen.microservicejourney.entity.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface viajeRepository extends JpaRepository<Viaje,Long> {
@Query("SELECT v FROM Viaje v WHERE v.monopatin.id= :id_monopatin")
    List<Viaje>FindViajesPorId_monopatin(Long id_monopatin);

    @Query("SELECT v.monopatin.id FROM Viaje v WHERE FUNCTION('YEAR', v.fecha_inicio) = :anio GROUP BY v.monopatin.id HAVING COUNT(v) > :viajes")
    List<Long> findMonopatinesByViaje(int anio,int viajes);

    @Query("SELECT v.pausas FROM Viaje v WHERE v.id = :idViaje")
    List<Pausa> findPausasByIdViaje(Long idViaje);
}

