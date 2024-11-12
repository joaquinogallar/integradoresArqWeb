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

    @GetMapping("/order/tiempo-uso/con-pausa")
    public ResponseEntity<List<MonopatinDto>> getMonopatinesConTiempoPausa()  {
        return monopatinService.getMonopatinesConTiempoPausa();
    }


    @GetMapping("/order/tiempo-uso/sin-pausa")
    public ResponseEntity<List<MonopatinDto>> getMonopatinesSinTiempoPausa()  {
        return monopatinService.getMonopatinesSinTiempoPausa();

    }
    @PutMapping("/{monopatinID}")
    public ResponseEntity<MonopatinDto>editarMonopatin(@PathVariable Long monopatinID){
        return monopatinService.putMonopatin(monopatinID);
    }
       
}
