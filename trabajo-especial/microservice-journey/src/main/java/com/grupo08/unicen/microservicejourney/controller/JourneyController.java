package com.grupo08.unicen.microservicejourney.controller;


import com.grupo08.unicen.microservicejourney.dto.JourneyDto;
import com.grupo08.unicen.microservicejourney.model.MonopatinDto;
import com.grupo08.unicen.microservicejourney.service.JourneyService;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/journeys")
public class JourneyController {

    JourneyService viajeService ;

    public JourneyController(JourneyService journeyService) {
        this.viajeService = journeyService;
    }

    @GetMapping
    public  ResponseEntity<List<JourneyDto>> getAll(){
        return viajeService.getAll();
    }

    @GetMapping("/monopatines/viajes/{cant}/{anio}")
    public ResponseEntity<List<MonopatinDto>> getMonopatinesByViaje(@PathVariable int cant, @PathVariable int anio){
        return viajeService.getMonopatinesByXViajes(cant, anio);
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
    public ResponseEntity<JourneyDto> createViaje(@PathVariable("monopatinId") UUID monopatinId, @PathVariable("usuarioId") UUID usuarioId){
       try {
           return ResponseEntity.ok(viajeService.createViaje(monopatinId, usuarioId));
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
       }
    }


    @GetMapping("/monopatines/{monopatinId}")
    public ResponseEntity<List<JourneyDto>>getViajeByMonopatin(@PathVariable UUID monopatinId) {
        return viajeService.getViajeByMonopatin(monopatinId);
    }

    @GetMapping("/facturado/{year}/{mes}/{mes2}")
    public int getFacturadoEntreMeses(@PathVariable int year, int mesInicio, int mesFinal){
        return viajeService.getFacturadoEntreMeses(year,mesInicio,mesFinal);
    }


}