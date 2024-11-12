package com.grupo08.unicen.microservicemaintenance.controller;


import com.grupo08.unicen.microservicemaintenance.dto.MaintenanceRecordDto;
import com.grupo08.unicen.microservicemaintenance.entity.MaintenanceRecord;
import com.grupo08.unicen.microservicemaintenance.service.MaintenanceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/mantenimientos")
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
}
