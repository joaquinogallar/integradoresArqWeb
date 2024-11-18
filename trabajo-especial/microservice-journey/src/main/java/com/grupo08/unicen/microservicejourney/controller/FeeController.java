package com.grupo08.unicen.microservicejourney.controller;

import com.grupo08.unicen.microservicejourney.dto.FeeDto;
import com.grupo08.unicen.microservicejourney.entity.Fee;
import com.grupo08.unicen.microservicejourney.service.FeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/fees")
public class FeeController {

    @Autowired
    FeeService feeService;

    @Operation(summary = "Obtener todas las tarifas", description = "Devuelve una lista de todas las tarifas registradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de tarifas obtenida correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = FeeDto.class)))
    })
    @GetMapping
    public ResponseEntity<List<FeeDto>> getAllFees() {
        return feeService.getAllFees();
    }

    @Operation(summary = "Obtener tarifa por ID", description = "Devuelve los detalles de una tarifa específica basada en su UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarifa obtenida correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = FeeDto.class))),
            @ApiResponse(responseCode = "404", description = "Tarifa no encontrada", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/{tarifaId}")
    public ResponseEntity<FeeDto> getFeeById(
            @Parameter(description = "ID único de la tarifa (UUID)")
            @PathVariable UUID tarifaId) {
        return feeService.getFeeById(tarifaId);
    }

    @Operation(summary = "Crear una nueva tarifa", description = "Permite registrar una nueva tarifa.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarifa creada correctamente", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud", content = @Content(mediaType = "application/json"))
    })
    @PostMapping
    public ResponseEntity<FeeDto> createFee(
            @Parameter(description = "Objeto con la información de la tarifa a crear", required = true)
            @RequestBody FeeDto t) {
        return feeService.crearTarifa(t);
    }
}
