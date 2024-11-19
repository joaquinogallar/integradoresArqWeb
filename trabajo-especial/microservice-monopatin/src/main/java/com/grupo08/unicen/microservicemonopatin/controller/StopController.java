package com.grupo08.unicen.microservicemonopatin.controller;

import com.grupo08.unicen.microservicemonopatin.dto.MonopatinDto;
import com.grupo08.unicen.microservicemonopatin.dto.StopDto;
import com.grupo08.unicen.microservicemonopatin.entity.Stop;
import com.grupo08.unicen.microservicemonopatin.service.StopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/stops")
public class StopController {

    private final StopService stopService;

    public StopController(StopService stopService) {
        this.stopService = stopService;
    }

    @Operation(summary = "Obtener todas las paradas", description = "Devuelve una lista de todas las paradas registradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paradas obtenidas correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = StopDto.class)))
    })
    @GetMapping
    public ResponseEntity<List<StopDto>> getAllStops() {
        return stopService.getAllStops();
    }

    @Operation(summary = "Obtener una parada por ID", description = "Devuelve los detalles de una parada específica basada en su UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parada obtenida correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = StopDto.class))),
            @ApiResponse(responseCode = "404", description = "Parada no encontrada", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/{stopId}")
    public ResponseEntity<StopDto> getStopById(@Parameter(description = "ID único de la parada (UUID)", example = "a29a40c3-d2c8-43b0-b5b6-7b9029bfa5e5")
                                                @PathVariable UUID stopId) {
        return stopService.getStopById(stopId);
    }

    @DeleteMapping("/{stopId}")
    public ResponseEntity<String> deleteStop(@PathVariable UUID stopId) {
        try {
            return ResponseEntity.ok(stopService.deleteStop(stopId));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @Operation(summary = "Crear una nueva parada", description = "Permite registrar una nueva parada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parada creada correctamente", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud", content = @Content(mediaType = "application/json"))
    })
    @PostMapping
    public ResponseEntity<String> createStop(@Parameter(description = "Objeto con la información de la nueva parada a registrar", required = true)
                                             @RequestBody Stop newStop) {
        return stopService.createStop(newStop);
    }

    @Operation(summary = "Obtener monopatines de una parada", description = "Devuelve una lista de monopatines asociados a una parada específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Monopatines obtenidos correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = MonopatinDto.class))),
            @ApiResponse(responseCode = "404", description = "Parada no encontrada", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/{stopId}/monopatines")
    public ResponseEntity<List<MonopatinDto>> getMonopatinesByStopId(@Parameter(description = "ID único de la parada (UUID)", example = "a29a40c3-d2c8-43b0-b5b6-7b9029bfa5e5")
                                                                    @PathVariable UUID stopId) {
        return stopService.getMonopatinesByStopId(stopId);
    }

    @Operation(summary = "Añadir un monopatín a una parada", description = "Asocia un monopatín a una parada específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Monopatín añadido correctamente", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Parada o monopatín no encontrado", content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/{stopId}/monopatines/{monopatinId}")
    public ResponseEntity<String> addMonopatinToStop(@Parameter(description = "ID único de la parada (UUID)", example = "a29a40c3-d2c8-43b0-b5b6-7b9029bfa5e5")
                                                     @PathVariable UUID stopId,
                                                     @Parameter(description = "ID único del monopatín (UUID)", example = "fa39b0b6-3a1e-4f1f-b47b-cf50634e5678")
                                                     @PathVariable UUID monopatinId) {
        return stopService.addMonopatinToStop(stopId, monopatinId);
    }

    @Operation(summary = "Obtener una parada por coordenadas", description = "Devuelve una parada basada en las coordenadas x e y.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parada obtenida correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = StopDto.class))),
            @ApiResponse(responseCode = "404", description = "Parada no encontrada", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/{x}/{y}")
    public ResponseEntity<StopDto> getStopByXY(@Parameter(description = "Coordenada X de la parada", example = "100")
                                               @PathVariable int x,
                                               @Parameter(description = "Coordenada Y de la parada", example = "200")
                                               @PathVariable int y) {
        return stopService.getStopByXY(x, y);
    }
}
