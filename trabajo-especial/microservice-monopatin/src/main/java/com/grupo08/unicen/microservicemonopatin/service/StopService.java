package com.grupo08.unicen.microservicemonopatin.service;

import com.grupo08.unicen.microservicemonopatin.dto.MonopatinDto;
import com.grupo08.unicen.microservicemonopatin.dto.StopDto;
import com.grupo08.unicen.microservicemonopatin.entity.Monopatin;
import com.grupo08.unicen.microservicemonopatin.entity.Stop;
import com.grupo08.unicen.microservicemonopatin.exception.MonopatinNotFoundException;
import com.grupo08.unicen.microservicemonopatin.exception.StopNotFoundException;
import com.grupo08.unicen.microservicemonopatin.repository.MonopatinRepository;
import com.grupo08.unicen.microservicemonopatin.repository.StopRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
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

    public ResponseEntity<List<StopDto>> getAllStops() {
        try {
            List<StopDto> aux = new ArrayList<>();
            List<Stop> stops = stopRepository.findAll();
            for (Stop s : stops) {
                aux.add(new StopDto(s.getId(), s.getX(), s.getY(), s.getAddress()));
            }
            return ResponseEntity.ok(aux);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    public ResponseEntity<StopDto> getStopById(UUID stopId) {
        Stop s = stopRepository.findById(stopId)
                .orElseThrow(() -> new StopNotFoundException(stopId.toString()));
        return ResponseEntity.ok(new StopDto(s.getId(), s.getX(), s.getY(), s.getAddress()));
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

    public String deleteStop(UUID stopId) {
        Stop s = stopRepository.findById(stopId)
                .orElseThrow(() -> new StopNotFoundException(stopId.toString()));
        stopRepository.delete(s);
        return "Stop deleted successfully";
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

    public ResponseEntity<List<MonopatinDto>> getMonopatinesByStopId(UUID stopId) {
        try {
            // pregunta: getMonopatinsById
            List<MonopatinDto> aux = new ArrayList<>();
            List<Monopatin> monopatines = monopatinRepository.getMonopatinsById(stopId);
            for (Monopatin monopatin : monopatines) {
                aux.add(new MonopatinDto(monopatin.getId(), monopatin.getState(), monopatin.getX(), monopatin.getY(), monopatin.getKmTraveled(), monopatin.getUseTime()));
            }
            return ResponseEntity.ok(aux);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    public ResponseEntity<StopDto> getStopByXY(@PathVariable int x, @PathVariable int y) {
        try {
            Stop stop = stopRepository.findStopByXAndY(x, y);
            StopDto stopDto = new StopDto(stop.getId(), stop.getX(), stop.getY(), stop.getAddress());
            return ResponseEntity.ok(stopDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
   
}

