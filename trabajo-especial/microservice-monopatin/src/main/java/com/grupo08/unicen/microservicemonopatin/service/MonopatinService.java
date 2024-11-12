package com.grupo08.unicen.microservicemonopatin.service;

import com.grupo08.unicen.microservicemonopatin.dto.MonopatinDto;
import com.grupo08.unicen.microservicemonopatin.entity.Monopatin;
import com.grupo08.unicen.microservicemonopatin.exception.MonopatinNotFoundException;
import com.grupo08.unicen.microservicemonopatin.repository.MonopatinRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MonopatinService {

    private MonopatinRepository monopatinRepository;

    // dependency injection
    public MonopatinService(MonopatinRepository monopatinRepository) {
        this.monopatinRepository = monopatinRepository;
    }

    public ResponseEntity<List<MonopatinDto>> getAllMonopatines() {
        try {
            List<Monopatin> monopatines = monopatinRepository.findAll();
            List<MonopatinDto> monopatinDtos = new ArrayList<>();
            monopatines.forEach(m -> monopatinDtos.add(new MonopatinDto(m)));
            return ResponseEntity.ok(monopatinDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    public ResponseEntity<MonopatinDto> getMonopatinById(UUID monopatinId) {
        Monopatin monopatin = monopatinRepository.findById(monopatinId)
                .orElseThrow(() -> new MonopatinNotFoundException(monopatinId.toString()));
        return ResponseEntity.ok(new MonopatinDto(monopatin));
    }

    public ResponseEntity<String> createMonopatin(MonopatinDto monopatin) {
        try {
            monopatinRepository.save(new Monopatin(monopatin));
            return ResponseEntity.ok("Monopatin created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e);
        }
    }

    public ResponseEntity<List<MonopatinDto>> getMonopatinesConTiempoPausa() {
           try{
            List<Monopatin> monopatines= this.monopatinRepository.getMonopatinesConTiempoPausa();
            List<MonopatinDto> respuesta = new ArrayList<>();
            for (Monopatin monopatin : monopatines) {
                respuesta.add(new MonopatinDto(monopatin)) ;
            }
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
    

    public ResponseEntity<List<MonopatinDto>> getMonopatinesSinTiempoPausa() {
        try{
            List<Monopatin> monopatines= this.monopatinRepository.getMonopatinesSinTiempoPausa();
            List<MonopatinDto> respuesta = new ArrayList<>();
            for (Monopatin monopatin : monopatines) {
                respuesta.add(new MonopatinDto(monopatin));
            }
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(null);
        }
    }

    public ResponseEntity<MonopatinDto> putMonopatin(Long monopatinID) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'putMonopatin'");
    }

    public ResponseEntity <List<MonopatinDto>> getMonopatinesInMaintenance() {
        try {
            List<Monopatin>m = monopatinRepository.getMonopatinesInMaintenance();
        List<MonopatinDto> aux = new ArrayList<>();
        for (Monopatin monopatin : m) {
            aux.add(new MonopatinDto(monopatin));
        }
        return ResponseEntity.ok(aux);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(null);
        }
        
    }

    public ResponseEntity<List<MonopatinDto>> getActivesMonopatines() {
        try {
            List<Monopatin>m = monopatinRepository.getActivesMonopatines();
        List<MonopatinDto> aux = new ArrayList<>();
        for (Monopatin monopatin : m) {
            aux.add(new MonopatinDto(monopatin));
        }
        return ResponseEntity.ok(aux);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(null);
    }
}
}



