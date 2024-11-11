package com.grupo08.unicen.microservicemonopatin.repository;

import com.grupo08.unicen.microservicemonopatin.entity.Monopatin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MonopatinRepository extends JpaRepository<Monopatin, UUID> {
    List<Monopatin> getMonopatinsById(UUID stopId);
}
