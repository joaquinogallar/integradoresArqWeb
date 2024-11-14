package com.grupo08.unicen.microservicemaintenance.service;

import com.grupo08.unicen.microservicemaintenance.FeignClient.MonopatinClient;
import com.grupo08.unicen.microservicemaintenance.dto.MaintenanceRecordDto;
import com.grupo08.unicen.microservicemaintenance.dto.MonopatinDto;
import com.grupo08.unicen.microservicemaintenance.entity.MaintenanceRecord;
import com.grupo08.unicen.microservicemaintenance.repository.MaintenanceRecordRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MaintenanceRecordService {

    private final MaintenanceRecordRepository maintenanceRecordRepository;

    private final MonopatinClient monopatinClient;

    public MaintenanceRecordService(MaintenanceRecordRepository maintenanceRecordRepository,
                                    MonopatinClient monopatinClient) {
        this.maintenanceRecordRepository = maintenanceRecordRepository;
        this.monopatinClient = monopatinClient;
    }

    public ResponseEntity<List<MaintenanceRecordDto>> getAllMaintenanceRecords() {
        try {
            List<MaintenanceRecord> maintenanceRecords = maintenanceRecordRepository.findAll();
            List<MaintenanceRecordDto> maintenanceRecordDtos = new ArrayList<>();
            maintenanceRecords.forEach(m ->  maintenanceRecordDtos.add(new MaintenanceRecordDto(m)));
            return ResponseEntity.ok(maintenanceRecordDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    public ResponseEntity<MaintenanceRecordDto> getMaintenanceRecordById(UUID id) {
        MaintenanceRecord mRecord = maintenanceRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        MaintenanceRecordDto maintenanceRecordDto = new MaintenanceRecordDto(mRecord);
        return ResponseEntity.ok(maintenanceRecordDto);
    }

    public ResponseEntity<String> createMaintenanceRecord(MaintenanceRecordDto newMaintenanceRecord) {
        try {
            maintenanceRecordRepository.save(new MaintenanceRecord(newMaintenanceRecord));
            return ResponseEntity.ok("Record created succesfully");
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("Error: " + e);
        }
    }

    public ResponseEntity<MaintenanceRecordDto> deleteMaintenanceRecordById(UUID id) {
        MaintenanceRecord record = maintenanceRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));
        MaintenanceRecordDto maintenanceRecordDto = new MaintenanceRecordDto(record);
        maintenanceRecordRepository.delete(record);

        return ResponseEntity.ok(maintenanceRecordDto);
    }

    public List<MonopatinDto> getActivesMonopatines() {
        return monopatinClient.getActivesMonopatines();
    }

    public List<MonopatinDto> getMonopatinesInMaintenance() {
        return monopatinClient.getMonopatinesInMaintenance();
    }

    public List<MonopatinDto> getMonopatinesWithTimePause() {
        return monopatinClient.getMonopatinesByKms();
    }

    public List<MonopatinDto> getMonopatinesWithoutTimePause() {
        return monopatinClient.getMonopatinesWithoutTimePause();
    }


}
