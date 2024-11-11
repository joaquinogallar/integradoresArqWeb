package com.grupo08.unicen.microservicemonopatin.service;

import com.grupo08.unicen.microservicemonopatin.entity.Monopatin;
import com.grupo08.unicen.microservicemonopatin.exception.MonopatinNotFoundException;
import com.grupo08.unicen.microservicemonopatin.repository.MonopatinRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MonopatinService {

    private MonopatinRepository monopatinRepository;

    // dependency injection
    public MonopatinService(MonopatinRepository monopatinRepository) {
        this.monopatinRepository = monopatinRepository;
    }

    public ResponseEntity<List<Monopatin>> getAllMonopatines() {
        try {
            List<Monopatin> monopatines = monopatinRepository.findAll();
            return ResponseEntity.ok(monopatines);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    public ResponseEntity<Monopatin> getMonopatinById(UUID monopatinId) {
        Monopatin monopatin = monopatinRepository.findById(monopatinId)
                .orElseThrow(() -> new MonopatinNotFoundException(monopatinId.toString()));
        return ResponseEntity.ok(monopatin);
    }

    public ResponseEntity<String> createMonopatin(Monopatin monopatin) {
        try {
            monopatinRepository.save(monopatin);
            return ResponseEntity.ok("Monopatin created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e);
        }
    }


}
