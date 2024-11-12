package com.grupo08.unicen.microservicemaintenance.entity;

import com.grupo08.unicen.microservicemaintenance.dto.MaintenanceRecordDto;
import com.grupo08.unicen.microservicemonopatin.entity.Monopatin;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@ToString
public class MaintenanceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID monopatinId;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private String description;

    @Enumerated(EnumType.STRING)
    private MaintenanceStatus status;

    private BigDecimal kmsTraveled;
    private BigDecimal usageTime;

    public enum MaintenanceStatus {
        PENDING,
        IN_PROGRESS,
        COMPLETED
    }

    public MaintenanceRecord(UUID monopatinId, LocalDateTime startDate, LocalDateTime endDate, String description, String status) {
        this.monopatinId = monopatinId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;

        switch (status.toUpperCase()) {
            case "PENDING":
            case "PENDIENTE":
                this.status = MaintenanceStatus.PENDING;
                break;
            case "IN_PROGRESS":
            case "EN_PROGRESO":
                this.status = MaintenanceStatus.IN_PROGRESS;
                break;
            case "COMPLETED":
            case "COMPLETADO":
                this.status = MaintenanceStatus.COMPLETED;
                break;
            default:
                throw new IllegalArgumentException("Estado invalido: " + status);
        }
    }


    public MaintenanceRecord(MaintenanceRecordDto maintenanceRecordDto) {
        this.id = maintenanceRecordDto.getId();
        this.monopatinId = maintenanceRecordDto.getMonopatinId();
        this.startDate = maintenanceRecordDto.getStartDate();
        this.endDate = maintenanceRecordDto.getEndDate();
        this.description = maintenanceRecordDto.getDescription();
        this.status = MaintenanceStatus.valueOf(maintenanceRecordDto.getStatus());
        this.kmsTraveled = maintenanceRecordDto.getKmsTraveled();
        this.usageTime = maintenanceRecordDto.getUsageTime();
    }
}
