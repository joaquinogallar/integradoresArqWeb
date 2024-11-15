package com.grupo08.unicen.microservicejourney.controller;

import com.grupo08.unicen.microservicejourney.dto.FeeDto;
import com.grupo08.unicen.microservicejourney.entity.Fee;
import com.grupo08.unicen.microservicejourney.service.FeeService;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/fees")
public class FeeController {

    @Autowired
    FeeService feeService;

    @GetMapping
    public ResponseEntity<List<FeeDto>> getAllFees() {
        return feeService.getAllFees();
    }

    @GetMapping("/{tarifaId}")
    public ResponseEntity<FeeDto> getFeeById(@PathVariable UUID tarifaId) {
        return feeService.getFeeById(tarifaId);
    }

    @PostMapping
    public ResponseEntity<FeeDto> createFee(@RequestBody FeeDto t) {
        return feeService.crearTarifa(t);
    }
}