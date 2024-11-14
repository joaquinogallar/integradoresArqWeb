package com.grupo08.unicen.microservicemaintenance.entity;

import com.grupo08.unicen.microservicemaintenance.dto.MaintenanceRecordDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;



@Data
@NoArgsConstructor
@ToString
@Document(collation = "maintenanceRecord")
public class MaintenanceRecord {
    @Id
    private UUID id;

    private UUID monopatinId;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private String description;

    private BigDecimal kmsTraveled;
    private BigDecimal usageTime;

    public enum MaintenanceStatus {
        PENDING,
        IN_PROGRESS,
        COMPLETED
    }

    public MaintenanceRecord(UUID monopatinId, LocalDateTime startDate, LocalDateTime endDate, String description) {
        this.monopatinId = monopatinId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;

    }

    public MaintenanceRecord(UUID monopatinId, LocalDateTime startDate, LocalDateTime endDate, String description, BigDecimal kmsTraveled, BigDecimal usageTime) {
        this.monopatinId = monopatinId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.kmsTraveled = kmsTraveled;
        this.usageTime = usageTime;
    }


    public MaintenanceRecord(MaintenanceRecordDto maintenanceRecordDto) {
        this.id = maintenanceRecordDto.getId();
        this.monopatinId = maintenanceRecordDto.getMonopatinId();
        this.startDate = maintenanceRecordDto.getStartDate();
        this.endDate = maintenanceRecordDto.getEndDate();
        this.description = maintenanceRecordDto.getDescription();
        this.kmsTraveled = maintenanceRecordDto.getKmsTraveled();
        this.usageTime = maintenanceRecordDto.getUsageTime();
    }
}
