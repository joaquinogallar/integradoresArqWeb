package com.grupo08.unicen.microservicejourney.controller;

import java.util.List;
import java.util.UUID;

import com.grupo08.unicen.microservicejourney.dto.PauseDto;
import com.grupo08.unicen.microservicejourney.service.PauseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pauses")
public class PauseController {

    PauseService pausaService;

    public PauseController(PauseService pausaService) {
        this.pausaService = pausaService;
    }

    @Operation(summary = "Crear una pausa en un viaje", description = "Crea una pausa asociada a un viaje específico utilizando el ID del viaje.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pausa creada correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PauseDto.class))),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("viaje/{journeyId}")
    public ResponseEntity<PauseDto> crearPausa(
            @Parameter(description = "ID del viaje en el cual se va a crear la pausa", example = "b7b1c890-f1c5-4878-bc61-58553902b4db")
            @PathVariable UUID journeyId) {
        return ResponseEntity.ok(pausaService.crearPausa(journeyId));
    }

    @Operation(summary = "Obtener las pausas de un viaje", description = "Devuelve una lista de todas las pausas realizadas durante un viaje específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pausas obtenida correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = PauseDto.class))),
            @ApiResponse(responseCode = "404", description = "No se encontraron pausas para el viaje especificado", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/viaje/{journeyId}")
    public ResponseEntity<List<PauseDto>> getPausasPorViaje(
            @Parameter(description = "ID del viaje para el cual se desean obtener las pausas")
            @PathVariable UUID journeyId) {
        return ResponseEntity.ok(pausaService.getPausasPorViaje(journeyId));
    }
}
