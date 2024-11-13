package com.grupo08.unicen.microservicemonopatin.controller;

import com.grupo08.unicen.microservicemonopatin.dto.MonopatinDto;
import com.grupo08.unicen.microservicemonopatin.dto.StopDto;
import com.grupo08.unicen.microservicemonopatin.entity.Monopatin;
import com.grupo08.unicen.microservicemonopatin.entity.Stop;
import com.grupo08.unicen.microservicemonopatin.service.StopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/stops")
public class StopController {

    private StopService stopService;

    public StopController(StopService stopService) {
        this.stopService = stopService;
    }

    @GetMapping
    public ResponseEntity<List<StopDto>> getAllStops() {
        return stopService.getAllStops();
    }

    @GetMapping("/{stopId}")
    public ResponseEntity<StopDto> getStopById(@PathVariable UUID stopId) {
        return stopService.getStopById(stopId);
    }

    @PostMapping
    public ResponseEntity<String> createStop(@RequestBody Stop newStop) {
        return  stopService.createStop(newStop);
    }

    @GetMapping("/{stopId}/monopatines")
    public ResponseEntity<List<MonopatinDto>> getMonopatinesByStopId(@PathVariable UUID stopId) {
        return stopService.getMonopatinesByStopId(stopId);
    }

    @PutMapping("/{stopId}/monopatines/{monopatinId}")
    public ResponseEntity<String> addMonopatinToStop(@PathVariable UUID stopId, @PathVariable UUID monopatinId) {
        return stopService.addMonopatinToStop(stopId, monopatinId);
    }
    
}
