package com.grupo08.unicen.microservicemonopatin.service;

import com.grupo08.unicen.microservicemonopatin.entity.Monopatin;
import com.grupo08.unicen.microservicemonopatin.entity.Stop;
import com.grupo08.unicen.microservicemonopatin.exception.MonopatinNotFoundException;
import com.grupo08.unicen.microservicemonopatin.exception.StopNotFoundException;
import com.grupo08.unicen.microservicemonopatin.repository.MonopatinRepository;
import com.grupo08.unicen.microservicemonopatin.repository.StopRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StopService {

    private StopRepository stopRepository;
    private MonopatinRepository monopatinRepository;

    public StopService(StopRepository stopRepository, MonopatinRepository monopatinRepository) {
        this.stopRepository = stopRepository;
        this.monopatinRepository = monopatinRepository;
    }

    public ResponseEntity<List<Stop>> getAllStops() {
        try {
            List<Stop> stops = stopRepository.findAll();
            return ResponseEntity.ok(stops);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    public ResponseEntity<Stop> getStopById(UUID stopId) {
        Stop stop = stopRepository.findById(stopId)
                .orElseThrow(() -> new StopNotFoundException(stopId.toString()));
        return ResponseEntity.ok(stop);
    }

    public ResponseEntity<String> createStop(Stop stop) {
        try {
            stopRepository.save(stop);
            return ResponseEntity.ok("Stop created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    public ResponseEntity<String> addMonopatinToStop(UUID stopId, UUID monopatinId) {
        try {
            Stop stop = stopRepository.findById(stopId)
                            .orElseThrow(() -> new StopNotFoundException(stopId.toString()));
            Monopatin monopatin = monopatinRepository.findById(monopatinId)
                            .orElseThrow(() -> new MonopatinNotFoundException(monopatinId.toString()));

            stop.getMonopatines().add(monopatin);
            stopRepository.save(stop);
            return ResponseEntity.ok("Monopatin added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    public ResponseEntity<List<Monopatin>> getMonopatinesByStopId(UUID stopId) {
        try {
            // pregunta: getMonopatinsById
            List<Monopatin> monopatines = monopatinRepository.getMonopatinsById(stopId);
            return ResponseEntity.ok(monopatines);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}

