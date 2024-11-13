package com.grupo08.unicen.microservicemonopatin.repository;

import com.grupo08.unicen.microservicemonopatin.entity.Monopatin;
import com.grupo08.unicen.microservicemonopatin.entity.Stop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StopRepository extends JpaRepository<Stop, UUID> {
    Stop findStopByXAndY(int x, int y);
}
