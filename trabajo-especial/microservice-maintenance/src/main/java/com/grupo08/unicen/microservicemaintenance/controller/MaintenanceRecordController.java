package com.grupo08.unicen.microservicemaintenance.controller;

import com.grupo08.unicen.microservicemaintenance.dto.MaintenanceRecordDto;
import com.grupo08.unicen.microservicemaintenance.dto.MonopatinDto;
import com.grupo08.unicen.microservicemaintenance.service.MaintenanceRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/maintenances")
public class MaintenanceRecordController {

    @Autowired
    private MaintenanceRecordService maintenanceRecordService;

    public MaintenanceRecordController(MaintenanceRecordService maintenanceRecordService) {
        this.maintenanceRecordService = maintenanceRecordService;
    }

    @Operation(
            summary = "Obtener todos los registros de mantenimiento",
            description = "Devuelve una lista de todos los registros de mantenimiento."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registros obtenidos correctamente", content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = MaintenanceRecordDto.class))),
            @ApiResponse(responseCode = "500", description = "Error en el servidor", content = @Content(mediaType = "application/json"))
    })
    @GetMapping
    public ResponseEntity<List<MaintenanceRecordDto>> getAllMaintenanceRecords() {
        return maintenanceRecordService.getAllMaintenanceRecords();
    }

    @Operation(
            summary = "Obtener un registro de mantenimiento por ID",
            description = "Devuelve los detalles de un registro de mantenimiento específico basado en su UUID.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "ID del mantenimiento",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UUID.class)
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro obtenido correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MaintenanceRecordDto.class))),
            @ApiResponse(responseCode = "404", description = "Registro no encontrado", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/{maintenanceId}")
    public ResponseEntity<MaintenanceRecordDto> getMaintenanceRecordById(@Parameter(description = "ID del mantenimiento") @PathVariable UUID maintenanceId) {
        return maintenanceRecordService.getMaintenanceRecordById(maintenanceId);
    }

    @Operation(
            summary = "Crear un nuevo registro de mantenimiento",
            description = "Crea un nuevo registro de mantenimiento con los datos recibidos.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del nuevo registro de mantenimiento",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MaintenanceRecordDto.class)
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro creado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud", content = @Content(mediaType = "application/json"))
    })
    @PostMapping
    public ResponseEntity<String> createMaintenanceRecord(@RequestBody  @Schema(description = "DTO del nuevo registro de mantenimiento", implementation = MaintenanceRecordDto.class) MaintenanceRecordDto newMaintenanceRecord) {
        return maintenanceRecordService.createMaintenanceRecord(newMaintenanceRecord);
    }

    @Operation(
            summary = "Eliminar un registro de mantenimiento",
            description = "Elimina un registro de mantenimiento basado en su UUID.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "ID del mantenimiento a eliminar",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UUID.class)
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro eliminado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MaintenanceRecordDto.class))),
            @ApiResponse(responseCode = "404", description = "Registro no encontrado", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/{maintenanceId}")
    public ResponseEntity<MaintenanceRecordDto> deleteMaintenanceRecordById(@Parameter(description = "ID del mantenimiento", example = "123e4567-e89b-12d3-a456-426614174000") @PathVariable UUID maintenanceId) {
        return maintenanceRecordService.deleteMaintenanceRecordById(maintenanceId);
    }

    @Operation(
            summary = "Obtener reporte de monopatines en mantenimiento",
            description = "Devuelve un reporte con los monopatines actualmente en mantenimiento."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reporte obtenido correctamente", content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = MonopatinDto.class))),
            @ApiResponse(responseCode = "500", description = "Error en el servidor", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/report-in-maintenance")
    public ResponseEntity<List<MonopatinDto>> getReportMonopatinesInMaintenance() {
        try {
            List<MonopatinDto> reporte = maintenanceRecordService.getMonopatinesInMaintenance();
            return ResponseEntity.ok(reporte);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Operation(
            summary = "Obtener reporte de monopatines activos",
            description = "Devuelve un reporte con los monopatines actualmente activos."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reporte obtenido correctamente", content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = MonopatinDto.class))),
            @ApiResponse(responseCode = "500", description = "Error en el servidor", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/report-actives")
    public ResponseEntity<List<MonopatinDto>> getReportMonopatinesActives() {
        try {
            List<MonopatinDto> reporte = maintenanceRecordService.getActivesMonopatines();
            return ResponseEntity.ok(reporte);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Operation(
            summary = "Obtener reporte de monopatines que necesitan mantenimiento",
            description = "Devuelve un reporte de monopatines que requieren mantenimiento basado en los kms.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Número de kilómetros necesarios para la evaluación",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Integer.class)
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reporte obtenido correctamente", content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = MonopatinDto.class))),
            @ApiResponse(responseCode = "500", description = "Error en el servidor", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/report-need-maintenance/{kms}")
    public ResponseEntity<List<MonopatinDto>> getReportNeedMaintenance(@Parameter(description = "Kilómetros para el reporte de mantenimiento", example = "100") @PathVariable int kms) {
        try {
            List<MonopatinDto> reporte = maintenanceRecordService.getMonopatinesWithTimePause();
            return ResponseEntity.ok(reporte);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Operation(
            summary = "Obtener reporte de monopatines sin pausa de tiempo",
            description = "Devuelve un reporte de monopatines sin pausa de tiempo en el mantenimiento."
            
                    
            
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reporte obtenido correctamente", content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = MonopatinDto.class))),
            @ApiResponse(responseCode = "500", description = "Error en el servidor", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/report-need-maintenance/no-pause")
    public ResponseEntity<List<MonopatinDto>> getReportNeedMaintenanceNoPause( @PathVariable int kms) {
        try {
            List<MonopatinDto> reporte = maintenanceRecordService.getMonopatinesWithoutTimePause();
            return ResponseEntity.ok(reporte);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

