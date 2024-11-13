package com.grupo08.unicen.microservicemaintenance.controller;


import com.grupo08.unicen.microservicemaintenance.dto.MaintenanceRecordDto;
import com.grupo08.unicen.microservicemaintenance.dto.MonopatinDto;
import com.grupo08.unicen.microservicemaintenance.service.MaintenanceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/mantenimiento")
public class MaintenanceRecordController {
    @Autowired
    private MaintenanceRecordService maintenanceRecordService;

    public MaintenanceRecordController(MaintenanceRecordService maintenanceRecordService) {
        this.maintenanceRecordService = maintenanceRecordService;
    }

    @GetMapping
    public ResponseEntity<List<MaintenanceRecordDto>> getAllMaintenanceRecords() {
        return maintenanceRecordService.getAllMaintenanceRecords();
    }

    @GetMapping("/{maintenanceId}")
    public ResponseEntity<MaintenanceRecordDto> getMaintenanceRecordById(@PathVariable UUID maintenanceId) {
        return maintenanceRecordService.getMaintenanceRecordById(maintenanceId);
    }

    @PostMapping
    public ResponseEntity<String> createMaintenanceRecord(@RequestBody MaintenanceRecordDto newMaintenanceRecord) {
        return maintenanceRecordService.createMaintenanceRecord(newMaintenanceRecord);
    }

    @DeleteMapping("/{maintenanceId}")
    public ResponseEntity<MaintenanceRecordDto> deleteMaintenanceRecordById(@PathVariable UUID maintenanceId) {
        return maintenanceRecordService.deleteMaintenanceRecordById(maintenanceId);
    }

    @GetMapping("/report-in-maintenance/")
    public ResponseEntity<List<MonopatinDto>>GetReportMonopatinesInMaintenance(){
        try {
            List<MonopatinDto> reporte = maintenanceRecordService.getMonopatinesInMaintenance();
            return ResponseEntity.ok(reporte);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/report-actives/")
    public ResponseEntity<List<MonopatinDto>>GetReportMonopatinesActives(){
        try {
            List<MonopatinDto> reporte = maintenanceRecordService.getActivesMonopatines();
            return ResponseEntity.ok(reporte);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

}
