package com.grupo08.unicen.microservicemaintenance.dto;

import com.grupo08.unicen.microservicemaintenance.entity.MaintenanceRecord;
import com.grupo08.unicen.microservicemonopatin.DTO.MonopatinDTO;
import com.grupo08.unicen.microservicemonopatin.entity.Monopatin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class MaintenanceRecordDto {

    private UUID id;

    private UUID monopatinId;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private String description;

    private String status;

    private BigDecimal kmsTraveled;
    private BigDecimal usageTime;

    public MaintenanceRecordDto(MaintenanceRecord maintenanceRecord) {
        this.id = maintenanceRecord.getId();
        this.description = maintenanceRecord.getDescription();
        this.startDate = maintenanceRecord.getStartDate();
        this.endDate = maintenanceRecord.getEndDate();
        this.status = maintenanceRecord.getStatus().toString();
        this.kmsTraveled = maintenanceRecord.getKmsTraveled();
        this.usageTime = maintenanceRecord.getUsageTime();
        this.monopatinId = maintenanceRecord.getMonopatinId();
    }



}
