package com.grupo08.unicen.microservicemaintenance.repository;

import com.grupo08.unicen.microservicemaintenance.dto.MonopatinDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.List;

@FeignClient(name = "monopatin-service", url = "http://localhost:8080/")
public interface MonopatinClient {

    @GetMapping("/api/monopatines/maintenance")
    List<MonopatinDto> getMonopatinesInMaintenance();

    // Endpoint para obtener los monopatines activos
    @GetMapping("/api/monopatines/actives")
    List<MonopatinDto> getActivesMonopatines();

    @GetMapping("/api/monopatines/order/use-time/with-pause/")
    List<MonopatinDto> getMonopatinesWithTimePause();

    @GetMapping("/api/monopatines/order/use-time/with-pause/byKms/{kms}")
    List<MonopatinDto> getMonopatinesWithTimePauseByKms(@PathVariable BigDecimal kms);

}
