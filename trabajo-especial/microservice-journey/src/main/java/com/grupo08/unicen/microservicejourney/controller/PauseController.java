package com.grupo08.unicen.microservicejourney.controller;



import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.grupo08.unicen.microservicejourney.service.PauseService;

@RestController
@RequestMapping("/pausas")
public class PauseController {


     @Autowired
     PauseService pausaService;

    @PostMapping("viaje/{idViaje}")
    public ResponseEntity<?> crearPausa(@PathVariable UUID idViaje){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(pausaService.crearPausa(idViaje));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



    @GetMapping("/viaje/{idViaje}")
    public ResponseEntity<?> getPausasPorViaje(@PathVariable UUID idViaje){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(pausaService.getPausasPorViaje(idViaje));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
