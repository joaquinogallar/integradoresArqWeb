package com.grupo08.unicen.microservicemonopatin.controller;

import com.grupo08.unicen.microservicemonopatin.dto.MonopatinDto;
import com.grupo08.unicen.microservicemonopatin.service.MonopatinService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/monopatines")
@Tag(name = "Monopatines", description = "Gestión de monopatines")
public class MonopatinController {

    private final MonopatinService monopatinService;

    public MonopatinController(MonopatinService monopatinService) {
        this.monopatinService = monopatinService;
    }

    @Operation(summary = "Obtener todos los monopatines", description = "Devuelve una lista de todos los monopatines registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de monopatines obtenida correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = MonopatinDto.class)))
    })
    @GetMapping
    public ResponseEntity<List<MonopatinDto>> getAllMonopatines() {
        return monopatinService.getAllMonopatines();
    }

<<<<<<< HEAD
    @GetMapping("/{monopatinId}")
    public ResponseEntity<MonopatinDto> getMonopatinById(@PathVariable UUID monopatinId) {
        return monopatinService.getMonopatinById(monopatinId);
    }

    @PostMapping
    public ResponseEntity<String> createMonopatin(@RequestBody MonopatinDto newMonopatin) {
        return monopatinService.createMonopatin(newMonopatin);
    }

    @DeleteMapping("/{monoaptinId}")
    public ResponseEntity<MonopatinDto> deleteMonopatin(@PathVariable UUID monopatinId) {
        try {
            return ResponseEntity.ok(monopatinService.deleteMonopatin(monopatinId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

=======
    @Operation(summary = "Obtener monopatines por uso con pausa", description = "Devuelve una lista ordenada de monopatines a partir de su uso contando las pausas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de monopatines obtenida correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = MonopatinDto.class)))
    })
>>>>>>> 38d2d384682334152e08f7b2737ddf4a5e05558f
    @GetMapping("/order/use-time/with-pause")
    public ResponseEntity<List<MonopatinDto>> getMonopatinesWithTimePause() {
        return monopatinService.getMonopatinesConTiempoPausa();
    }

    @Operation(summary = "Ordenar monopatines por kilometraje", description = "Devuelve una lista de monopatines ordenados por kilómetros recorridos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de monopatines obtenida correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = MonopatinDto.class)))
    })
    @GetMapping("/order/byKms")
    public ResponseEntity<List<MonopatinDto>> getMonopatinesByKms() {
        return monopatinService.getMonopatinesPorKms();
    }

    @Operation(summary = "Obtener monopatines por uso sin pausa", description = "Devuelve una lista de monopatines ordenada a partir de su tiempo de uso sin contar pausas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de monopatines obtenida correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = MonopatinDto.class)))
    })
    @GetMapping("/order/use-time/without-pause")
    public ResponseEntity<List<MonopatinDto>> getMonopatinesWithoutTimePause() {
        return monopatinService.getMonopatinesSinTiempoPausa();
    }

    @Operation(summary = "Obtener monopatines en mantenimiento", description = "Devuelve una lista de monopatines que actualmente están en mantenimiento.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de monopatines en mantenimiento obtenida correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = MonopatinDto.class)))
    })
    @GetMapping("/maintenance")
    public ResponseEntity<List<MonopatinDto>> getMonopatinesInMaintenance() {
        return monopatinService.getMonopatinesInMaintenance();
    }

    @Operation(summary = "Obtener monopatines activos", description = "Devuelve una lista de monopatines que están activos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de monopatines activos obtenida correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = MonopatinDto.class)))
    })
    @GetMapping("/actives")
    public ResponseEntity<List<MonopatinDto>> getActivesMonopatines() {
        return monopatinService.getActivesMonopatines();
    }

    @Operation(summary = "Obtener monopatines cercanos", description = "Devuelve una lista de monopatines dentro de un rango de metros desde una posición dada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de monopatines cercanos obtenida correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = MonopatinDto.class))),
            @ApiResponse(responseCode = "400", description = "Parámetros inválidos en la solicitud")
    })
    @GetMapping("/nearMonopatines/{x}/{y}/{rangoMetros}")
    public ResponseEntity<List<MonopatinDto>> GetNearStops(
            @Parameter(description = "Coordenada X de la posición desde donde calcular los monopatines cercanos", example = "100") 
            @PathVariable int x,
            
            @Parameter(description = "Coordenada Y de la posición desde donde calcular los monopatines cercanos", example = "200") 
            @PathVariable int y,

            @Parameter(description = "Rango en metros para calcular los monopatines cercanos", example = "500")
            @PathVariable int rangoMetros) {
        return monopatinService.getNearMonopatines(x, y, rangoMetros);
    }
}