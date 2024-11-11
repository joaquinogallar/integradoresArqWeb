package com.grupo08.unicen.microservicemonopatin.service;

import com.grupo08.unicen.microservicemonopatin.entity.Stop;
import com.grupo08.unicen.microservicemonopatin.exception.StopNotFoundException;
import com.grupo08.unicen.microservicemonopatin.repository.StopRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StopService {

    private StopRepository stopRepository;

    public StopService(StopRepository stopRepository) {
        this.stopRepository = stopRepository;
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

}

