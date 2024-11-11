package com.grupo08.unicen.microservicemonopatin.controller;

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
    public ResponseEntity<List<Stop>> getAllStops() {
        return stopService.getAllStops();
    }

    @GetMapping("/{stopId}")
    public ResponseEntity<Stop> getStopById(@PathVariable UUID stopId) {
        return stopService.getStopById(stopId);
    }

    @PostMapping
    public ResponseEntity<String> createStop(@RequestBody Stop newStop) {
        return  stopService.createStop(newStop);
    }
}
