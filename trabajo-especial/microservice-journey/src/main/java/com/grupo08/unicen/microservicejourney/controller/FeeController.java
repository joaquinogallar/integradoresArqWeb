package com.grupo08.unicen.microservicejourney.controller;

import com.grupo08.unicen.microservicejourney.entity.Fee;
import com.grupo08.unicen.microservicejourney.service.FeeService;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/tarifas")
public class FeeController {

    @Autowired
    FeeService tarifaService;


    @GetMapping("/")
    public ResponseEntity<?> getAllTarifas() {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(tarifaService.getAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{tarifaId}")
    public ResponseEntity<?> getTarifaById(@PathVariable UUID tarifaId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tarifaService.getById(tarifaId));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PostMapping("/")
    public ResponseEntity<?> crateTarifa(@RequestBody Fee t) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(tarifaService.crearTarifa(t));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}