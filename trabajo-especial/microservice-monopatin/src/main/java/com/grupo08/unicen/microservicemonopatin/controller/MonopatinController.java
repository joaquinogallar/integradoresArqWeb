package com.grupo08.unicen.microservicemonopatin.controller;

import com.grupo08.unicen.microservicemonopatin.dto.MonopatinDto;
import com.grupo08.unicen.microservicemonopatin.entity.Monopatin;
import com.grupo08.unicen.microservicemonopatin.service.MonopatinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/monopatines")
public class MonopatinController {

    private MonopatinService monopatinService;

    public MonopatinController(MonopatinService monopatinService) {
        this.monopatinService = monopatinService;
    }

    @GetMapping
    public ResponseEntity<List<MonopatinDto>> getAllMonopatines() {
        return monopatinService.getAllMonopatines();
    }

    @GetMapping("/{monopatinId}")
    public ResponseEntity<MonopatinDto> getMonopatinById(@PathVariable UUID monopatinId) {
        return monopatinService.getMonopatinById(monopatinId);
    }

    @PostMapping
    public ResponseEntity<String> createMonopatin(@RequestBody MonopatinDto newMonopatin) {
        return monopatinService.createMonopatin(newMonopatin);
    }

    @GetMapping("/order/use-time/with-pause")
    public ResponseEntity<List<MonopatinDto>> getMonopatinesWithTimePause()  {
        return monopatinService.getMonopatinesConTiempoPausa();
    }


    @GetMapping("/order/use-time/without-pause")
    public ResponseEntity<List<MonopatinDto>> getMonopatinesWithoutTimePause()  {
        return monopatinService.getMonopatinesSinTiempoPausa();

    }
    @PutMapping("/{monopatinID}")
    public ResponseEntity<MonopatinDto>editMonopatin(@PathVariable Long monopatinID){
        return monopatinService.putMonopatin(monopatinID);
    }
    @GetMapping("/maintenance")
    public ResponseEntity<List<MonopatinDto>>getMonopatinesInMaintenance(){
        return monopatinService.getMonopatinesInMaintenance();
    }   

    @GetMapping("/actives")
    public ResponseEntity<List<MonopatinDto>>getActivesMonopatines(){
        return monopatinService.getActivesMonopatines();
    }
}
