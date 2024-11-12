package com.grupo08.unicen.microservicejourney.controller;


import com.grupo08.unicen.microservicejourney.dto.JourneyDto;
import com.grupo08.unicen.microservicejourney.service.JourneyService;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/viajes")
public class JourneyController {

    @Autowired
    JourneyService viajeService ;

    @GetMapping("/")
    public  ResponseEntity<List<JourneyDto>> getAll(){
        return viajeService.getAll();
    }
    @GetMapping("/monopatines/viajes/{cant}/{anio}")
    public ResponseEntity<?> getMonopatinesByViaje(@PathVariable int cant, @PathVariable int anio){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(viajeService.getMonopatinesByViajes(cant, anio));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/endViaje/{idViaje}")

    public ResponseEntity<JourneyDto> endViaje(@PathVariable UUID idViaje){
        try {
            return ResponseEntity.ok(viajeService.endViaje(idViaje));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

   @PostMapping("/crear/{monopatinId}/usuario/{usuarioId}")
   public void createViaje(@PathVariable("monopatinId") UUID monopatinId, @PathVariable("usuarioId") UUID usuarioId){
       viajeService.createViaje(monopatinId, usuarioId);
   }

    @GetMapping("/monopatin/{monopatinId}")
    public ResponseEntity<?>getViajeByMonopatin(@PathVariable UUID monopatinId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(viajeService.getViajeByMonopatin(monopatinId));
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}