package com.grupo08.unicen.microservicejourney.repository;

import com.grupo08.unicen.microservicejourney.entity.Pause;

import com.grupo08.unicen.microservicejourney.entity.Journey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JourneyRepository extends JpaRepository<Journey, UUID> {
    @Query("SELECT j FROM Journey j WHERE j.monopatinId = :id_monopatin")
    List<Journey>FindViajesPorId_monopatin(UUID id_monopatin);

    @Query("SELECT j.monopatinId FROM Journey j WHERE FUNCTION('YEAR', j.startDate) = :year GROUP BY j.monopatinId HAVING COUNT(j) > :journeys")
    List<Long> findMonopatinesByViaje(int year, int journeys);

    @Query("SELECT j.pauses FROM Journey j WHERE j.id = :journeyId")
    List<Pause> findPausasByIdViaje(UUID journeyId);
    @Query("SELECT SUM(f.fee) FROM Journey j JOIN j.fee f WHERE FUNCTION('YEAR', j.startDate) = :year AND FUNCTION('MONTH', j.startDate) BETWEEN :mesInicio AND :mesFin")
    int getFacturado(int year,int mesInicio,int mesFin);
}
   

