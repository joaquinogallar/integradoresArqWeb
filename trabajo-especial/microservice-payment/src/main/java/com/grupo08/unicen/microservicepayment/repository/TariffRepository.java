package com.grupo08.unicen.microservicepayment.repository;

import com.grupo08.unicen.microservicepayment.entity.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface TariffRepository extends JpaRepository<Tariff, UUID> {}