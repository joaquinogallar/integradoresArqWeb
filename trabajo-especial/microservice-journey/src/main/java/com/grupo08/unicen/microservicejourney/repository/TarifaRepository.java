package com.grupo08.unicen.microservicejourney.repository;

import com.grupo08.unicen.microservicejourney.entity.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TarifaRepository extends JpaRepository<Tarifa, Long>  {
    @Query(" SELECT  t.tarifa FROM Tarifa  t where  t.fecha_inicio < current date  " +
            "order by t.fecha_inicio desc limit 1")
    Tarifa getTarifaNormalEnPlazoValido();

    @Query(" SELECT  t.tarifaEspecial FROM Tarifa  t where  t.fecha_inicio < current date " +
            "order by t.fecha_inicio desc limit 1")
    Tarifa getTarifaExtraEnPlazoValido();
}

