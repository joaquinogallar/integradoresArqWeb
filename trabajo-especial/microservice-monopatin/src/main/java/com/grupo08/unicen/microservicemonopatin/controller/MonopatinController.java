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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @Operation(summary = "Obtener monopatín por ID", description = "Devuelve los detalles de un monopatín específico basado en su UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Monopatín obtenido correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MonopatinDto.class))),
            @ApiResponse(responseCode = "404", description = "Monopatín no encontrado", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/{monopatinId}")
    public ResponseEntity<MonopatinDto> getMonopatinById(@Parameter(description = "ID único del monopatín (UUID)", example = "fa39b0b6-3a1e-4f1f-b47b-cf50634e5678")
                                                         @PathVariable UUID monopatinId) {
        return monopatinService.getMonopatinById(monopatinId);
    }

    @Operation(summary = "Crear un nuevo monopatín", description = "Permite registrar un nuevo monopatín.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Monopatín creado correctamente", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud", content = @Content(mediaType = "application/json"))
    })
    @PostMapping
    public ResponseEntity<String> createMonopatin(@Parameter(description = "Información del nuevo monopatín a registrar", required = true)
                                                  @RequestBody MonopatinDto newMonopatin) {
        return monopatinService.createMonopatin(newMonopatin);
    }

    @Operation(summary = "Eliminar un monopatín", description = "Elimina un monopatín específico basado en su UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Monopatín eliminado correctamente"),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud")
    })
    @DeleteMapping("/{monopatinId}")
    public ResponseEntity<MonopatinDto> deleteMonopatin(@Parameter(description = "ID único del monopatín a eliminar (UUID)", example = "fa39b0b6-3a1e-4f1f-b47b-cf50634e5678")
                                                        @PathVariable UUID monopatinId) {
        try {
            return ResponseEntity.ok(monopatinService.deleteMonopatin(monopatinId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Operation(summary = "Ordenar monopatines por tiempo de uso (con pausas)", description = "Devuelve una lista de monopatines ordenada por su tiempo de uso, considerando las pausas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = MonopatinDto.class)))
    })
    @GetMapping("/order/use-time/with-pause")
    public ResponseEntity<List<MonopatinDto>> getMonopatinesWithTimePause() {
        return monopatinService.getMonopatinesConTiempoPausa();
    }

    @Operation(summary = "Ordenar monopatines por kilometraje", description = "Devuelve una lista de monopatines ordenada por kilómetros recorridos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = MonopatinDto.class)))
    })
    @GetMapping("/order/byKms")
    public ResponseEntity<List<MonopatinDto>> getMonopatinesByKms() {
        return monopatinService.getMonopatinesPorKms();
    }

    @Operation(summary = "Ordenar monopatines por tiempo de uso (sin pausas)", description = "Devuelve una lista de monopatines ordenada por su tiempo de uso, sin considerar las pausas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = MonopatinDto.class)))
    })
    @GetMapping("/order/use-time/without-pause")
    public ResponseEntity<List<MonopatinDto>> getMonopatinesWithoutTimePause() {
        return monopatinService.getMonopatinesSinTiempoPausa();
    }

    @Operation(summary = "Obtener monopatines en mantenimiento", description = "Devuelve una lista de monopatines que están en mantenimiento.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = MonopatinDto.class)))
    })
    @GetMapping("/maintenance")
    public ResponseEntity<List<MonopatinDto>> getMonopatinesInMaintenance() {
        return monopatinService.getMonopatinesInMaintenance();
    }

    @Operation(summary = "Obtener monopatines activos", description = "Devuelve una lista de monopatines que están activos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = MonopatinDto.class)))
    })
    @GetMapping("/actives")
    public ResponseEntity<List<MonopatinDto>> getActivesMonopatines() {
        return monopatinService.getActivesMonopatines();
    }

    @Operation(summary = "Obtener monopatines cercanos", description = "Devuelve una lista de monopatines cercanos a una posición específica dentro de un rango de metros.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = MonopatinDto.class))),
            @ApiResponse(responseCode = "400", description = "Parámetros inválidos en la solicitud")
    })
    @GetMapping("/nearMonopatines/{x}/{y}/{rangoMetros}")
    public ResponseEntity<List<MonopatinDto>> getNearStops(
            @Parameter(description = "Coordenada X", example = "100")
            @PathVariable int x,
            @Parameter(description = "Coordenada Y", example = "200")
            @PathVariable int y,
            @Parameter(description = "Rango en metros", example = "500")
            @PathVariable int rangoMetros) {
        return monopatinService.getNearMonopatines(x, y, rangoMetros);
    }
}
