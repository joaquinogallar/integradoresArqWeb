package com.grupo08.unicen.microservicejourney.repository;

import com.grupo08.unicen.microservicejourney.entity.Pause;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public   interface PauseRepository extends JpaRepository<Pause, UUID> {
    @Query("SELECT p FROM Pause p WHERE p.journey.id = :journeyId")
    List<Pause> findPausasByidviaje(@Param("journeyId") UUID journeyId);


}
