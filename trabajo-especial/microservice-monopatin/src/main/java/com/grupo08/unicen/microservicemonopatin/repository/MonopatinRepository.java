package com.grupo08.unicen.microservicemonopatin.repository;

import com.grupo08.unicen.microservicemonopatin.entity.Monopatin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MonopatinRepository extends JpaRepository<Monopatin, UUID> {
    List<Monopatin> getMonopatinesConTiempoPausa = null;

    List<Monopatin> getMonopatinsById(UUID stopId);

    @Query("SELECT m from Monopatin m order by m.kmTraveled desc")
    List<Monopatin> getMonopatinesPorKilometros();

    @Query("SELECT m from Monopatin m order by m.useTime desc")
    List<Monopatin> getMonopatinesConTiempoPausa();

    @Query("SELECT m from Monopatin m where m.state = :IN_MAINTENANCE ")
    List<Monopatin>getMonopatinesInMaintenance();

    @Query("SELECT m from Monopatin m where m.state = : AVAILABLE OR m.state = : IN_USE")
    List<Monopatin>getActivesMonopatines();

    @Query("SELECT m from Monopatin m where m.state = : AVAILABLE")
    List<Monopatin>findAvailables();
}
