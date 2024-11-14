package com.grupo08.unicen.microservicemaintenance.repository;

import com.grupo08.unicen.microservicemaintenance.entity.MaintenanceRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MaintenanceRecordRepository extends MongoRepository<MaintenanceRecord, UUID> {
}
