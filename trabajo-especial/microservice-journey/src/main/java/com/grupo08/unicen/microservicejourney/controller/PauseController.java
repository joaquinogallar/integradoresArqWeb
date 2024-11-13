package com.grupo08.unicen.microservicejourney.controller;



import java.util.List;
import java.util.UUID;

import com.grupo08.unicen.microservicejourney.dto.PauseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.grupo08.unicen.microservicejourney.service.PauseService;

@RestController
@RequestMapping("/api/pauses")
public class PauseController {

    PauseService pausaService;

    public PauseController(PauseService pausaService) {
        this.pausaService = pausaService;
    }

    @PostMapping("viaje/{journeyId}")
    public ResponseEntity<PauseDto> crearPausa(@PathVariable UUID journeyId) {
        return ResponseEntity.ok(pausaService.crearPausa(journeyId));
    }

    @GetMapping("/viaje/{journeyId}")
    public ResponseEntity<List<PauseDto>> getPausasPorViaje(@PathVariable UUID journeyId) {
        return ResponseEntity.ok(pausaService.getPausasPorViaje(journeyId));
    }
}
