package com.grupo08.unicen.microservicejourney.repository;

import com.grupo08.unicen.microservicejourney.entity.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FeeRepository extends JpaRepository<Fee, UUID>  {
    @Query(" SELECT  f.fee FROM Fee f where  f.startDate < current date  " +
            "order by f.startDate desc limit 1")
    Fee getTarifaNormalEnPlazoValido();

    @Query(" SELECT  f.specialFee FROM Fee f where  f.startDate < current date " +
            "order by f.startDate desc limit 1")
    Fee getTarifaExtraEnPlazoValido();
}

